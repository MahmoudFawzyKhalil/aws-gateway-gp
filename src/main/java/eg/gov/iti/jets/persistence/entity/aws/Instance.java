package eg.gov.iti.jets.persistence.entity.aws;

import eg.gov.iti.jets.persistence.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "instance")
@ToString
public class Instance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
    @Column(name = "instance_name")
    private String name;
    @Column(name = "ami_id")
    String amiId;
    @Column(name = "aws_instance_id", unique = true)
    private String instanceId;
    @Column(name = "state")
    private String state;
    @Column(name = "instance_publicip")
    private String publicIp;
    @Column(name = "instance_public_dns_name")
    private String publicDnsName;
    @Column(name = "instance_type")
    private String instanceType;
    @Column(name = "subnet_id")
    private String subnetId;
    @Column(name = "vpc_id")
    private String vpcId;
    @Column(name = "platform")
    private String platform; // windows or linux
    @Column(name = "instance_password")
    private String decryptedPassword;
    @Column(name = "instance_username")
    private String username;
    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime;
    @OneToOne
    @JoinColumn(name = "key_pair_id")
    private KeyPair keyPair;
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;
    @ManyToMany(mappedBy = "grantedInstances")
    private List<User> instanceUsers;
    @ManyToMany
    @JoinTable(name = "instance_security_groups",
            joinColumns = @JoinColumn(name = "instance_id"),
            inverseJoinColumns = @JoinColumn(name = "security_group_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"instance_id", "security_group_id"}))
    private List<SecurityGroup> securityGroups;

    @ManyToOne
    @JoinColumn(name = "template_configuration_id", nullable = false)
    TemplateConfiguration templateConfiguration;
}
