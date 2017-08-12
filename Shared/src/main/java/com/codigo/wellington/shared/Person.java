package com.codigo.wellington.shared;

import java.io.Serializable;

/**
 * @Date 11/08/2017  @Time 08:16:30
 * @author Wellington Lins Claudino Duarte
 * @mail wellingtonlins2013@gmail.com
 */

public class Person implements Serializable{
private int id;
private String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
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
}