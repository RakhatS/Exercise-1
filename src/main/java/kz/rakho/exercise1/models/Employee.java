package kz.rakho.exercise1.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Data
@Entity
//@Getter
@Table(name = "Employees")

public class Employee {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String surName;
    @Column(unique = true)
    @NotNull
    private String email;

    @Column(unique = true)
    @NotNull
    private String phoneNumber;

    @NotNull
    private String country;

    @NotNull
    private String city;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSurName() {
        return surName;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }
}
