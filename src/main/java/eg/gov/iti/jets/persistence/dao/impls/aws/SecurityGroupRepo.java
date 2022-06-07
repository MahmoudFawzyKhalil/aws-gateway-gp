package eg.gov.iti.jets.persistence.dao.impls.aws;

import eg.gov.iti.jets.persistence.entity.aws.SecurityGroup;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface SecurityGroupRepo extends JpaRepository<SecurityGroup,Integer> {

    <T> Optional<T> findById(Integer id, Class<T> projection);

    <T> Page<T> findBy(PageRequest pageRequest, Class<T> projection);

    <T> List<T> findAllBy(Example<T> example, Class<T> projection);
}
