package com.codigo.wellington.shared;

import java.io.Serializable;

/**
 * @Date 11/08/2017  @Time 08:29:30
 * @author Wellington Lins Claudino Duarte
 * @mail wellingtonlins2013@gmail.com
 */

public class Order implements Serializable{
private int id;
private Salesman salesman;
private Product product;
private int quantity;

    public Order(int id, Salesman salesman, Product product, int quantity) {
        this.id = id;
        this.salesman = salesman;
        this.product = product;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Salesman getSalesman() {
        return salesman;
    }

    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}