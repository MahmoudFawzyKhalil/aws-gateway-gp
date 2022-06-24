package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.persistence.dao.InstanceDao;
import eg.gov.iti.jets.persistence.dao.UserDao;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.persistence.entity.aws.*;
import eg.gov.iti.jets.service.exception.ResourceAlreadyExistException;
import eg.gov.iti.jets.service.exception.ResourceNotFoundException;
import eg.gov.iti.jets.service.gateway.aws.ec2.AwsGateway;
import eg.gov.iti.jets.service.management.InstanceManagement;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class InstanceManagementImpl implements InstanceManagement {

    private final InstanceDao instanceDao;
    private final UserDao userDao;
    private final AwsGateway awsGateway;
    private static final long DEFAULT_INSTANCE_TTL = 60L;


    public InstanceManagementImpl( InstanceDao instanceDao, UserDao userDao, AwsGateway awsGateway ) {
        this.instanceDao = instanceDao;
        this.userDao = userDao;
        this.awsGateway = awsGateway;
    }

    @Override
    @Transactional
    public Instance createInstance( Instance instanceToCreate ) {
        setDefaultTimeToLive( instanceToCreate );

        Instance createdInstance = awsGateway.createInstance(
                instanceToCreate.getTemplateConfiguration(),
                instanceToCreate.getName(),
                instanceToCreate.getKeyPair(),
                instanceToCreate.getTimeToLiveInMinutes() );
        System.out.println( createdInstance.getInstanceId() );
        try {
            createdInstance.setInstanceUsers( instanceToCreate.getInstanceUsers() );
            createdInstance.setCreator( instanceToCreate.getCreator() );
            createdInstance.setTemplateConfiguration( instanceToCreate.getTemplateConfiguration() );
            createdInstance.setCreationDateTime( LocalDateTime.now() );
            return instanceDao.save( createdInstance );
        }catch (Exception e) {
            throw new ResourceAlreadyExistException("Instance with id " + instanceToCreate.getInstanceId() + ", is already exist!");
        }
    }

    private void setDefaultTimeToLive( Instance instanceToCreate ) {
        if ( instanceToCreate.getTimeToLiveInMinutes() == null ) {
            instanceToCreate.setTimeToLiveInMinutes( DEFAULT_INSTANCE_TTL );
        }
    }

    @Override
    public String startInstance( String instanceId ) {
        Instance example = new Instance();
        example.setInstanceId( instanceId );

        var result = instanceDao.findAllByExample( example );
        if ( result.isEmpty() )
            throw new ResourceNotFoundException( String.format( "No instance exists with the id [%s]", instanceId ) );

        Instance instance = result.get( 0 );
        String instanceState = awsGateway.startInstance( instance );
        instance.setState( instanceState );
        instanceDao.update( instance );
        return instanceState;
    }

    @Override
    public String stopInstance( String instanceId ) {
        Instance example = new Instance();
        example.setInstanceId( instanceId );

        var result = instanceDao.findAllByExample( example );
        if ( result.isEmpty() )
            throw new ResourceNotFoundException( String.format( "No instance exists with the id [%s]", instanceId ) );

        Instance instance = result.get( 0 );
        String instanceState = awsGateway.stopInstance( instance.getInstanceId() );
        instance.setState( instanceState );
        instanceDao.update( instance );
        return instanceState;
    }

    @Override
    public String deleteInstance( String instanceId ) {
        Instance example = new Instance();
        example.setInstanceId( instanceId );

        var result = instanceDao.findAllByExample( example );
        if ( result.isEmpty() )
            throw new ResourceNotFoundException( String.format( "No instance exists with the id [%s]", instanceId ) );

        Instance instance = result.get( 0 );
        String instanceState = awsGateway.terminateInstance( instance.getInstanceId() );
        instance.setState( "terminated" );
        instanceDao.update( instance );
        return instanceState;
    }

    @Override
    public Instance getInstanceDetails( String instanceId ) {
        Instance example = new Instance();
        example.setInstanceId( instanceId );
        List<Instance> result = instanceDao.findAllByExample( example );
        if ( result.isEmpty() )
            throw new ResourceNotFoundException( String.format( "No instance exists with the id [%s]", instanceId ) );
        Instance instance = result.get( 0 );
        awsGateway.updateInstanceInfoFromAws( instance );
        return instance;
    }

    @Override
    public List<Instance> getInstancesByUserId( Integer id ) {
        Optional<User> optionalUser = userDao.findById( id );

        if ( optionalUser.isPresent() ) {
            String roleName = optionalUser.get().getRole().getName();

            switch ( roleName ) {
                case "STUDENT":
                    return getStudentInstances( id );
                case "INSTRUCTOR":
                    return getInstructorInstance( id );
                case "TRACK_SUPERVISOR":
                    return getSupervisorInstance( id, optionalUser );

            }
        }
        return Collections.emptyList();
    }

    private List<Instance> getSupervisorInstance( Integer id, Optional<User> optionalUser ) {
        List<Instance> supervisorInstances = instanceDao.findUserGrantedInstances( id );
        List<Instance> instructorsSupervisorInstances = instanceDao.findFollowersUsersGrantedInstances( id );
        List<User> instructors = optionalUser.get().getFollowers();

        List<List<Instance>> studentsInstructorsInstances = instructors.stream().map( follower -> instanceDao.findFollowersUsersGrantedInstances( follower.getId() ) ).collect( Collectors.toList() );

        List<Instance> studentsInstructorsInstancesFlatten = studentsInstructorsInstances.stream().flatMap( Collection::stream ).collect( Collectors.toList() );
        List<Instance> allSupervisorInstance = Stream.concat( supervisorInstances.stream(),
                        instructorsSupervisorInstances.stream() )
                .collect( Collectors.toList() );
        List<Instance> all = Stream.concat( allSupervisorInstance.stream(),
                        studentsInstructorsInstancesFlatten.stream() )
                .collect( Collectors.toList() );
        all = all.stream().filter( i -> !i.getState().equals( "terminated" ) ).collect( Collectors.toList() );

        awsGateway.updateInstancesInfoFromAws( all );
        return all;
    }

    private List<Instance> getInstructorInstance( Integer id ) {
        List<Instance> instructorInstances = instanceDao.findUserGrantedInstances( id );
        List<Instance> studentsInstructorInstances = instanceDao.findFollowersUsersGrantedInstances( id );
        List<Instance> allInstructorInstance = Stream.concat( instructorInstances.stream(), studentsInstructorInstances.stream() )
                .collect( Collectors.toList() );
        allInstructorInstance = allInstructorInstance.stream().filter( i -> !i.getState().equals( "terminated" ) ).collect( Collectors.toList() );

        awsGateway.updateInstancesInfoFromAws( allInstructorInstance );
        return allInstructorInstance;
    }

    private List<Instance> getStudentInstances( Integer id ) {
        List<Instance> studentInstance = instanceDao.findUserGrantedInstances( id );

        studentInstance = studentInstance.stream().filter( i -> !i.getState().equals( "terminated" ) ).collect( Collectors.toList() );
        awsGateway.updateInstancesInfoFromAws( studentInstance );
        return studentInstance;
    }

}
