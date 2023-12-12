package org.blokaj.multidb.models.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateTenant {

    @NotBlank
    String name;
}
