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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(unique = true,nullable = false)
    private String name;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "branch_id",nullable = false)
    private  Branch branch;


    @OneToMany(mappedBy = "trainingProgram" ,cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private List<Intake> intakes = new ArrayList<>();

}
