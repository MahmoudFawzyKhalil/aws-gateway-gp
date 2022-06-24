package eg.gov.iti.jets.service.management;

import eg.gov.iti.jets.persistence.entity.User;

import java.util.List;

public interface StaffManagement {

    //get all staff
    //create staff
    //create bulk of staff
    //update

    List<User> getAllStaff();

    void createStaff(List<User> staff);

    User getStaffById(int id);

    User updateStaff(User user);













    //get by track
}
