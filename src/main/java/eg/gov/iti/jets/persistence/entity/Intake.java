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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(name = "intake_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "training_program_id",nullable = false)
    private TrainingProgram trainingProgram;

    @OneToMany(mappedBy = "intake" )
    private List<Track> tracks = new ArrayList<>();

    @Column(name = "intake_description")
    private String description;

}
