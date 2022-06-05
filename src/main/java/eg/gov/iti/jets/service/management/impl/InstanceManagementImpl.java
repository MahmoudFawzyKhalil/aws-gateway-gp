package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.persistence.entity.aws.SecurityGroup;
import eg.gov.iti.jets.service.gateway.aws.ec2.AwsGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstanceManagementImpl {

    public AwsGateway awsGateway;

    public InstanceManagementImpl(AwsGateway awsGateway) {
        this.awsGateway = awsGateway;
    }

    public void createInstances(){}

    public void deleteInstances(){}

    public void getAllInstances(){}

    //filtering by branch
    public void getAllBranchInstances(String branch){}

    public void getAllTrackInstances(String Track){}


    public void assignInstance(){}

    public void createInstanceUsingTemplate(){}

    public void startInstance(String id){}

    public void stopInstance(String id){}

    public void getInstance(String id){}


    public List<SecurityGroup> describeSecurityGroupsForVpc(String vpcId){
        return awsGateway.describeSecurityGroupsForVpc(vpcId);
    }

    public List<String> getInstanceTypes(){
       return awsGateway.getInstanceTypes();
    }

}
