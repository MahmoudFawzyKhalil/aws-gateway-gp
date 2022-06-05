package eg.gov.iti.jets.persistence.entity.aws;


import eg.gov.iti.jets.persistence.entity.User;
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
    @Column(name = "image_ami", nullable = false)
    private String amiId;
    @Column(name = "subnet_id")
    private String subnetId;
    @Column(name = "instance_type", nullable = false)
    private String instanceType;

    @ManyToOne(fetch = FetchType.LAZY , cascade = {CascadeType.MERGE , CascadeType.PERSIST})
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator; // super admin or track supervisor

    @ManyToMany(cascade = {CascadeType.MERGE , CascadeType.PERSIST})
    @JoinTable(name = "template_configuration_instructors",
            joinColumns = @JoinColumn(name = "template_configuration_id"),
            inverseJoinColumns = @JoinColumn(name = "instructor_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"template_configuration_id", "instructor_id"}))
    private List<User> instructors;

    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(name = "template_security_groups", joinColumns = @JoinColumn(name = "template_id"),
            inverseJoinColumns = @JoinColumn(name = "security_group_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"template_id", "security_group_id"}))
    private List<SecurityGroup> securityGroups;
}