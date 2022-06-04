package eg.gov.iti.jets.persistence.entity.aws;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subnet {
    private String subnetId;
    private String vpcId;
    private String availabilityZone;
    private String availabilityZoneId;
    private Integer availableIpAddressCount;
    private String cidrBlock;
    private Boolean mapPublicIpOnLaunch;
}
