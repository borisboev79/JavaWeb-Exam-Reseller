package exam.reseller.domain.models;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfferViewModel {
    private Long id;
    private String username;
    private String condition;
    private String description;
    private BigDecimal price;

}
