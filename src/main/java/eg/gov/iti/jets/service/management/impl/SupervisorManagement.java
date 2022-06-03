package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.service.management.CrudOperations;
import eg.gov.iti.jets.service.model.Supervisor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupervisorManagement implements CrudOperations<Supervisor> {

    @Override
    public Boolean create(Supervisor type) {
        return null;
    }

    @Override
    public Supervisor update(Supervisor type) {
        return null;
    }

    @Override
    public Boolean delete(int id) {
        return null;
    }

    @Override
    public List<Supervisor> getAll() {
        return null;
    }

    @Override
    public Supervisor getById(int id) {
        return null;
    }
}
