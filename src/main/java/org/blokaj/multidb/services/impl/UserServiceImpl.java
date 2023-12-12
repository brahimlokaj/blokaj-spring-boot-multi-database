package org.blokaj.multidb.services.impl;

import lombok.RequiredArgsConstructor;
import org.blokaj.multidb.repositories.db1.RoleRepository;
import org.blokaj.multidb.repositories.db1.UserRepository;
import org.blokaj.multidb.repositories.db2.TenantRepository;
import org.blokaj.multidb.services.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final TenantRepository tenantRepository;

}
