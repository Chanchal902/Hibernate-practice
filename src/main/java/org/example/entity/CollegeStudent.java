package org.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "college")
public class CollegeStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private int id;
    @Column(name = "student_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public CollegeStudent() {
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
