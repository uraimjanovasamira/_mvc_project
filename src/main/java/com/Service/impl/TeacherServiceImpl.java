package com.Service.impl;

import com.Service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import com.model.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class TeacherServiceImpl implements UserService<Teacher> {

    EntityManager entityManager;

    @Override
    public void save(Teacher teacher) {
        entityManager.persist(teacher);

    }
    @Override
    public List<Teacher> findAll() {
        return entityManager.createQuery("from Teacher", Teacher.class).getResultList();
    }

    @Override
    public Teacher findById(Long id) {
        return entityManager.find(Teacher.class, id);
    }

    @Override
    public void update(Long id, Teacher teacher) {
        Teacher oldTeacher = findById(id);
        oldTeacher.setName(teacher.getName());
        oldTeacher.setEmail(teacher.getEmail());
        oldTeacher.setLastName(teacher.getLastName());
        entityManager.merge(oldTeacher);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(id);

    }
}
