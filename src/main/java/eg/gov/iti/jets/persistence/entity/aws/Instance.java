package eg.gov.iti.jets.persistence.entity.aws;

import eg.gov.iti.jets.persistence.entity.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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

//    @ManyToMany
//    @JoinTable(name = "user_granted_instances",
//            joinColumns = @JoinColumn(name = "instance_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"),
//            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "instance_id"}))
//
//    @OneToOne
//    @JoinColumn(name = "user_granted_id")

    @ManyToOne
    @JoinColumn(name = "user_granted_id")
    private User instanceUsers;

    @ManyToOne
    @JoinColumn(name = "template_configuration_id", nullable = false)
    TemplateConfiguration templateConfiguration;

    @Min( 5L )
    @NotNull
    private Long timeToLiveInMinutes; // This must be provided from the frontend

}
