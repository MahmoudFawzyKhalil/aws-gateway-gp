package eg.gov.iti.jets.api.resource.subnet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubnetResponse {

    private String subnetId;
    private String vpcId;
    private String availabilityZone;
    private String availabilityZoneId;
    private Integer availableIpAddressCount;
    private String cidrBlock;
    private Boolean mapPublicIpOnLaunch;
}
