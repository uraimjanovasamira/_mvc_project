package com.Service.impl;

import com.Service.UserService;
import com.model.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import com.model.Group;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GroupServiceImpl implements UserService<Group> {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void save(Group group) {
        entityManager.persist(group);
    }


    @Override
    public List<Group> findAll() {
        return entityManager.createQuery("from Group ", Group.class).getResultList();

    }

    @Override
    public Group findById(Long id) {
        return entityManager.find(Group.class, id);
    }

    @Override
    public void update(Long id, Group group) {
        Group oldGroup = findById(id);
        oldGroup.setGroupName(group.getGroupName());
        oldGroup.setDateOfStart(group.getDateOfStart());
        oldGroup.setDateOfFinish(group.getDateOfFinish());
        entityManager.merge(oldGroup);
    }

    @Override
    public void delete(Long id) {
        Group group=findById(id);
        entityManager.remove(group);

    }

}
