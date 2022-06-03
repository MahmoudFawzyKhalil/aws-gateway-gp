package eg.gov.iti.jets.service.model;

import java.util.ArrayList;
import java.util.List;

public class Role {
    private Integer id;
    private String name;
    private final List<Privilege> privileges = new ArrayList<>();
}
