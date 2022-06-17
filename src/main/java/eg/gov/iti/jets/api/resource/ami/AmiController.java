package eg.gov.iti.jets.api.resource.ami;

import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.aws.Ami;
import eg.gov.iti.jets.service.management.AmiAws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/api/ami")
public class AmiController {

    final
    Mapper mapper;
    final
    AmiAws amiAws;

    public AmiController( Mapper mapper, AmiAws amiAws ) {
        this.mapper = mapper;
        this.amiAws = amiAws;
    }

    @PostMapping()
    public AmiViewResponse getAmi( @RequestBody AmiRequest amiRequest){
        Optional<Ami> ami = amiAws.describeAmi( amiRequest.getAmiId() );
        return ami.map( value -> new AmiViewResponse( true, mapper.mapFromAmiToAmiResponse( value ) ) ).orElseGet( () -> new AmiViewResponse( false, null ) );
    }

}
