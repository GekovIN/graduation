package ru.gekov.to;

public class AbstractTo {
    private Integer id;

    public AbstractTo() {
    }

    public AbstractTo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return getId() == null;
    }


}
