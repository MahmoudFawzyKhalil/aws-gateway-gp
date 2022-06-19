package eg.gov.iti.jets.persistence.entity.aws;

import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.persistence.entity.enums.UserAction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "instance_logs")
public class InstanceLogs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name = "instance_id", nullable = false)
    Instance instance;
    @Column(name = "date_time", nullable = false)
    LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    UserAction action;

    @ManyToOne
    @JoinColumn(name = "action_maker_id", nullable = false)
    User actionMaker;
}
