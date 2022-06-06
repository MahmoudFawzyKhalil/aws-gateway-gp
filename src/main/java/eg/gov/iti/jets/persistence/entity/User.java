package eg.gov.iti.jets.persistence.entity;


import eg.gov.iti.jets.persistence.entity.aws.Instance;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    @NotNull
    private String password;


    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToMany
    @JoinTable(name = "user_tracks",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "track_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "track_id"}))
    private List<Track> tracks;


    @OneToMany(mappedBy = "creator" ,cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private List<Instance> createdInstances = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_granted_instances",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "instance_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "instance_id"}))
    private List<Instance> grantedInstances = new ArrayList<>();

}