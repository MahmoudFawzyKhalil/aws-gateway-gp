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
    Integer id;
    String imageId;
    String imageOwnerAlias;
    String architecture;
    String imageName;
    String description;
    String platform;
}
