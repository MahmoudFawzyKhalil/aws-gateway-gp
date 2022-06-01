package eg.gov.iti.jets.persistence.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "configuration")
public class Configuration {

    @Id
    private Long id;

    @Column(name = "image_ami")
    private String amiId;
    @Column(name = "instance_type")
    private String instanceType;
    @Column(name = "sequrity_croup")
    private String securityGroups;

}
