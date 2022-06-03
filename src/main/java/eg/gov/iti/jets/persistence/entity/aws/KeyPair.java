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
    Integer id;
    @Column(name = "key_pair_id", unique = true, nullable = false)
    String keyPairId;

    @Column(name = "key_name", nullable = false)
    String keyName;

    @Column(name = "key__material")
    String keyMaterial;

}
