package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.persistence.dao.UserDao;
import eg.gov.iti.jets.persistence.entity.User;
import eg.gov.iti.jets.service.exception.ResourceAlreadyExistException;
import eg.gov.iti.jets.service.exception.ResourceConstraintsViolationException;
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
        return userDao.findUsersWithoutRoleName("STUDENT");
    }

    @Override
    public void createStaff(List<User> staff){
        for (User user:staff) {
            try {
                System.out.println("management :: "+user.getUsername());
                System.out.println();
                userDao.save(user);
            }catch (Exception e) {
                throw new ResourceAlreadyExistException("There is duplicate in Staff "+ user.getUsername());
            }
        }
    }

    @Override
    public User getStaffById(int id) {
        return userDao.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User with id [ " + id + " ] , not found"));
    }

    @Override
    public void updateStaff(User user) {
        try{

             userDao.update(user);
        }catch (Exception ex){
            throw new ResourceNotFoundException("Could not update Staff with id [ " + user.getId() + " ] !!");
        }
    }

    @Override
    public List<User> getAllInstructors(User user) {
        if(user.getRole().getName().equalsIgnoreCase("TRACK_SUPERVISOR"))
               return   userDao.findAllFollowers(user);
        else {
            throw new ResourceConstraintsViolationException("not allowed to access");
        }

    }
}
