package com.example.microserviceDemo;

import javax.persistence.*;

import java.util.Objects;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.Digits;
@Entity
public class Customer {

    @Id
    @SequenceGenerator(name  = "customer_id_sequence",
    sequenceName = "customer_id_sequence")

    //allocationSize = 1
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_id_sequence"

    )
    private Integer id;
    private String name;

    private String email;

    private String password;

   // @Digits(integer = 10, fraction =0)
    private String phone;

    public Customer(Integer id, String name, String email, String password, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }
    public Customer(){

    }


    public String getEmail(){
        return this.email;
    }
    public Integer getID(){

        return this.id;
    }
    public boolean setID(Integer id){
        this.id= id;
        return true;
    }

    public String getPhone(){

        return this.phone;
    }
    public boolean setPhone(String newPhone){
        this.phone= newPhone;
        return true;
    }

    public boolean setEmail(String newEmail){
        this.email= newEmail;
        return true;
    }
    public String getPassword(){

        return this.password;
    }
    public boolean setPassword(String newPassword){
        this.password= newPassword;
        return true;
    }

    public String getName(){
        return this.name;
    }
    public boolean setName(String newName){
        this.name= newName;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(phone, customer.phone) && Objects.equals(id, customer.id) && Objects.equals(name, customer.name) && Objects.equals(email, customer.email) && Objects.equals(password, customer.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password,phone);
    }

    @Override
    public String toString() {
        return "Customer{" +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
