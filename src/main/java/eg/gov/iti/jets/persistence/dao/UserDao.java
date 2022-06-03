package eg.gov.iti.jets.persistence.dao;

import eg.gov.iti.jets.persistence.entity.User;

import java.util.Optional;

public interface UserDao extends GenericCrudDao<User, Integer> {
    Optional<User> findByUserNameAndPassword(String userName, String password);
}
