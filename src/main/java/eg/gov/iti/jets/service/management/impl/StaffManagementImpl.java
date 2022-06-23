package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.persistence.dao.UserDao;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.service.exception.ResourceExistException;
import eg.gov.iti.jets.service.exception.ResourceNotFoundException;
import eg.gov.iti.jets.service.management.StaffManagement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class StaffManagementImpl implements StaffManagement {
    private final UserDao userDao;

    @Override
    public List<User> getAllStaff() {
        return null;
    }

    @Override
    public User createStaff(User user) {
        try {
            return userDao.save(user);
        }catch (Exception exception) {
            throw new ResourceExistException("Staff with name [ " + user.getUsername() + " ] , already exists !");
        }
    }

    @Override
    public User getStaffById(int id) {
        return userDao.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User with id [ " + id + " ] , not found"));
    }

    @Override
    public User updateStaff(User user) {
        try{
            return userDao.update(user);
        }catch (Exception ex){
            throw new ResourceNotFoundException("Could not update Staff with id [ " + user.getId() + " ] !!");
        }
    }
}
