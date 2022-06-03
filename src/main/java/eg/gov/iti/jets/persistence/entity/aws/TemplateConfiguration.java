package eg.gov.iti.jets.persistence.entity.aws;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "template_configuration")
public class TemplateConfiguration {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "key_pair_id")
    private KeyPair keyPair;
    @Column(name = "image_ami" ,nullable = false)
    private String amiId;
    @Column(name = "subnet_id")
    private String subnetId;
    @Column(name = "vpc_id")
    private String vpcId;
    @Column(name = "instance_type" ,nullable = false)
    private String instanceType;

    @ManyToMany
    @JoinTable(name = "template_security_groups" ,joinColumns = @JoinColumn(name = "template_id")
            ,inverseJoinColumns = @JoinColumn(name = "security_group_id")
            ,uniqueConstraints = @UniqueConstraint(columnNames = {"template_id","security_group_id"}))
    private List<SecurityGroup> securityGroups;

    // TODO template configurations should be visible only across a certain track because they are created by a supervisor - make a reference to the Track and/or Supervisor here
}
