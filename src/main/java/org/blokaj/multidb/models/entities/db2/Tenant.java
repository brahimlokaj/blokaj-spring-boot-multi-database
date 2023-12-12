package org.blokaj.multidb.models.entities.db2;

import lombok.Getter;
import lombok.Setter;
import org.blokaj.multidb.models.entities.BaseEntity;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tenants")
public class Tenant extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

}
