package eg.gov.iti.jets.persistence.entity.aws;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ami")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ami {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String imageId;
    private String imageOwnerAlias;
    private String architecture;
    private String imageName;
    private String description;
    private String platform;
}
