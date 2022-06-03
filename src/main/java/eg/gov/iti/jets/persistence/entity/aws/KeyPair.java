package eg.gov.iti.jets.persistence.entity.aws;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @Column(name = "key_name")
    private String keyName;

    @Column(name = "key__material")
    private String keyMaterial;

}
