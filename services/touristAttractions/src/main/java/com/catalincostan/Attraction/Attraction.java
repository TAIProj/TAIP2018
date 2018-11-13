package com.catalincostan.Attraction;

import javax.persistence.*;

@Entity
public class Attraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String Name;

    @Column(nullable = false)
    private String Description;

    @Column(nullable = false)
    private String Image;

    @Column
    private String Address;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImageUrl() {
        return Image;
    }

    public void setImageUrl(String imageUrl) {
        Image = imageUrl;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
