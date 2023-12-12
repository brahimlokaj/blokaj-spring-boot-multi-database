package org.blokaj.multidb.models.entities.db1;

import lombok.Getter;
import lombok.Setter;
import org.blokaj.multidb.models.entities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

}
