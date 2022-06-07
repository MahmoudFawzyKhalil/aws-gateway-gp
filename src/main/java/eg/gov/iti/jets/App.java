package eg.gov.iti.jets;

import eg.gov.iti.jets.persistence.dao.*;
import eg.gov.iti.jets.persistence.dao.impls.AmiDaoIml;
import eg.gov.iti.jets.persistence.entity.Privilege;
import eg.gov.iti.jets.persistence.entity.Role;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.persistence.entity.aws.*;
import eg.gov.iti.jets.persistence.entity.enums.PrivilegeName;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class App {
    public static void main(String[] args) {

        SpringApplication.run(App.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(PrivilegeDao privilegeDao, SecurityGroupDao securityGroupDao, RoleDao roleDao, UserDao userDao, KeyPairDao keyPairDao, InstanceDao instanceDao, AmiDao amiDao, TemplateConfigurationDao templateConfigurationDao) {
//
//        return ars -> {
//            if (roleDao.findAllByExample(new Role(null, "STUDENT", null)).isEmpty()) {
//                Privilege privilegeRead = new Privilege(null, PrivilegeName.READ, null);
//                Privilege privilegeManageTemplate = new Privilege(null, PrivilegeName.MANAGE_TEMPLATE, null);
//                Privilege privilegeCreateTerminateInstance = new Privilege(null, PrivilegeName.CREATE_TERMINATE_INSTANCE, null);
//                Privilege privilegeStartStopInstance = new Privilege(null, PrivilegeName.START_STOP_INSTANCE, null);
//                Privilege privilegeViewTemplates = new Privilege(null, PrivilegeName.VIEW_TEMPLATES, null);
//                privilegeRead = privilegeDao.save(privilegeRead);
//                privilegeViewTemplates = privilegeDao.save(privilegeViewTemplates);
//                privilegeStartStopInstance = privilegeDao.save(privilegeStartStopInstance);
//                privilegeCreateTerminateInstance = privilegeDao.save(privilegeCreateTerminateInstance);
//                privilegeManageTemplate = privilegeDao.save(privilegeManageTemplate);
//                Role supervisorRole = roleDao.save(new Role(null, "ROLE_SUPERVISOR", List.of(privilegeManageTemplate,
//                                                                                                    privilegeCreateTerminateInstance,
//                                                                                                    privilegeStartStopInstance,
//                                                                                                    privilegeViewTemplates,
//                                                                                                    privilegeRead)));
//
//                Role instructorRole = roleDao.save(new Role(null, "ROLE_INSTRUCTOR", List.of(privilegeCreateTerminateInstance,
//                                                                                                    privilegeStartStopInstance,
//                                                                                                    privilegeViewTemplates,
//                                                                                                    privilegeRead)));
//
//                Role studentRole = roleDao.save(new Role(null, "ROLE_STUDENT", List.of( privilegeRead )));
//                System.out.println(studentRole.getName() + " " + studentRole.getId());
//                User supervisorUser = userDao.save(
//                        new User(null, "ashrf", "ashrf@g.com",
//                                "1234", supervisorRole, null, null, null));
//                User adminUser = userDao.save(
//                        new User(null, "marwa", "m@m.com",
//                                "1234", instructorRole, null, null, null));
//                User studentUser = userDao.save(
//                        new User(null, "hesham", "h@h.com",
//                                "1234", studentRole, null, null, null));
//                User studentUser2 = userDao.save(
//                        new User(null, "hossam", "ho@h.com",
//                                "1234", studentRole, null, null, null));
//
////                var key = keyPairDao.save(new KeyPair(null, "keyPairId", "keyName", "keyMaterial", "keyMaterialType", adminUser));
////                var ke2 = keyPairDao.save(new KeyPair(null, "keyPairId2", "keyName2", "keyMaterial2", "keyMaterialType2", adminUser));
////                SecurityGroup securityGroup = new SecurityGroup(null, "secGroupId", "secGroup1", "descriptoon", "vpcId", null, null);
////                securityGroupDao.save(securityGroup);
////                TemplateConfiguration templateConfiguration = new TemplateConfiguration(null, "ami1", "subnetId", "instanceType", adminUser, null, List.of(securityGroup));
////                TemplateConfiguration templateConfiguration2 = new TemplateConfiguration(null, "ami2", "subnetId2", "instanceType2", adminUser, null, List.of(securityGroup));
////                templateConfigurationDao.save(templateConfiguration);
////                templateConfigurationDao.save(templateConfiguration2);
////                Instance instance = instanceDao.save(new Instance(null, "name", "amid", "instanceId", "state", "publicIp", "publicDnsName", "instanceType", "subnetId", "vpcId", "platform", "decryptedPassword", "userName", LocalDateTime.now(), key, adminUser, null, null, templateConfiguration));
////                Instance instance2 = instanceDao.save(new Instance(null, "name2", "amid2", "instanceId2", "state2", "publicIp2", "publicDnsName2", "instanceType2", "subnetId2", "vpcId2", "platform2", "decryptedPassword2", "userName2", LocalDateTime.now(), key, adminUser, null, null, templateConfiguration2));
////
////                instanceDao.save(instance);
////                instanceDao.save(instance2);
////
////
////                Ami ami1 = amiDao.save(new Ami(null, "imageId", "imagwOwnerAlias", "arch", "imageName", "description", "platform"));
////                Ami ami2 = amiDao.save(new Ami(null, "imageId2", "imagwOwnerAlias2", "arch2", "imageName2", "description2", "platform2"));
//
//            }
//            System.out.println(userDao.findAllByExample(new User(null, "ashrf", null, null, null, null, null, null)).get(0).getId());
//            System.out.println("ASDASDAasdasDASDASDasd");
//
//        };
//    }
}


