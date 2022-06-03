package eg.gov.iti.jets.persistence.entity.aws;

import eg.gov.iti.jets.persistence.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "instance")
public class Instance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
    @Column(name = "instance_name")
    private String name;
    @Column(name = "aws_instance_id",unique = true)
    private String instanceId;
    @Column(name = "instance_keymaterial")
    private String keyMaterial;
    @Column(name = "instance_publicip")
    private String publicIp;
    @Column(name = "instance_publicdns")
    private String publicDNS;
    @Column(name = "instance_password")
    private String decryptedPassword;
    @Column(name = "instance_username")
    private String username;
    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime;

    @Column(name = "instance_keypair",unique = true)
    private String keyPair;
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;
//    @ManyToMany(mappedBy = "instances")
//    private List<User> instanceUsers;
}
