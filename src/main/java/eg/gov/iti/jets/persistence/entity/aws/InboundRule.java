package eg.gov.iti.jets.persistence.entity.aws;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "inbound_rule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InboundRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
    Integer fromPort;
    Integer toPort;
    String ipProtocol;
    String ipRangeAllowedIn; // mapped from IpRange
    String groupId;
}
