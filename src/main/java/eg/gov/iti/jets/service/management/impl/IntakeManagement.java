package eg.gov.iti.jets.service.management.impl;

import eg.gov.iti.jets.service.management.CrudOperations;
import eg.gov.iti.jets.service.model.Intake;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntakeManagement implements CrudOperations<Intake> {

    @Override
    public Boolean create(Intake type) {
        return null;
    }

    @Override
    public Intake update(Intake type) {
        return null;
    }

    @Override
    public Boolean delete(int id) {
        return null;
    }

    @Override
    public List<Intake> getAll() {
        return null;
    }

    @Override
    public Intake getById(int id) {
        return null;
    }
}
