package com.refah.walletwrapper.model.entity;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date createdDate;
    private Date modifiedDate;

    @PrePersist
    void setCreatedDate() {
        if (createdDate == null)
            createdDate = new Date();
    }

    @PreUpdate
    void setModifiedDate() {
        if (modifiedDate == null)
            modifiedDate = new Date();
    }

    public BaseModel() {
    }

    public BaseModel(Integer id, Date createdDate) {
        this.id = id;
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
