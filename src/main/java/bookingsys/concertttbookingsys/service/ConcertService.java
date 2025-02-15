package bookingsys.concertttbookingsys.service;

import bookingsys.concertttbookingsys.entity.Concert;
import bookingsys.concertttbookingsys.exception.ConcertNotFoundException;
import bookingsys.concertttbookingsys.repository.ConcertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConcertService {

    @Autowired
    private ConcertRepository concertRepository;

    public Concert createConcert(Concert concert){
        return concertRepository.save(concert);
    }

    public Optional<Concert> getConcert(Long id){
        return concertRepository.findById(id);
    }

    public List<Concert> getAllConcerts(){
        return concertRepository.findAll();
    }

    public Concert updateConcert(Long id, Concert concertDetails){
        return concertRepository.findById(id).map( concert -> {
            concert.setName(concertDetails.getName());
            concert.setLocation(concertDetails.getLocation());
            concert.setDate(concertDetails.getDate());
            concert.setAvailableTickets(concertDetails.getAvailableTickets());
            return concertRepository.save(concert);
        }).orElseThrow(() -> new ConcertNotFoundException("Could not find concert with id " + id));
    }

    public void deleteConcert(Long id){
        concertRepository.deleteById(id);
    }
}
