package eg.gov.iti.jets.persistence.dao.impls;

import eg.gov.iti.jets.persistence.entity.Role;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface RoleRepo extends JpaRepository<Role,Integer> {
    <C> Optional<C> findById(Integer id, Class<C> projection);


    <C> Page<C> findBy(PageRequest pageRequest, Class<C> projection);

    <C> List<C> findAllBy(Example<C> example, Class<C> projection);
}
