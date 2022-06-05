package eg.gov.iti.jets.persistence.entity.aws;

import eg.gov.iti.jets.persistence.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "key_pair")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class KeyPair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "key_pair_id")
    private String keyPairId;

    @Column(name = "key_name" , unique = true)
    private String keyName;

    @Column(name = "key_material")
    private String keyMaterial;

    @Column(name = "key_material_type")
    private String KeyMaterialType;

    // TODO people should be able to the keys they created, add a User creator field and relationship

    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(name = "user_keypair",
            joinColumns = @JoinColumn(name = "keypair_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"keypair_id", "user_id"}))
    private List<User> usersCreator =new ArrayList<>();
}
