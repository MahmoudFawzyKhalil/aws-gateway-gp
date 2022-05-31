package eg.gov.iti.jets.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "intake")
public class Intake {


    @Id
    private Integer id;

    @Column(name = "intake_number")
    private Integer intakeNumber;

    @ManyToMany(mappedBy = "intakes")
    private List<Track> tracks = new ArrayList<>();


}
