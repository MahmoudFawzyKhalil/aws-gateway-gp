package eg.gov.iti.jets.persistence.entity.aws;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "outbound_rule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OutboundRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    private String ipProtocol;
    private String ipRangeAllowedOut ;// mapped from IpRange
    private String groupId;
}
