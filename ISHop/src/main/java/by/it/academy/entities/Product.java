package by.it.academy.entities;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private int id;
    private String name;
    private BigDecimal price;
    private int number;
    private String description;

    public Product(String name, BigDecimal price, int number, String description) {
        this.name = name;
        this.price = price;
        this.number = number;
        this.description = description;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && number == product.number && Objects.equals(name, product.name) && Objects.equals(price, product.price) && Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, number, description);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", number=" + number +
                ", description=" + description +
                '}';
    }
}
