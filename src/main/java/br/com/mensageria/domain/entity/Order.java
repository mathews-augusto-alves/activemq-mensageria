package br.com.mensageria.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {

    private String id;
    private String product;
    private int quantity;
    private String customer;

    public Order(String id, String product, int quantity, String customer) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.customer = customer;
    }

    public Order() {
    }
}
