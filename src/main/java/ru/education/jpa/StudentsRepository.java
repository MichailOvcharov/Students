package ru.education.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.education.entity.Student;

import java.util.List;

@Repository
public interface StudentsRepository extends JpaRepository<Student,Integer> {

    List<Student> findByName(String name);

    List<Student> findByPassport(String passport);

}
