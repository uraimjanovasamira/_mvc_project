package com.Service.impl;

import com.Service.UserService;
import com.enums.Role;
import com.model.Course;
import com.model.Group;
import com.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService<User> {

    private final BCryptPasswordEncoder passwordEncoder;

    @PersistenceContext
    EntityManager entityManager;

    public UserServiceImpl(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void save(User user) {
        Group group = entityManager.find(Group.class, user.getGroup().getId());
        Course course = entityManager.find(Course.class, user.getCourse().getId());
        user.setGroup(group);
        user.setCourse(course);

        if (user.getEmail() == null){
            throw new RuntimeException("Email is null");
        }
        if (user.getName().length() < 2){
            throw new RuntimeException("Name is not correct");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.INSTRUCTOR);
        entityManager.persist(user);

        if (findAll().isEmpty()) {
            user.setRole(Role.ADMIN);
            user.setCourse(null);
            user.setGroup(null);
            user.setStudyFormat(null);
        } else if (user.getName().equals("instructor")) {
            user.setRole(Role.INSTRUCTOR);
            if (user.getCourse() == null) {
                throw new RuntimeException("Instructor must have a course");
            }
            Course course1 = entityManager.find(Course.class, user.getCourse().getId());
            user.setCourse(course1);
            user.setGroup(null);
        } else {
            user.setRole(Role.STUDENT);
            if (user.getGroup() == null) {
                throw new RuntimeException("Student must have a group");
            }
            Group group1= entityManager.find(Group.class, user.getGroup().getId());
            user.setGroup(group1);
            user.setCourse(null);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        entityManager.persist(user);

    }
    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void update(Long id, User user) {
        User oldUser = findById(id);
        Group group = entityManager.find(Group.class, oldUser.getGroup().getId());
        Course course = entityManager.find(Course.class, oldUser.getCourse().getId());

        oldUser.setName(group.getGroupName());
        oldUser.setEmail(user.getEmail());
        oldUser.setLastName(user.getLastName());
        oldUser.setStudyFormat(user.getStudyFormat());
        oldUser.setGroup(group);
        oldUser.setCourse(course);

        oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
        oldUser.setRole(user.getRole());
        entityManager.merge(oldUser);

    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("from User ", User.class).getResultList();
    }

    @Override
    public void delete(Long id) {
        User user = findById(id);
        entityManager.remove(user);
    }

    public User findByEmail(String email) {
        return entityManager.createQuery("select user from User user where user.email =: email", User.class)
                .setParameter("email", email).getSingleResult();
    }

}

