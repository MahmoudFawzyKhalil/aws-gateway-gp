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


    @ManyToMany(mappedBy = "tracks")
    private List<TrainingProgram> trainingPrograms = new ArrayList<>();


    @OneToMany(mappedBy = "track")
    List<UserTrackIntake> userTrackIntakes = new ArrayList<>();


    /*** TODO ***/
    //Track supervisor
    //Students
    //TA


}
