package com.Service.impl;

import com.Service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import com.model.Company;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CompanyService implements UserService<Company> {

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
        entityManager.remove(id);

    }
}
