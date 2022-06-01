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
@Table(name = "branch")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @NotNull
    @Column(unique = true)
    private String name;

    private String address;
    @OneToOne
    @JoinColumn(name = "manger_id")
    private User manger;

    //one-to-many relationship with training programs
    @OneToMany(mappedBy = "branch")
    private List<TrainingProgram> trainingPrograms = new ArrayList<>();


}
