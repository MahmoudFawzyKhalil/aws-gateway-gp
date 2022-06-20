package eg.gov.iti.jets.persistence.dao;

import eg.gov.iti.jets.persistence.entity.aws.Instance;

import java.util.List;

public interface InstanceDao extends GenericCrudDao<Instance, Integer> {
    List<Instance> findUserGrantedInstances(int userId);

    List<Instance> findFollowersUsersGrantedInstances(int userId);
}
