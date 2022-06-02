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
    private Long id;
    @Column(name = "image_ami" ,nullable = false)
    private String amiId;
    @ManyToMany
    @JoinTable(name = "template_security_groups" ,joinColumns = @JoinColumn(name = "template_id")
            ,inverseJoinColumns = @JoinColumn(name = "security_group_id")
            ,uniqueConstraints = @UniqueConstraint(columnNames = {"template_id","security_group_id"}))
    private List<SecurityGroup> securityGroups;
    @Column(name = "instance_type" ,nullable = false)
    private String instanceType;
    //todo why we need this
    /*@Column(name = "number_of_instance")
    private Integer numberOfInstances=1;*/
}
