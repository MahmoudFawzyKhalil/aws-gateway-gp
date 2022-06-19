package eg.gov.iti.jets.persistence.dao;

import eg.gov.iti.jets.persistence.entity.aws.Instance;

import java.util.List;

public interface InstanceDao extends GenericCrudDao<Instance, Integer> {
    <C> List<C> findUserGrantedInstances(int userId, Class<C> projection);
}
