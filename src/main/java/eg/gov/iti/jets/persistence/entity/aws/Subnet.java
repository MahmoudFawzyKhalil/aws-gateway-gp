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
    String subnetId;
    String vpcId;
    String availabilityZone;
    String availabilityZoneId;
    Integer availableIpAddressCount;
    String cidrBlock;
    Boolean mapPublicIpOnLaunch;
}
