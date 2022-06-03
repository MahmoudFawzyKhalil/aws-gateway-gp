package eg.gov.iti.jets.persistence.entity.aws;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "security_groups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SecurityGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "vpc")
    private String vpcId;

    @OneToMany
    @JoinColumn(name = "security_group_id")
    List<InboundRule> inboundRules;
    @OneToMany
    @JoinColumn(name = "security_group_id")
    List<OutboundRule> outboundRules;
}
