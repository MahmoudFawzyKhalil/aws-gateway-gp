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
    private Integer fromPort;
    private Integer toPort;
    private String ipProtocol;
    private String ipRangeAllowedIn; // mapped from IpRange
    private String groupId;
}
