package eg.gov.iti.jets.persistence.entity.aws;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "security_groups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SecurityGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
    @Column(name = "security_group_id",unique = true,nullable = false)
    private String securityGroupId;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "vpc",nullable = false)
    private String vpcId;

    @OneToMany(fetch = FetchType.EAGER , cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "security_group_id")
    private Set<InboundRule> inboundRules;
    @OneToMany(fetch = FetchType.EAGER , cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "security_group_id")
    private Set<OutboundRule> outboundRules;
}
