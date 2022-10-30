package org.capybara.capybarabackend.item.domain.jpa;

import org.capybara.capybarabackend.common.domain.jpa.BaseDateTimeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static org.capybara.capybarabackend.item.model.ItemModel.CONSTRAINT_NAME_MAX_SIZE;
import static org.capybara.capybarabackend.item.model.ItemModel.CONSTRAINT_NAME_MIN_SIZE;

@Entity
@Table(name = "items")
public class ItemEntity extends BaseDateTimeEntity {

    //<editor-fold desc="Fields">

    private String name = null;

    //</editor-fold>

    //<editor-fold desc="Constructors">

    public ItemEntity() {
        // Empty constructor is required by JPA
    }

    //</editor-fold>

    //<editor-fold desc="Primitive accessors">

    @NotBlank
    @Size(min = CONSTRAINT_NAME_MIN_SIZE, max = CONSTRAINT_NAME_MAX_SIZE)
    @Column(name = "name", length = CONSTRAINT_NAME_MAX_SIZE, unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //</editor-fold>

    //<editor-fold desc="Object methods">

    @Override
    public String toString() {
        return String.format(
                "ItemEntity[id=%s, name='%s']",
                getId(), getName());
    }

    //</editor-fold>

}
