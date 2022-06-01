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
    private Integer id;

    @Column(name = "intake_number")
    private Integer intakeNumber;


    // many-to-one relationship with branch
    @ManyToOne
    @JoinColumn(name = "training_program_id",nullable = false)
    private TrainingProgram trainingProgram;



    //one-to-many relationship with track
    @OneToMany(mappedBy = "intake")
    private List<Track> tracks = new ArrayList<>();




}
