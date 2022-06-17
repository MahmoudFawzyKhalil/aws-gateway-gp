package eg.gov.iti.jets.api.resource.ami;

import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.aws.Ami;
import eg.gov.iti.jets.service.management.AmiAws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


// TODO: 6/17/2022 Ashraf dh ely by3mel el supervisor bs 
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
    public ResponseEntity<?> getAmi( @RequestBody AmiRequest amiRequest){
        // TODO: 6/17/2022 if bad request  
        Optional<Ami> ami = amiAws.describeAmi( amiRequest.getAmiId() );
        // TODO: 6/17/2022 Error here if Optional return null 
        AmiViewResponse amiViewResponse = ami.map( value -> new AmiViewResponse( mapper.mapFromAmiToAmiResponse( value ) ) ).orElse( new AmiViewResponse() );
        return new ResponseEntity<>(amiViewResponse, HttpStatus.OK);
    }

}
