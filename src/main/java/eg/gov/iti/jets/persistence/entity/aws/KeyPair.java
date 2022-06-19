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

    @Column(name = "key_material" , columnDefinition = "TEXT")
    private String keyMaterial;

    @Column(name = "key_material_type")
    private String KeyMaterialType;

    @ManyToOne
    @JoinColumn(name = "creator_id",nullable = false)
    private User creator;

}
