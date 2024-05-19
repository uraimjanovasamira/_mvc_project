package com.Service.impl;

import com.Service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import com.model.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements UserService<Course> {

    EntityManager entityManager;

    @Override
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    public List<Course> findAll() {
        return entityManager.createQuery("from Course ", Course.class).getResultList();
    }

    @Override
    public Course findById(Long id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    public void update(Long id, Course course) {
        Course oldCourse = findById(id);
        oldCourse.setCourseName(course.getCourseName());
        oldCourse.setDuration(course.getDuration());
        entityManager.merge(oldCourse);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(id);

    }
}
