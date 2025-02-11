package bookingsys.concertttbookingsys.service;

import bookingsys.concertttbookingsys.entity.Concert;
import bookingsys.concertttbookingsys.entity.Ticket;
import bookingsys.concertttbookingsys.repository.ConcertRepository;
import bookingsys.concertttbookingsys.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ConcertRepository concertRepository;

    public Ticket bookTicket(Long concertId, String buyerName) {
        Concert concert = concertRepository.findById(concertId)
                .orElseThrow(() -> new RuntimeException("Concert not found"));

        if (concert.getAvailableTickets() <= 0) {
            throw new RuntimeException("No tickets available");
        }

        concert.setAvailableTickets(concert.getAvailableTickets() - 1);
        concertRepository.save(concert);

        Ticket ticket = new Ticket();
        ticket.setBuyerName(buyerName);
        ticket.setConcert(concert);
        return ticketRepository.save(ticket);
    }

    public Optional<Ticket> getTicket(Long id) {
        return ticketRepository.findById(id);
    }
}
