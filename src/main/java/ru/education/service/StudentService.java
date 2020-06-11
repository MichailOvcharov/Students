package ru.education.service;

import ru.education.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> findAll();

    Student findById(Object id);

    List<Student> findByName(String name);

    List<Student> findByPassport(String passport);

    Student create(Student student);

    Student update(Student student);

    void delete(Object id);
}
