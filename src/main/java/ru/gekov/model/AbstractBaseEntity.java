package ru.gekov.model;

import org.springframework.data.domain.Persistable;
import javax.persistence.*;

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class AbstractBaseEntity implements Persistable<Integer> {

    static final int START_SEQ = 100000;

    protected AbstractBaseEntity() {
    }

    public abstract void setId(Integer id);

    public abstract Integer getId();

    public boolean isNew() {
        return getId() == null;
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s)", getClass().getName(), getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractBaseEntity that = (AbstractBaseEntity) o;
        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId() == null ? 0 : getId();
    }
}
