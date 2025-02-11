package bookingsys.concertttbookingsys.repository;

import bookingsys.concertttbookingsys.entity.Concert;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

@Registered
public interface ConcertRepository extends JpaRepository<Concert, Long> {
}
