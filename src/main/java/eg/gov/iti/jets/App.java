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
    @Bean
    CommandLineRunner commandLineRunner(PrivilegeDao privilegeDao,SecurityGroupDao securityGroupDao,RoleDao roleDao, UserDao userDao, KeyPairDao keyPairDao, InstanceDao instanceDao, AmiDao amiDao,TemplateConfigurationDao templateConfigurationDao){
        return ars -> {
            if(roleDao.findAllByExample(new Role(null,"STUDENT",null))!=null){
                Privilege privilege=new Privilege(null, PrivilegeName.TEST_PRIVILEGE_1,null);
                Privilege privilege2=new Privilege(null,PrivilegeName.TEST_PRIVILEGE_2,null);
                privilege=privilegeDao.save(privilege);
                privilege2=privilegeDao.save(privilege2);
                Role studentRole=roleDao.save(new Role(null,"STUDENT",List.of(privilege)));
                Role adminRole=roleDao.save(new Role(null,"ADMIN",List.of(privilege,privilege2)));
                System.out.println(studentRole.getName()+" "+studentRole.getId());
                User studentUser = userDao.save(new User(null, "student1", "student1", "student1", studentRole, null, null, null));
                User adminUser = userDao.save(new User(null, "admin1", "admin1", "admin1", adminRole, null, null, null));

                var key = keyPairDao.save(new KeyPair(null, "keyPairId", "keyName", "keyMaterial", "keyMaterialType", adminUser));
                var ke2 = keyPairDao.save(new KeyPair(null, "keyPairId2", "keyName2", "keyMaterial2", "keyMaterialType2", adminUser));
                SecurityGroup securityGroup=new SecurityGroup(null,"secGroupId","secGroup1","descriptoon","vpcId",null,null);
                securityGroupDao.save(securityGroup);
                TemplateConfiguration templateConfiguration=new TemplateConfiguration(null,"ami1","subnetId","instanceType",adminUser,null, List.of(securityGroup));
                TemplateConfiguration templateConfiguration2=new TemplateConfiguration(null,"ami2","subnetId2","instanceType2",adminUser,null, List.of(securityGroup));
                templateConfigurationDao.save(templateConfiguration);
                templateConfigurationDao.save(templateConfiguration2);
                Instance instance = instanceDao.save(new Instance(null, "name", "amid", "instanceId", "state", "publicIp", "publicDnsName", "instanceType", "subnetId", "vpcId", "decryptedPassword", "userName", LocalDateTime.now(), key, adminUser, null, null, templateConfiguration));
                Instance instance2 = instanceDao.save(new Instance(null, "name2", "amid2", "instanceId2", "state2", "publicIp2", "publicDnsName2", "instanceType2", "subnetId2", "vpcId2", "decryptedPassword2", "userName2", LocalDateTime.now(), key, adminUser, null, null, templateConfiguration2));

                instanceDao.save(instance);
                instanceDao.save(instance2);


                Ami ami1=amiDao.save(new Ami(null,"imageId","imagwOwnerAlias","arch","imageName","description","platform"));
                Ami ami2=amiDao.save(new Ami(null,"imageId2","imagwOwnerAlias2","arch2","imageName2","description2","platform2"));

            }

        };
    }
}
