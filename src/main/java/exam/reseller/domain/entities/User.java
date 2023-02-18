package exam.reseller.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "user", targetEntity = Offer.class)
    @Fetch(FetchMode.JOIN)
    private List<Offer> offers;

    @OneToMany(mappedBy = "user", targetEntity = Offer.class)
    @Fetch(FetchMode.JOIN)
    private List<Offer> boughtOffers;
}
