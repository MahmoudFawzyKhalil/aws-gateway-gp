package eg.gov.iti.jets;

import eg.gov.iti.jets.persistence.dao.*;
import eg.gov.iti.jets.persistence.entity.*;
import eg.gov.iti.jets.persistence.entity.aws.*;
import eg.gov.iti.jets.persistence.entity.enums.PrivilegeName;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class App {
    public static void main(String[] args) {

        SpringApplication.run(App.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(IntakeDao intakeDao, TrackDao trackDao, TrainingProgramDao trainingProgramDao, BranchDao branchDao, PrivilegeDao privilegeDao, SecurityGroupDao securityGroupDao, RoleDao roleDao, UserDao userDao, KeyPairDao keyPairDao, InstanceDao instanceDao, AmiDao amiDao, TemplateConfigurationDao templateConfigurationDao) {

        return ars -> {
            if (roleDao.findAllByExample(new Role(null, "STUDENT", null)).isEmpty()) {

                Privilege privilege = new Privilege(null, PrivilegeName.TEST_PRIVILEGE_1, null);
                Privilege privilege2 = new Privilege(null, PrivilegeName.TEST_PRIVILEGE_2, null);
                privilege = privilegeDao.save(privilege);
                privilege2 = privilegeDao.save(privilege2);


                Role studentRole = roleDao.save(new Role(null, "STUDENT", List.of(privilege)));
                Role supervisorRole = roleDao.save(new Role(null, "TRACK_SUPERVISOR", List.of(privilege, privilege2)));
                Role instructorRole = roleDao.save(new Role(null, "INSTRUCTOR", List.of(privilege, privilege2)));
                Role trainingMangerRole = roleDao.save(new Role(null, "TRAINING_MANAGER", List.of(privilege, privilege2)));
                Role branchMangerRole = roleDao.save(new Role(null, "BRANCH_MANAGER", List.of(privilege, privilege2)));


                User superVisorUser = userDao.save(new User(null, "supervisor", "supervisor", "supervisor", supervisorRole, null, null, null));
                User instructorUser = userDao.save(new User(null, "instructor", "instructor", "instructor", instructorRole, null, null, null));
                User trainingMangerUser = userDao.save(new User(null, "trainingManger", "trainingManger", "trainingManger", trainingMangerRole, null, null, null));

                DummyData.populateStaticDataForSmartBranch(studentRole, branchMangerRole, intakeDao, trackDao, trainingProgramDao, branchDao, privilegeDao, securityGroupDao, roleDao, userDao, keyPairDao, instanceDao, amiDao, templateConfigurationDao);
                DummyData.populateStaticDataForIsmailiaBranch(studentRole, branchMangerRole, intakeDao, trackDao, trainingProgramDao, branchDao, privilegeDao, securityGroupDao, roleDao, userDao, keyPairDao, instanceDao, amiDao, templateConfigurationDao);
                DummyData.populateStaticDataForMenofiaBranch(studentRole, branchMangerRole, intakeDao, trackDao, trainingProgramDao, branchDao, privilegeDao, securityGroupDao, roleDao, userDao, keyPairDao, instanceDao, amiDao, templateConfigurationDao);


                var key = keyPairDao.save(new KeyPair(null, "keyPairId", "keyName", "keyMaterial", "keyMaterialType", superVisorUser));
                var ke2 = keyPairDao.save(new KeyPair(null, "keyPairId2", "keyName2", "keyMaterial2", "keyMaterialType2", superVisorUser));
                SecurityGroup securityGroup = new SecurityGroup(null, "secGroupId", "secGroup1", "descriptoon", "vpcId", null, null);
                securityGroupDao.save(securityGroup);
                TemplateConfiguration templateConfiguration = new TemplateConfiguration(null, "ami1", "subnetId", "instanceType", superVisorUser, null, List.of(securityGroup));
                TemplateConfiguration templateConfiguration2 = new TemplateConfiguration(null, "ami2", "subnetId2", "instanceType2", superVisorUser, null, List.of(securityGroup));
                templateConfigurationDao.save(templateConfiguration);
                templateConfigurationDao.save(templateConfiguration2);
                Instance instance = instanceDao.save(new Instance(null, "name", "amid", "instanceId", "state", "publicIp", "publicDnsName", "instanceType", "subnetId", "vpcId", "platform", "decryptedPassword", "userName", LocalDateTime.now(), key, superVisorUser, null, templateConfiguration));
                Instance instance2 = instanceDao.save(new Instance(null, "name2", "amid2", "instanceId2", "state2", "publicIp2", "publicDnsName2", "instanceType2", "subnetId2", "vpcId2", "platform2", "decryptedPassword2", "userName2", LocalDateTime.now(), key, superVisorUser, null, templateConfiguration2));

                instanceDao.save(instance);
                instanceDao.save(instance2);

                Ami ami1 = amiDao.save(new Ami(null, "imageId", "imagwOwnerAlias", "arch", "imageName", "description", "platform"));
                Ami ami2 = amiDao.save(new Ami(null, "imageId2", "imagwOwnerAlias2", "arch2", "imageName2", "description2", "platform2"));

            }
            List<Branch> branches = branchDao.findAllByExample(new Branch(null,"smart",null,null,null));
            branches.forEach(b-> System.out.println(b.getName()));
            System.out.println("Finished Inserting");

        };
    }

}

class DummyData {

    public static void populateStaticDataForSmartBranch(Role studentRole,
                                                        Role branchMangerRole,
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
                                                        TemplateConfigurationDao templateConfigurationDao) {

        User smartBranchMangerUser = userDao.save(new User(null, "smartBranchManger", "smartBranchManger", "smartBranchManger", branchMangerRole, null, null, null));


        User studentUser = userDao.save(new User(null, "student", "student", "student", studentRole, null, null, null));
        User studentUser1 = userDao.save(new User(null, "student1", "student1", "student1", studentRole, null, null, null));
        User studentUser2 = userDao.save(new User(null, "student2", "student2", "student2", studentRole, null, null, null));
        User studentUser3 = userDao.save(new User(null, "student3", "student3", "student3", studentRole, null, null, null));
        User studentUser4 = userDao.save(new User(null, "student4", "student4", "student4", studentRole, null, null, null));
        User studentUser5 = userDao.save(new User(null, "student5", "student5", "student5", studentRole, null, null, null));


        Branch smartBranch = new Branch(null, "Smart", "October", smartBranchMangerUser, null);
        smartBranch = branchDao.save(smartBranch);

        TrainingProgram nineMonthTrainingProgram = new TrainingProgram(null, "9 Month program", smartBranch, null);
        nineMonthTrainingProgram = trainingProgramDao.save(nineMonthTrainingProgram);
        TrainingProgram threeMonthTrainingProgram = new TrainingProgram(null, "3 Month program", smartBranch, null);
        threeMonthTrainingProgram = trainingProgramDao.save(threeMonthTrainingProgram);


        Intake intake42 = new Intake(null, "intake_42", nineMonthTrainingProgram, null, "intake 42 description");
        intake42 = intakeDao.save(intake42);
        Intake intakeFirstQuarter42 = new Intake(null, "intake_42_first_quarter", threeMonthTrainingProgram, null, "intake 42 description");
        intakeFirstQuarter42 = intakeDao.save(intakeFirstQuarter42);


        Track javaTrack = new Track(null, "Java", intake42, List.of(studentUser, studentUser1, studentUser2));
        javaTrack = trackDao.save(javaTrack);
        Track phpTrack = new Track(null, "PHP", intakeFirstQuarter42, List.of(studentUser3, studentUser4, studentUser5));
        phpTrack = trackDao.save(phpTrack);


    }

    public static void populateStaticDataForIsmailiaBranch(Role studentRole,
                                                           Role branchMangerRole,
                                                           IntakeDao intakeDao,
                                                           TrackDao trackDao,
                                                           TrainingProgramDao trainingProgramDao,
                                                           BranchDao branchDao,
                                                           PrivilegeDao privilegeDao,
                                                           SecurityGroupDao securityGroupDao,
                                                           RoleDao roleDao, UserDao userDao,
                                                           KeyPairDao keyPairDao, InstanceDao instanceDao,
                                                           AmiDao amiDao,
                                                           TemplateConfigurationDao templateConfigurationDao) {

        User IsmailiaBranchMangerUser = userDao.save(new User(null, "ismailiaBranchMangerUser", "IsmailiaBranchMangerUser@iti.com", "ismailiaBranchMangerUser", branchMangerRole, null, null, null));


        User studentUser6 = userDao.save(new User(null, "student6", "student6", "student6", studentRole, null, null, null));
        User studentUser7 = userDao.save(new User(null, "student7", "student7", "student7", studentRole, null, null, null));
        User studentUser8 = userDao.save(new User(null, "student8", "student8", "student8", studentRole, null, null, null));
        User studentUser9 = userDao.save(new User(null, "student9", "student9", "student9", studentRole, null, null, null));
        User studentUser10 = userDao.save(new User(null, "student10", "student10", "student10", studentRole, null, null, null));
        User studentUser11 = userDao.save(new User(null, "student11", "student11", "student11", studentRole, null, null, null));


        Branch ismailiaBranch = new Branch(null, "Ismailia", "Ismailia", IsmailiaBranchMangerUser, null);
        ismailiaBranch = branchDao.save(ismailiaBranch);

        TrainingProgram nineMonthTrainingProgram = new TrainingProgram(null, "9 Month program", ismailiaBranch, null);
        nineMonthTrainingProgram = trainingProgramDao.save(nineMonthTrainingProgram);


        Intake intake42 = new Intake(null, "intake_42", nineMonthTrainingProgram, null, "intake 42 description");
        intake42 = intakeDao.save(intake42);


        Track qualityControl = new Track(null, "QA", intake42, List.of(studentUser6, studentUser7));
        qualityControl = trackDao.save(qualityControl);
        Track testing = new Track(null, "testing", intake42, List.of(studentUser8, studentUser9));
        testing = trackDao.save(testing);
        Track mobileApplication = new Track(null, "Mobile Application", intake42, List.of(studentUser10, studentUser11));
        mobileApplication = trackDao.save(mobileApplication);

    }

    public static void populateStaticDataForMenofiaBranch(Role studentRole,
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
                                                          TemplateConfigurationDao templateConfigurationDao) {

        User menofiaBranchMangerUser = userDao.save(new User(null, "menofiaBranchMangerUser", "menofiaBranchMangerUser@iti.com", "menofiaBranchMangerUser", branchMangerRole, null, null, null));


        User studentUser12 = userDao.save(new User(null, "student12", "student12", "student12", studentRole, null, null, null));
        User studentUser13 = userDao.save(new User(null, "student13", "student13", "student13", studentRole, null, null, null));
        User studentUser14 = userDao.save(new User(null, "student14", "student14", "student14", studentRole, null, null, null));
        User studentUser15 = userDao.save(new User(null, "student15", "student15", "student15", studentRole, null, null, null));
        User studentUser16 = userDao.save(new User(null, "student16", "student16", "student16", studentRole, null, null, null));
        User studentUser17 = userDao.save(new User(null, "student17", "student17", "student17", studentRole, null, null, null));


        Branch menofiaBranch = new Branch(null, "menofia", "menofia", menofiaBranchMangerUser, null);
        menofiaBranch = branchDao.save(menofiaBranch);


        TrainingProgram threeMonthTrainingProgram = new TrainingProgram(null, "3 Month program", menofiaBranch, null);
        threeMonthTrainingProgram = trainingProgramDao.save(threeMonthTrainingProgram);
        TrainingProgram nineMonthTrainingProgram = new TrainingProgram(null, "9 Month program", menofiaBranch, null);
        nineMonthTrainingProgram = trainingProgramDao.save(nineMonthTrainingProgram);

        Intake intake42 = new Intake(null, "intake_42", nineMonthTrainingProgram, null, "intake 42 description");
        intake42 = intakeDao.save(intake42);
        Intake intakeFirstQuarter42 = new Intake(null, "intake_42_first_quarter", threeMonthTrainingProgram, null, "intake 42 description");
        intakeFirstQuarter42 = intakeDao.save(intakeFirstQuarter42);


        Track testing = new Track(null, "testing", intake42, List.of(studentUser14, studentUser15));
        testing = trackDao.save(testing);
        Track mobileApplication = new Track(null, "Mobile Application", intakeFirstQuarter42, List.of(studentUser16, studentUser17));
        mobileApplication = trackDao.save(mobileApplication);
        Track qualityControl = new Track(null, "QA", intake42, List.of(studentUser12, studentUser13));
        qualityControl = trackDao.save(qualityControl);
    }

}