package org.blokaj.multidb.services;

import org.blokaj.multidb.models.ResponseObject;
import org.blokaj.multidb.models.requests.CreateTenant;
import org.blokaj.multidb.models.responses.ViewTenant;

import java.util.List;

public interface TenantService {

    ResponseObject<ViewTenant> create(CreateTenant createTenant);

    ResponseObject<ViewTenant> findById(Long tenantId);

    ResponseObject<List<ViewTenant>> findAll();
}
