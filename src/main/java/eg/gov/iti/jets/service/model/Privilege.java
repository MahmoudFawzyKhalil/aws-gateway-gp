package eg.gov.iti.jets.service.model;

import java.util.ArrayList;
import java.util.List;

public class Privilege {

    private Integer id;
    private String name;
    private final List<Role> roles = new ArrayList<>();
}
