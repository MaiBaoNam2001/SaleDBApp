package com.mbn.pojo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category extends Base {
    public Category() {
    }

    public Category(int id, String name, String description) {
        super(id, name, description);
    }
}
