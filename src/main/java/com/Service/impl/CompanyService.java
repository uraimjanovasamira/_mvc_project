package com.Service.impl;

import com.Service.UserService;
import com.model.Company;
import com.model.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CompanyService implements UserService<Company> {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void save(Company company) {
        entityManager.persist(company);
    }

    @Override
    public List<Company> findAll() {
        return entityManager.createQuery("from Company ", Company.class).getResultList();
    }

    @Override
    public Company findById(Long id) {
        return entityManager.find(Company.class, id);
    }

    @Override
    public void update(Long id, Company company) {
        Company oldCompany = findById(id);
        oldCompany.setCompanyName(company.getCompanyName());
        oldCompany.setLocatedCountry(company.getLocatedCountry());
        entityManager.merge(oldCompany);
    }

    @Override
    public void delete(Long id) {
        Company company = findById(id);
        entityManager.remove(company);

    }


    public List<Course> getCoursesByCompany(Long companyId) {
        return entityManager.createQuery("select c from Course c join Company com on c.company.id=com.id where com.id=:companyId", Course.class)
                .setParameter("companyId", companyId).getResultList();
    }
}
