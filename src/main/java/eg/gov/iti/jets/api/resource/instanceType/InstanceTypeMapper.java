package eg.gov.iti.jets.api.resource.instanceType;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstanceTypeMapper {
    // InstanceType Mapping
    public InstanceTypeObjectResponse mapFromInstanceTypeToObjectResponse( List<String> types ) {
        List<InstanceTypeResponse> instanceTypeResponses = types.stream().map( InstanceTypeResponse::new ).collect( Collectors.toList() );
        return new InstanceTypeObjectResponse( instanceTypeResponses );
    }
}
