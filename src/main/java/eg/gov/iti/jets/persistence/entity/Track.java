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
@Table(name = "track")
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "track_name")
    private String trackName;


    // many-to-many relationship with training program
    @ManyToMany(mappedBy = "tracks")
    private List<TrainingProgram> trainingPrograms = new ArrayList<>();


    // many-to-many relationship with intake
    @ManyToMany
    @JoinTable(name = "track_intake" ,
            joinColumns = @JoinColumn(name = "track_id") ,
            inverseJoinColumns = @JoinColumn(name = "intake_id"))
    private List<Intake> intakes = new ArrayList<>();



    /*** TODO ***/
    //Track supervisor
    //Students
    //TA










}
