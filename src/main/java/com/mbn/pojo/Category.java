package com.mbn.pojo;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category extends Base {
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<Product> products;

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
