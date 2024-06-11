package com.Service.impl;

import com.Service.UserService;
import com.model.Company;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import com.model.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements UserService<Course> {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void save(Course course) {
        Company company=entityManager.find(Company.class,course.getCompany().getId());
        course.setCompany(company);
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
        Course course=findById(id);
        entityManager.remove(course);

    }
}
