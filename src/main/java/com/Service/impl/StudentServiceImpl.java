package com.Service.impl;

import com.Service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import com.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class StudentServiceImpl implements UserService<Student> {
    EntityManager entityManager;

    @Override
    public void save(Student student) {
        entityManager.persist(student);

    }

    @Override
    public List<Student> findAll() {
        return entityManager.createQuery("from Student ", Student.class).getResultList();
    }

    @Override
    public Student findById(Long id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public void update(Long id, Student student) {
        Student oldStudent = findById(id);
        oldStudent.setName(student.getName());
        oldStudent.setEmail(student.getEmail());
        oldStudent.setLastName(student.getLastName());
        oldStudent.setStudyFormat(student.getStudyFormat());
        entityManager.merge(oldStudent);

    }

    @Override
    public void delete(Long id) {
        entityManager.remove(id);
    }
}