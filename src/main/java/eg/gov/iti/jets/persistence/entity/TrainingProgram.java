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
@Table(name = "training_program")
public class TrainingProgram {

    @Id
    private Integer id;

    @NotNull
    @Column(unique = true,nullable = false)
    private String name;

    @Column(nullable = false)
    private Double duration;

    // many-to-many relationship with branch
    @ManyToMany(mappedBy = "trainingPrograms")
    private List<Branch> branches = new ArrayList<>();


    // many-to-many relationship with track
    @ManyToMany
    @JoinTable(name = "training_program_track" ,
            joinColumns = @JoinColumn(name = "training_program_id") ,
            inverseJoinColumns = @JoinColumn(name = "track_id"))
    private List<Track> tracks = new ArrayList<>();





}
