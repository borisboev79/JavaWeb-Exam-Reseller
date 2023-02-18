package exam.reseller.repositories;

import exam.reseller.domain.entities.Condition;
import exam.reseller.domain.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    void deleteAllByUser_Id(Long id);

    Optional<List<Offer>> findAllByUser_Id(Long id);


    Optional<List<Offer>> findAllByUser_IdNot(Long id);
}
