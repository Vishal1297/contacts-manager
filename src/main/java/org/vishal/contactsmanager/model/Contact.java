package org.vishal.contactsmanager.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @Column(name = "uuid", nullable = false)
    private String uuid;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "dateOfBirth")
    private Long dateOfBirth;

    @OneToOne(cascade = CascadeType.ALL , orphanRemoval = true)
    @JoinColumn(name = "address_uuid")
    @JsonManagedReference
    private Address address;

    public Contact() {
    }

    public Contact(String uuid, String fullName, Long dateOfBirth, Address address) {
        this.uuid = uuid;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Long dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
