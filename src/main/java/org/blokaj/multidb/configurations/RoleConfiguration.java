package org.blokaj.multidb.configurations;

import lombok.RequiredArgsConstructor;
import org.blokaj.multidb.models.entities.db1.Role;
//import org.blokaj.multidb.repositories.db1.RoleRepository;
import org.blokaj.multidb.repositories.db1.RoleRepository;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

//@Configuration
@RequiredArgsConstructor
public class RoleConfiguration {

    private final RoleRepository roleRepository;

    @PostConstruct
    public void addRoles() {
        Role roleAdmin = new Role();
        roleAdmin.setName("ADMIN");

        Role roleUser = new Role();
        roleUser.setName("USER");

        List<Role> roles = new ArrayList<>();
        roles.add(roleAdmin);
        roles.add(roleUser);

        try {
            roleRepository.saveAll(roles);
        } catch (Exception ignore) {}
    }
}
