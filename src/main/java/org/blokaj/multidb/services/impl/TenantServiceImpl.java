package org.blokaj.multidb.services.impl;

import lombok.RequiredArgsConstructor;
import org.blokaj.multidb.models.ResponseObject;
import org.blokaj.multidb.models.entities.db2.Tenant;
import org.blokaj.multidb.models.requests.CreateTenant;
import org.blokaj.multidb.models.responses.ViewTenant;
import org.blokaj.multidb.repositories.db2.TenantRepository;
import org.blokaj.multidb.services.TenantService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TenantServiceImpl implements TenantService {

    private final TenantRepository tenantRepository;

    @Override
    public ResponseObject<ViewTenant> create(CreateTenant createTenant) {
        Optional<Tenant> optionalTenant = tenantRepository.findFirstByName(createTenant.getName());
        if (optionalTenant.isPresent()) {
            throw new RuntimeException("This tenant exist!");
        }

        Tenant tenant = new Tenant();
        tenant.setName(createTenant.getName());
        tenantRepository.save(tenant);

        return mapResponseObject(HttpStatus.CREATED, tenant);
    }

    @Override
    public ResponseObject<ViewTenant> findById(Long tenantId) {
        Optional<Tenant> optionalTenant = tenantRepository.findById(tenantId);
        if(optionalTenant.isEmpty()) {
            throw new RuntimeException("Tenant not found!");
        }

        return mapResponseObject(HttpStatus.OK, optionalTenant.get());
    }

    @Override
    public ResponseObject<List<ViewTenant>> findAll() {
        HttpStatus httpStatus = HttpStatus.OK;
        List<ViewTenant> tenants = tenantRepository.findAllTenants();
        if (tenants.isEmpty()) {
            httpStatus = HttpStatus.NO_CONTENT;
        }

        return ResponseObject.<List<ViewTenant>>builder()
                .status(httpStatus)
                .data(tenants)
                .build();
    }

    private ResponseObject<ViewTenant> mapResponseObject(HttpStatus httpStatus, Tenant tenant) {
        ViewTenant viewTenant = ViewTenant.builder()
                .id(tenant.getId())
                .name(tenant.getName())
                .createdAt(tenant.getCreatedAt())
                .updatedAt(tenant.getUpdatedAt())
                .build();

        return ResponseObject.<ViewTenant>builder()
                .status(httpStatus)
                .data(viewTenant)
                .build();
    }
}
