package eg.gov.iti.jets.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserTrackIntakeId implements Serializable {
    @Column(name = "intake_id")
    Integer intakeId;
    @Column(name = "user_id")
    Integer userId;
    @Column(name = "track_id")
    Integer trackId;


}
