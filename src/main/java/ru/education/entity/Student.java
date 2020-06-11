package ru.education.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "students")
@Getter
@Setter
public class Student {

    public static String TYPE_NAME = "Студент";
    @Id
    @Column(name = "id", nullable =  false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "students_id_seq")
    @SequenceGenerator(name = "students_id_seq", sequenceName = "students_id_seq", allocationSize = 1)
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "passport")
    private String passport;

    public Student() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }
}
