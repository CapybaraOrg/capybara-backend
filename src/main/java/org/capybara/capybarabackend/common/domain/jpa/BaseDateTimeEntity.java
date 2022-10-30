package org.capybara.capybarabackend.common.domain.jpa;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseDateTimeEntity extends BaseEntity {

    //<editor-fold desc="Fields">

    private LocalDateTime created;

    private LocalDateTime modified;

    //</editor-fold>

    //<editor-fold desc="Constructors">

    public BaseDateTimeEntity() {
        // Empty constructor is required by JPA
    }

    //</editor-fold>

    //<editor-fold desc="Primitive accessors">

    @NotNull
    @Column(name = "created", nullable = false)
//    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
//    @Convert(converter = LocalDateTimeConverter.class)
    // https://stackoverflow.com/questions/53041110/mapping-timestamp-column-from-postgresql-to-localdate
    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @NotNull
    @Column(name = "modified", nullable = false)
    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    //</editor-fold>

}
