package org.capybara.capybarabackend.item.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ItemModel {

    //<editor-fold desc="Constants">

    public static final int CONSTRAINT_NAME_MIN_SIZE = 1;
    public static final int CONSTRAINT_NAME_MAX_SIZE = 255;

    //</editor-fold>

    //<editor-fold desc="Fields">

    private String name;

    //</editor-fold>

    //<editor-fold desc="Primitive accessors">

    @NotBlank
    @Size(min = CONSTRAINT_NAME_MIN_SIZE, max = CONSTRAINT_NAME_MAX_SIZE)
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
                "ItemModel[name='%s']",
                getName());
    }

    //</editor-fold>

}
