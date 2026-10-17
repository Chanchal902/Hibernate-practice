package org.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Candidate_Table")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "C_ID")
    private int id;
    @Column(name = "Name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Laptop_Id")
    private Laptop laptop;

    public Candidate() {
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

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }
}
