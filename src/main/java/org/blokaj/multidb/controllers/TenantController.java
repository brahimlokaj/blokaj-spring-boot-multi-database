package org.blokaj.multidb.controllers;

import lombok.RequiredArgsConstructor;
import org.blokaj.multidb.models.ResponseObject;
import org.blokaj.multidb.models.requests.CreateTenant;
import org.blokaj.multidb.models.responses.ViewTenant;
import org.blokaj.multidb.services.TenantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tenants")
public class TenantController {

    private final TenantService tenantService;

    @PostMapping("")
    ResponseEntity<ResponseObject<ViewTenant>> create(@RequestBody @Valid CreateTenant createTenant){
        ResponseObject<ViewTenant> responseObject = tenantService.create(createTenant);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseObject);
    }

    @GetMapping("/{tenantId}")
    ResponseEntity<ResponseObject<ViewTenant>>findById(@PathVariable("tenantId") Long tenantId){
        return ResponseEntity.ok(tenantService.findById(tenantId));
    }

    @GetMapping("")
    ResponseEntity<ResponseObject<List<ViewTenant>>> findAll(){
        return ResponseEntity.ok(tenantService.findAll());
    }
}
