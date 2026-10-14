package org.example.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "Details")
public class Students {
    @Id
    private int id;

    @Column(name = "S_Name")
    private String name;

    @Column(name = "S_Marks")
    private int marks;

    //default constructor
    public Students() {
    }

    //    public Students(int s_id , String s_name , int s_marks){
//        this.id = s_id;
//        this.name = s_name;
//        this.marks = s_marks;
//    }
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

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
}
