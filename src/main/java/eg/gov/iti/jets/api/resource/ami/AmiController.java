package eg.gov.iti.jets.api.resource.ami;

import eg.gov.iti.jets.api.util.Mapper;
import eg.gov.iti.jets.persistence.entity.aws.Ami;
import eg.gov.iti.jets.service.management.AmiAws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;


// TODO: 6/17/2022 Ashraf dh ely by3mel el supervisor bs 
@RestController
@RequestMapping("/api/ami")
public class AmiController {
    private final
    AmiMapper amiMapper;
    private final
    AmiAws amiAws;

    public AmiController(  AmiAws amiAws, AmiMapper amiMapper ) {
        this.amiAws = amiAws;
        this.amiMapper = amiMapper;
    }

    @PostMapping()
    public ResponseEntity<?> getAmi(@Valid @RequestBody AmiRequest amiRequest){
        Ami ami = amiAws.describeAmi( amiRequest.getAmiId() );
        AmiViewResponse amiViewResponse = new AmiViewResponse(amiMapper.mapFromAmiToAmiResponse(ami));
//        AmiViewResponse amiViewResponse = ami.map( value -> new AmiViewResponse( amiMapper.mapFromAmiToAmiResponse( value ) ) ).orElse( new AmiViewResponse() );
        return new ResponseEntity<>(amiViewResponse, HttpStatus.OK);
    }

}
