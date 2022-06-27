package eg.gov.iti.jets;

import eg.gov.iti.jets.persistence.dao.*;
import eg.gov.iti.jets.persistence.entity.*;
import eg.gov.iti.jets.persistence.entity.aws.*;
import eg.gov.iti.jets.persistence.entity.enums.BranchStatus;
import eg.gov.iti.jets.persistence.entity.enums.PrivilegeName;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class App {
    public static void main( String[] args ) {

        SpringApplication.run( App.class, args );
    }

    public static void addUserWithAllPriv( RoleDao roleDao, PrivilegeDao privilegeDao, UserDao userDao ) {
        if ( roleDao.findRoleByName( "ALL" ).isEmpty() ) {
            Role allRole = roleDao.save( new Role( null, "ALL",
                    List.of( privilegeDao.findByName( PrivilegeName.CREATE_TERMINATE_ASSIGN_INSTANCE ).get(),
                            privilegeDao.findByName( PrivilegeName.START_STOP_VIEW_INSTANCE ).get(),
                            privilegeDao.findByName( PrivilegeName.MANAGE_TEMPLATES ).get(),
                            privilegeDao.findByName( PrivilegeName.MANAGE_STUDENTS ).get(),
                            privilegeDao.findByName( PrivilegeName.MANAGE_STAFF ).get(),
                            privilegeDao.findByName( PrivilegeName.MANAGE_ROLES ).get(),
                            privilegeDao.findByName( PrivilegeName.MANAGE_TRAINING_PROGRAMS ).get(),
                            privilegeDao.findByName( PrivilegeName.MANAGE_INTAKES ).get(),
                            privilegeDao.findByName( PrivilegeName.MANAGE_TRACKS ).get(),
                            privilegeDao.findByName( PrivilegeName.MANAGE_BRANCHES ).get(),
                            privilegeDao.findByName( PrivilegeName.MANAGE_PROFILE ).get(),
                            privilegeDao.findByName( PrivilegeName.VIEW_TEMPLATES ).get(),
                            privilegeDao.findByName( PrivilegeName.VIEW_STUDENTS ).get(),
                            privilegeDao.findByName( PrivilegeName.VIEW_USER ).get(),
                            privilegeDao.findByName( PrivilegeName.VIEW_INSTRUCTOR ).get(),
                            privilegeDao.findByName( PrivilegeName.VIEW_ROLES ).get(),
                            privilegeDao.findByName( PrivilegeName.VIEW_PRIVILEGES ).get(),
                            privilegeDao.findByName( PrivilegeName.VIEW_BRANCHES ).get(),
                            privilegeDao.findByName( PrivilegeName.VIEW_TRAINING_PROGRAMS ).get(),
                            privilegeDao.findByName( PrivilegeName.VIEW_INTAKES ).get(),
                            privilegeDao.findByName( PrivilegeName.VIEW_TRACKS ).get(),
                            privilegeDao.findByName( PrivilegeName.VIEW_INSTANCE_LOGS ).get(),
                            privilegeDao.findByName( PrivilegeName.VIEW_STAFF ).get(),
                            privilegeDao.findByName( PrivilegeName.VIEW_STATISTICS ).get()
                    ) ) );
            allRole = roleDao.save( allRole );

            User all = userDao.save( new User( null, "all", "all", "all", allRole, null, null, null, null, null, null ) );

        }
    }

    @Bean
    CommandLineRunner commandLineRunner( IntakeDao intakeDao, TrackDao trackDao, TrainingProgramDao trainingProgramDao, BranchDao branchDao, PrivilegeDao privilegeDao, SecurityGroupDao securityGroupDao, RoleDao roleDao, UserDao userDao, KeyPairDao keyPairDao, InstanceDao instanceDao, AmiDao amiDao, TemplateConfigurationDao templateConfigurationDao ) {

        return ars -> {
            if ( roleDao.findAllByExample( new Role( null, "STUDENT", null ) ).isEmpty() ) {

                // rules and privileges for all branches

                Privilege CREATE_TERMINATE_ASSIGN_INSTANCE = new Privilege( null, PrivilegeName.CREATE_TERMINATE_ASSIGN_INSTANCE, null );
                Privilege START_STOP_VIEW_INSTANCE = new Privilege( null, PrivilegeName.START_STOP_VIEW_INSTANCE, null );
                Privilege MANAGE_TEMPLATES = new Privilege( null, PrivilegeName.MANAGE_TEMPLATES, null );
                Privilege MANAGE_STUDENTS = new Privilege( null, PrivilegeName.MANAGE_STUDENTS, null );
                Privilege MANAGE_STAFF = new Privilege( null, PrivilegeName.MANAGE_STAFF, null );
                Privilege MANAGE_ROLES = new Privilege( null, PrivilegeName.MANAGE_ROLES, null );
                Privilege MANAGE_TRAINING_PROGRAMS = new Privilege( null, PrivilegeName.MANAGE_TRAINING_PROGRAMS, null );
                Privilege MANAGE_INTAKES = new Privilege( null, PrivilegeName.MANAGE_INTAKES, null );
                Privilege MANAGE_TRACKS = new Privilege( null, PrivilegeName.MANAGE_TRACKS, null );
                Privilege MANAGE_BRANCHES = new Privilege( null, PrivilegeName.MANAGE_BRANCHES, null );
                Privilege MANAGE_PROFILE = new Privilege( null, PrivilegeName.MANAGE_PROFILE, null );
                Privilege VIEW_TEMPLATES = new Privilege( null, PrivilegeName.VIEW_TEMPLATES, null );
                Privilege VIEW_STUDENTS = new Privilege( null, PrivilegeName.VIEW_STUDENTS, null );
                Privilege VIEW_USER = new Privilege( null, PrivilegeName.VIEW_USER, null );
                Privilege VIEW_INSTRUCTOR = new Privilege( null, PrivilegeName.VIEW_INSTRUCTOR, null );
                Privilege VIEW_ROLE = new Privilege( null, PrivilegeName.VIEW_ROLES, null );
                Privilege VIEW_STAFF = new Privilege( null, PrivilegeName.VIEW_STAFF, null );
                Privilege VIEW_PRIVILEGES = new Privilege( null, PrivilegeName.VIEW_PRIVILEGES, null );
                Privilege VIEW_BRANCHES = new Privilege( null, PrivilegeName.VIEW_BRANCHES, null );
                Privilege VIEW_TRAINING_PROGRAMS = new Privilege( null, PrivilegeName.VIEW_TRAINING_PROGRAMS, null );
                Privilege VIEW_INTAKES = new Privilege( null, PrivilegeName.VIEW_INTAKES, null );
                Privilege VIEW_TRACKS = new Privilege( null, PrivilegeName.VIEW_TRACKS, null );
                Privilege VIEW_INSTANCE_LOGS = new Privilege( null, PrivilegeName.VIEW_INSTANCE_LOGS, null );
                Privilege VIEW_STATISTICS = new Privilege( null, PrivilegeName.VIEW_STATISTICS, null );


                CREATE_TERMINATE_ASSIGN_INSTANCE = privilegeDao.save( CREATE_TERMINATE_ASSIGN_INSTANCE );
                START_STOP_VIEW_INSTANCE = privilegeDao.save( START_STOP_VIEW_INSTANCE );
                MANAGE_TEMPLATES = privilegeDao.save( MANAGE_TEMPLATES );
                MANAGE_STUDENTS = privilegeDao.save( MANAGE_STUDENTS );
                MANAGE_STAFF = privilegeDao.save( MANAGE_STAFF );
                MANAGE_ROLES = privilegeDao.save( MANAGE_ROLES );
                MANAGE_TRAINING_PROGRAMS = privilegeDao.save( MANAGE_TRAINING_PROGRAMS );
                MANAGE_INTAKES = privilegeDao.save( MANAGE_INTAKES );
                MANAGE_TRACKS = privilegeDao.save( MANAGE_TRACKS );
                MANAGE_BRANCHES = privilegeDao.save( MANAGE_BRANCHES );
                MANAGE_PROFILE = privilegeDao.save( MANAGE_PROFILE );
                VIEW_TEMPLATES = privilegeDao.save( VIEW_TEMPLATES );
                VIEW_STUDENTS = privilegeDao.save( VIEW_STUDENTS );
                VIEW_USER = privilegeDao.save( VIEW_USER );
                VIEW_INSTRUCTOR = privilegeDao.save( VIEW_INSTRUCTOR );
                VIEW_ROLE = privilegeDao.save( VIEW_ROLE );
                VIEW_STAFF = privilegeDao.save( VIEW_STAFF );
                VIEW_PRIVILEGES = privilegeDao.save( VIEW_PRIVILEGES );
                VIEW_BRANCHES = privilegeDao.save( VIEW_BRANCHES );
                VIEW_TRAINING_PROGRAMS = privilegeDao.save( VIEW_TRAINING_PROGRAMS );
                VIEW_INTAKES = privilegeDao.save( VIEW_INTAKES );
                VIEW_TRACKS = privilegeDao.save( VIEW_TRACKS );
                VIEW_INSTANCE_LOGS = privilegeDao.save( VIEW_INSTANCE_LOGS );
                VIEW_STATISTICS = privilegeDao.save( VIEW_STATISTICS );

                Role studentRole = roleDao.save( new Role( null, "STUDENT", List.of( START_STOP_VIEW_INSTANCE , MANAGE_PROFILE) ) );
                Role supervisorRole = roleDao.save( new Role( null, "TRACK_SUPERVISOR", List.of( CREATE_TERMINATE_ASSIGN_INSTANCE , START_STOP_VIEW_INSTANCE , MANAGE_TEMPLATES , VIEW_TEMPLATES ,VIEW_STUDENTS ,VIEW_INSTRUCTOR  ,VIEW_BRANCHES ,VIEW_TRAINING_PROGRAMS , VIEW_INTAKES ,VIEW_TRACKS, MANAGE_PROFILE) ) );
                Role instructorRole = roleDao.save( new Role( null, "INSTRUCTOR", List.of( CREATE_TERMINATE_ASSIGN_INSTANCE , START_STOP_VIEW_INSTANCE , VIEW_TEMPLATES  , VIEW_STUDENTS ,VIEW_BRANCHES ,VIEW_TRAINING_PROGRAMS , VIEW_INTAKES ,VIEW_TRACKS , MANAGE_PROFILE) ) );
                Role trainingMangerRole = roleDao.save( new Role( null, "TRAINING_MANAGER", List.of( MANAGE_STUDENTS  , MANAGE_TRACKS ,VIEW_BRANCHES ,VIEW_TRAINING_PROGRAMS , VIEW_INTAKES ,VIEW_TRACKS , MANAGE_PROFILE ) ) );
                Role superAdminRole = roleDao.save( new Role( null, "SUPER_ADMIN", List.of( MANAGE_STUDENTS , MANAGE_STAFF , MANAGE_ROLES, MANAGE_TRACKS , MANAGE_TRAINING_PROGRAMS , MANAGE_INTAKES ,MANAGE_BRANCHES , VIEW_USER ,VIEW_ROLE , VIEW_PRIVILEGES ,VIEW_BRANCHES ,VIEW_TRAINING_PROGRAMS , VIEW_INTAKES ,VIEW_TRACKS , VIEW_INSTANCE_LOGS ,  VIEW_STAFF , MANAGE_PROFILE
                , VIEW_STATISTICS) ) );



                DummyData.populateStaticDataForSmartBranch( instructorRole, trainingMangerRole, supervisorRole, studentRole, superAdminRole, intakeDao, trackDao, trainingProgramDao, branchDao, privilegeDao, securityGroupDao, roleDao, userDao, keyPairDao, instanceDao, amiDao, templateConfigurationDao );
                DummyData.populateStaticDataForIsmailiaBranch( instructorRole, trainingMangerRole, supervisorRole, studentRole, superAdminRole, intakeDao, trackDao, trainingProgramDao, branchDao, privilegeDao, securityGroupDao, roleDao, userDao, keyPairDao, instanceDao, amiDao, templateConfigurationDao );
                App.addUserWithAllPriv( roleDao, privilegeDao, userDao );
            }
            templateConfigurationDao.findAllByInstructor( "supervisor", TemplateConfiguration.class ).forEach( t -> System.out.println( t.getAmiId() ) );
            System.out.println( "------------" );
            for ( User user : userDao.getUserByBranchIdAndRoleName( 1, "INSTRUCTOR" ) ) {
                System.out.println( user.getUsername() );
            }

            userDao.getAllByTrackAndRole( 1, "STUDENT", User.class ).forEach( u -> System.out.println( u.getUsername() ) );
            System.out.println( "-------------------------" );
            instanceDao.findUserGrantedInstances( 1 ).forEach( i -> System.out.println( i.getInstanceId() ) );
            instanceDao.findFollowersUsersGrantedInstances( 1 ).forEach( i -> System.out.println( i.getInstanceId() ) );

            List<Branch> branches = branchDao.findAllByExample( new Branch( null, BranchStatus.ACTIVE, "smart", null, null, null ) );
            branches.forEach( b -> System.out.println( b.getName() ) );
            System.out.println( "Finished Inserting" );
            System.out.println( "all users students in java track" );
            Optional<Track> javaTrack = trackDao.findById( 1 );
            Optional<Role> studentRole = roleDao.findById( 3 );
            List<User> users = userDao.findAllUsersByTrackAndRole( javaTrack.get(), studentRole.get() );
            users.forEach( b -> System.out.println( b.getEmail() ) );
            System.out.println( "all users in track" );
            List<User> allUsers = userDao.findAllUsersByTrack( javaTrack.get() );
            allUsers.forEach( b -> System.out.println( b.getEmail() ) );
            studentRole = roleDao.findById( 2 );
            users = userDao.findAllUsersByRole( studentRole.get() );
            users.forEach( b -> System.out.println( b.getEmail() ) );
            Optional<User> userInstructor = userDao.findById( 5 );
            users = userDao.findAllFollowers( userInstructor.get() );
            users.forEach( b -> System.out.println( b.getEmail() ) );

            System.out.println(branchDao.countAll());
            System.out.println(instanceDao.countAllByState("asd"));
            System.out.println(instanceDao.countAllByStateNot("asd"));
            System.out.println(userDao.countAll());

        };
    }

}

class DummyData {

    public static void populateStaticDataForSmartBranch( Role instructorRole,
                                                         Role trainingMangerRole,
                                                         Role supervisorRole,
                                                         Role studentRole,
                                                         Role superAdminRole,
                                                         IntakeDao intakeDao,
                                                         TrackDao trackDao,
                                                         TrainingProgramDao trainingProgramDao,
                                                         BranchDao branchDao,
                                                         PrivilegeDao privilegeDao,
                                                         SecurityGroupDao securityGroupDao,
                                                         RoleDao roleDao, UserDao userDao,
                                                         KeyPairDao keyPairDao,
                                                         InstanceDao instanceDao,
                                                         AmiDao amiDao,
                                                         TemplateConfigurationDao templateConfigurationDao ) {


        User superAdminUser = userDao.save( new User( null, "superAdmin", "superAdmin@gmail.com", "superAdmin", superAdminRole, null, null, null, null, null, null ) );

        User trainingManagerUser = userDao.save( new User( null, "trainingManager", "trainingManager@gmail.com", "trainingManager", trainingMangerRole, null, null, null, superAdminUser, null, null ) );

        User supervisorUser = userDao.save( new User( null, "supervisor", "supervisor@gmail.com", "supervisor", supervisorRole, null, null, null, trainingManagerUser, null, null ) );

        User instructorUserForJava = userDao.save( new User( null, "instructorJava", "instructorJava@gmail.com", "instructorJava", instructorRole, null, null, null, supervisorUser, null, null ) );

        User instructorUserForPHP = userDao.save( new User( null, "instructorPHP", "instructorPHP@gmail.com", "instructorPHP", instructorRole, null, null, null, supervisorUser, null, null ) );

        // Java
        User studentUser = userDao.save( new User( null, "hend", "hend@gmail.com", "hend", studentRole, null, null, null, instructorUserForJava, null, null ) );
        User studentUser1 = userDao.save( new User( null, "amira", "amira@gmail.com", "amira", studentRole, null, null, null, instructorUserForJava, null, null ) );
        User studentUser2 = userDao.save( new User( null, "hafsa", "hafsa@gmail.com", "hafsa", studentRole, null, null, null, instructorUserForJava, null, null ) );

        // PHP
        User studentUser3 = userDao.save( new User( null, "ahmed", "ahmed@gmail.com", "ahmed", studentRole, null, null, null, instructorUserForPHP, null, null ) );
        User studentUser4 = userDao.save( new User( null, "osama", "osama@gmail.com", "osama", studentRole, null, null, null, instructorUserForPHP, null, null ) );
        User studentUser5 = userDao.save( new User( null, "mina", "mina@gmail.com", "mina", studentRole, null, null, null, instructorUserForPHP, null, null ) );

        Branch smartBranch = new Branch( null, BranchStatus.ACTIVE, "Smart Village", "6th of October", superAdminUser, null );
        smartBranch = branchDao.save( smartBranch );

        TrainingProgram nineMonthTrainingProgram = new TrainingProgram( null, "9 Month Program - Smart Village", smartBranch, null );
        nineMonthTrainingProgram = trainingProgramDao.save( nineMonthTrainingProgram );
        TrainingProgram threeMonthTrainingProgram = new TrainingProgram( null, "3 Month Program - Smart Village", smartBranch, null );
        threeMonthTrainingProgram = trainingProgramDao.save( threeMonthTrainingProgram );


        Intake intake42 = new Intake( null, "Intake 42 - Smart Village", nineMonthTrainingProgram, null, "The 42nd intake of the PTP." );
        intake42 = intakeDao.save( intake42 );
        Intake intakeFirstQuarter42 = new Intake( null, "Quarter 1 2022 - Smart Village", threeMonthTrainingProgram, null, "Quarter 1 2022 3 month program intake." );
        intakeFirstQuarter42 = intakeDao.save( intakeFirstQuarter42 );


        Track javaTrack = new Track( null, "Java", intake42, List.of( studentUser, studentUser1, studentUser2, instructorUserForJava, trainingManagerUser, supervisorUser ) );
        trackDao.save( javaTrack );

        Track phpTrack = new Track( null, "PHP", intakeFirstQuarter42, List.of( studentUser3, studentUser4, studentUser5, instructorUserForPHP, trainingManagerUser, supervisorUser ) );
        trackDao.save( phpTrack );
    }

    public static void populateStaticDataForIsmailiaBranch( Role instructorRole,
                                                            Role trainingMangerRole,
                                                            Role supervisorRole,
                                                            Role studentRole,
                                                            Role superAdminRole,
                                                            IntakeDao intakeDao,
                                                            TrackDao trackDao,
                                                            TrainingProgramDao trainingProgramDao,
                                                            BranchDao branchDao,
                                                            PrivilegeDao privilegeDao,
                                                            SecurityGroupDao securityGroupDao,
                                                            RoleDao roleDao, UserDao userDao,
                                                            KeyPairDao keyPairDao, InstanceDao instanceDao,
                                                            AmiDao amiDao,
                                                            TemplateConfigurationDao templateConfigurationDao ) {

        User IsmailiaBranchMangerUser = userDao.save( new User( null, "ismailiaBranchMangerUser", "IsmailiaBranchMangerUser@iti.com", "ismailiaBranchMangerUser", superAdminRole, null, null, null, null, null, null ) );

        User trainingMangerUser = userDao.save( new User( null, "trainingManger1", "trainingManger1@gmail.com", "trainingManger1", trainingMangerRole, null, null, null, IsmailiaBranchMangerUser, null, null ) );

        User superVisorUser = userDao.save( new User( null, "supervisor1", "supervisor1@gmail.com", "supervisor1", supervisorRole, null, null, null, trainingMangerUser, null, null ) );

        User instructorUserForQA = userDao.save( new User( null, "instructorQA", "instructorQA@gmail.com", "instructorQA", instructorRole, null, null, null, superVisorUser, null, null ) );

        User instructorUserForTesting = userDao.save( new User( null, "instructorTesting", "instructorTesting@gmail.com", "instructorTesting ", instructorRole, null, null, null, superVisorUser, null, null ) );


        User instructorUserForMobileApplication = userDao.save( new User( null, "instructorMobileApplication", "instructorMobileApplication@gmail.com", "instructorMobileApplication ", instructorRole, null, null, null, superVisorUser, null, null ) );

        User studentUser6 = userDao.save( new User( null, "student6", "student6@gmail.com", "student6", studentRole, null, null, null, instructorUserForQA, null, null ) );
        User studentUser7 = userDao.save( new User( null, "student7", "student7@gmail.com", "student7", studentRole, null, null, null, instructorUserForQA, null, null ) );
        User studentUser8 = userDao.save( new User( null, "student8", "student8@gmail.com", "student8", studentRole, null, null, null, instructorUserForTesting, null, null ) );
        User studentUser9 = userDao.save( new User( null, "student9", "student9@gmail.com", "student9", studentRole, null, null, null, instructorUserForTesting, null, null ) );
        User studentUser10 = userDao.save( new User( null, "student10", "student10@gmail.com", "student10", studentRole, null, null, null, instructorUserForMobileApplication, null, null ) );
        User studentUser11 = userDao.save( new User( null, "student11", "student11@gmail.com", "student11", studentRole, null, null, null, instructorUserForMobileApplication, null, null ) );


        Branch ismailiaBranch = new Branch( null, BranchStatus.ACTIVE, "Ismailia", "Ismailia", IsmailiaBranchMangerUser, null );
        ismailiaBranch = branchDao.save( ismailiaBranch );

        TrainingProgram nineMonthTrainingProgram = new TrainingProgram( null, "9 Month Program - Ismailia", ismailiaBranch, null );
        nineMonthTrainingProgram = trainingProgramDao.save( nineMonthTrainingProgram );


        Intake intake42 = new Intake( null, "Intake 42 - Ismailia", nineMonthTrainingProgram, null, "The 42nd intake of the 9 month PTP in Ismailia." );
        intake42 = intakeDao.save( intake42 );


        Track qualityControl = new Track( null, "QA", intake42, List.of( studentUser6, studentUser7 ) );
        qualityControl = trackDao.save( qualityControl );
        Track testing = new Track( null, "Testing", intake42, List.of( studentUser8, studentUser9 ) );
        testing = trackDao.save( testing );
        Track mobileApplication = new Track( null, "Mobile Application", intake42, List.of( studentUser10, studentUser11 ) );
        mobileApplication = trackDao.save( mobileApplication );

    }


    //Discarded for now
    public static void populateStaticDataForMenofiaBranch( Role studentRole,
                                                           Role branchMangerRole,
                                                           IntakeDao intakeDao,
                                                           TrackDao trackDao,
                                                           TrainingProgramDao trainingProgramDao,
                                                           BranchDao branchDao,
                                                           PrivilegeDao privilegeDao,
                                                           SecurityGroupDao securityGroupDao,
                                                           RoleDao roleDao,
                                                           UserDao userDao,
                                                           KeyPairDao keyPairDao,
                                                           InstanceDao instanceDao,
                                                           AmiDao amiDao,
                                                           TemplateConfigurationDao templateConfigurationDao ) {

        User menofiaBranchMangerUser = userDao.save( new User( null, "menofiaBranchMangerUser", "menofiaBranchMangerUser@iti.com", "menofiaBranchMangerUser", branchMangerRole, null, null, null, null, null, null ) );


        User studentUser12 = userDao.save( new User( null, "student12", "student12", "student12", studentRole, null, null, null, null, null, null ) );
        User studentUser13 = userDao.save( new User( null, "student13", "student13", "student13", studentRole, null, null, null, null, null, null ) );
        User studentUser14 = userDao.save( new User( null, "student14", "student14", "student14", studentRole, null, null, null, null, null, null ) );
        User studentUser15 = userDao.save( new User( null, "student15", "student15", "student15", studentRole, null, null, null, null, null, null ) );
        User studentUser16 = userDao.save( new User( null, "student16", "student16", "student16", studentRole, null, null, null, null, null, null ) );
        User studentUser17 = userDao.save( new User( null, "student17", "student17", "student17", studentRole, null, null, null, null, null, null ) );


        Branch menofiaBranch = new Branch( null, BranchStatus.ACTIVE, "menofia", "menofia", menofiaBranchMangerUser, null );
        menofiaBranch = branchDao.save( menofiaBranch );


        TrainingProgram threeMonthTrainingProgram = new TrainingProgram( null, "3 Month program", menofiaBranch, null );
        threeMonthTrainingProgram = trainingProgramDao.save( threeMonthTrainingProgram );
        TrainingProgram nineMonthTrainingProgram = new TrainingProgram( null, "9 Month program", menofiaBranch, null );
        nineMonthTrainingProgram = trainingProgramDao.save( nineMonthTrainingProgram );

        Intake intake42 = new Intake( null, "intake_42", nineMonthTrainingProgram, null, "intake 42 description" );
        intake42 = intakeDao.save( intake42 );
        Intake intakeFirstQuarter42 = new Intake( null, "intake_42_first_quarter", threeMonthTrainingProgram, null, "intake 42 description" );
        intakeFirstQuarter42 = intakeDao.save( intakeFirstQuarter42 );


        Track testing = new Track( null, "testing", intake42, List.of( studentUser14, studentUser15 ) );
        testing = trackDao.save( testing );
        Track mobileApplication = new Track( null, "Mobile Application", intakeFirstQuarter42, List.of( studentUser16, studentUser17 ) );
        mobileApplication = trackDao.save( mobileApplication );
        Track qualityControl = new Track( null, "QA", intake42, List.of( studentUser12, studentUser13 ) );
        qualityControl = trackDao.save( qualityControl );
    }


}