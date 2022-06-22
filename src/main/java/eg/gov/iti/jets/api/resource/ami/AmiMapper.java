package eg.gov.iti.jets.api.resource.ami;

import eg.gov.iti.jets.persistence.entity.aws.Ami;
import org.springframework.stereotype.Service;

@Service
public class AmiMapper {
    // Ami Mapping
    public AmiResponse mapFromAmiToAmiResponse( Ami ami ) {
        return new AmiResponse( ami.getImageId(), ami.getImageOwnerAlias(), ami.getArchitecture(), ami.getImageName(), ami.getDescription(), ami.getPlatform() );
    }
}
