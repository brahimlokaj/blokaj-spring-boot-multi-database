package org.blokaj.multidb.repositories.db2;

import org.blokaj.multidb.models.entities.db2.Tenant;
import org.blokaj.multidb.models.responses.ViewTenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(transactionManager = "db2ManagerFactory", propagation = Propagation.REQUIRED)
public interface TenantRepository extends JpaRepository<Tenant, Long> {

    Optional<Tenant> findFirstByName(String name);

    @Query("select new org.blokaj.multidb.models.responses.ViewTenant(t.id, t.name, t.createdAt, t.updatedAt) from Tenant t")
    List<ViewTenant> findAllTenants();
}
