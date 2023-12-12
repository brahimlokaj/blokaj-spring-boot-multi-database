package org.blokaj.multidb.repositories.db1;

import org.blokaj.multidb.models.entities.db1.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(transactionManager = "db1ManagerFactory", propagation = Propagation.REQUIRED)
public interface RoleRepository extends JpaRepository<Role, Long> {

}
