package bookingsys.concertttbookingsys.controller;

import bookingsys.concertttbookingsys.entity.Ticket;
import bookingsys.concertttbookingsys.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestParam Long concertId, @RequestParam String buyerName) {
        return ResponseEntity.ok(ticketService.bookTicket(concertId, buyerName));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable Long id) {
        return ticketService.getTicket(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
