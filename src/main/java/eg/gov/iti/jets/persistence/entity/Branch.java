package eg.gov.iti.jets.persistence.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="branch")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(unique = true)
    private String name;

    private String address;


    //Joining Many Branches with many Training Programs
    //many-to-many relationship

    @ManyToMany
    @JoinTable(name = "branch_training_program" ,
            joinColumns = @JoinColumn(name = "branch_id") ,
            inverseJoinColumns = @JoinColumn(name = "training_program_id"))
    private List<TrainingProgram> trainingPrograms = new ArrayList<>();





    /*** TODO ***/
    // branch manager


    @ManyToMany
    @JoinTable(name = "branch_track",
    joinColumns = @JoinColumn(name = "branch_id"),
            inverseJoinColumns = @JoinColumn(name="track_id"))
    private List<Track> trackList = new ArrayList<>();



}
