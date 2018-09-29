package org.sergei.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_number")
    private Long customerNumber;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "age")
    private Integer age;

    @JsonIgnore
    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "customer_number")
    private List<Order> orders = new LinkedList<>();

    @JsonIgnore
    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "customer_number")
    private List<PhotoUploadResponse> photoUploadResponses = new LinkedList<>();

    public Customer() {
    }

    public Customer(String firstName, String lastName, Integer age,
                    List<Order> orders, List<PhotoUploadResponse> photoUploadResponses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.orders = orders;
        this.photoUploadResponses = photoUploadResponses;
    }

    public Long getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Long customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @JsonIgnore
    public List<PhotoUploadResponse> getPhotoUploadResponses() {
        return photoUploadResponses;
    }

    public void setPhotoUploadResponses(List<PhotoUploadResponse> photoUploadResponses) {
        this.photoUploadResponses = photoUploadResponses;
    }
}
