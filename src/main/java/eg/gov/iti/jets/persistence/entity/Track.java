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


    // many-to-one relationship with intake
    @ManyToOne
    @JoinColumn(name = "intake_id",nullable = false)
    private Intake intake;


    //one-to-many relationship with user
    @OneToMany(mappedBy = "track")
    private List<User> users = new ArrayList<>();


}
