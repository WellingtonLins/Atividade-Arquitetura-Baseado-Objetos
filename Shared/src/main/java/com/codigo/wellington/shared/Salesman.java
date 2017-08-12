package com.codigo.wellington.shared;

import java.io.Serializable;

/**
 * @Date 11/08/2017 @Time 08:27:01
 * @author Wellington Lins Claudino Duarte
 * @mail wellingtonlins2013@gmail.com
 */
public class Salesman implements Serializable {

    private Person person;
    private String phone;

    public Salesman(Person person, String phone) {
        this.person = person;
        this.phone = phone;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}