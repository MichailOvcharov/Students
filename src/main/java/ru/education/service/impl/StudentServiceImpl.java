package ru.education.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.education.entity.Student;
import ru.education.exceptions.EntityAlredyExistsException;
import ru.education.exceptions.EntityIllegalArgumentException;
import ru.education.exceptions.EntityNotFoundException;
import ru.education.jpa.StudentsRepository;
import ru.education.service.StudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentsRepository studentsRepository;

    public StudentServiceImpl(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public List<Student> findAll() {
        return studentsRepository.findAll();
    }

    public Student findById(Object id) {
        Student student;
        if (id == null) {
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }
        Integer parsedId;
        try {
            if (id instanceof Integer) {
                parsedId = (Integer) id;
            }  else {
                parsedId = Integer.valueOf((String) id);
            }
        } catch (NumberFormatException ex) {
            throw new EntityIllegalArgumentException(String.format("Не удалось перобразовать идентификатор "+
                    "к нужному типу, %s",ex)
            );
        }
        student = studentsRepository.findOne(parsedId);
        if (student == null) {
            throw new EntityNotFoundException(Student.TYPE_NAME, parsedId);
        }
        return student;
    }
    @Override
    public List<Student> findByName(String name) {
        return studentsRepository.findByName(name);
    }

    @Override
    public List<Student> findByPassport(String passport) {
        return studentsRepository.findByPassport(passport);
    }

    public Student create(Student student) {
        if (student == null) {
            throw new EntityIllegalArgumentException("Создаваемый объект не может быть null");
        }
        if (student.getId() == null) {
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }
        Student existedStudent = studentsRepository.findOne(student.getId());
        if (existedStudent != null) {
            throw new EntityAlredyExistsException(Student.TYPE_NAME, student.getId());
        }
        // Проверку на уникальность паспорта.
        List<Student> existedStudentPassport = studentsRepository.findByPassport(student.getPassport());
        if (existedStudentPassport.size() > 0) {
            throw new EntityAlredyExistsException("Студент с таким номером паспорта уже заведен!");
        }
        return studentsRepository.save(student);
    }

    public Student update(Student student) {
        if (student == null) {
            throw new EntityIllegalArgumentException("Создаваемый объект не может быть null");
        }
        if (student.getId() == null) {
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }
        List<Student> existedStudentPassport = studentsRepository.findByPassport(student.getPassport());
        if (existedStudentPassport.size() > 0) {
            throw new EntityAlredyExistsException("Студент с таким номером паспорта уже заведен!");
        }
        Student existedProduct = studentsRepository.findOne(student.getId());
        if (existedProduct == null) {
            throw new EntityNotFoundException(Student.TYPE_NAME, student.getId());
        }
        return studentsRepository.save(student);
    }

    public void delete(Object id) {
        Student student = this.findById(id);
        Student existedProduct = studentsRepository.findOne(student.getId());
        if (existedProduct == null) {
            throw new EntityNotFoundException(Student.TYPE_NAME, student.getId());
        }
        studentsRepository.delete(student);
    }
}
