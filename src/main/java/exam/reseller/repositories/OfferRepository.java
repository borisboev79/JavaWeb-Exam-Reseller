package exam.reseller.repositories;

import exam.reseller.domain.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    Optional<List<Offer>> findAllBySeller_IdNot(Long id);
}
