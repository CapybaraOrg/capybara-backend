package org.capybara.capybarabackend.common.domain.jpa;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class BaseEntity implements Persistable<String> {

    //<editor-fold desc="Fields">

    private String id;

    //</editor-fold>

    //<editor-fold desc="Constructors">

    public BaseEntity() {
        // Empty constructor is required by JPA
    }

    //</editor-fold>

    //<editor-fold desc="Primitive accessors">

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id", unique = true, nullable = false)
    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //</editor-fold>

    //<editor-fold desc="Persistable and Object methods">

    @Transient
    @Override
    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if ((other == null) || (this.getClass() != other.getClass())) {
            return false;
        }

        BaseEntity otherBaseEntity = (BaseEntity) other;

        return (this.id != null && this.id.equals(otherBaseEntity.id));
    }

    @Override
    public int hashCode() {
        int hash = 1;

        int idCode = (this.id == null ? 0 : this.id.hashCode());

        hash = 31 * hash + idCode;

        return hash;
    }

    //</editor-fold>

}
