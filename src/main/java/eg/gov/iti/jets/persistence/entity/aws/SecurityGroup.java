package eg.gov.iti.jets.persistence.entity.aws;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "security_groups")
public class SecurityGroup {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "vpc")
    private String vpc;
}
