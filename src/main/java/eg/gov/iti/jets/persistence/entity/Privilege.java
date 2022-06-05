package eg.gov.iti.jets.persistence.entity;


import eg.gov.iti.jets.persistence.entity.enums.PrivilegeNames;
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
@Table(name = "privilege")
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "privilege_name", unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private PrivilegeNames name;

    @ManyToMany(mappedBy = "privileges")
    private List<Role> roles = new ArrayList<>();


}
