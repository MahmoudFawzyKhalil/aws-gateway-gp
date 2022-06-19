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

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "intake_id",nullable = false)
    private Intake intake;


    @ManyToMany
    @JoinTable(name = "user_tracks",
            joinColumns =  @JoinColumn(name = "track_id"),
            inverseJoinColumns =@JoinColumn(name = "user_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"track_id", "user_id"}))
//    @ManyToMany(mappedBy = "tracks" )
    private List<User> users = new ArrayList<>();


}
