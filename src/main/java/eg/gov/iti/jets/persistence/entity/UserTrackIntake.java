package eg.gov.iti.jets.persistence.entity;

import javax.persistence.*;

@Entity
public class UserTrackIntake {
    @EmbeddedId
    private UserTrackIntakeId id;

    @ManyToOne
    @JoinColumn(name = "track_id", nullable = false)
    @MapsId("trackId")
    private Track track;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @MapsId("userId")
    private User user;
    @ManyToOne
    @JoinColumn(name = "intake_id", nullable = false)
    @MapsId("intakeId")
    private Intake intake;

    /** todo**/
    /*
    * duration and start date
    *
    * */
}
