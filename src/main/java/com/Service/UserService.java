package com.Service;

import java.util.List;

public interface UserService<T> {
    void save(T t);

    List<T> findAll();

    T findById(Long id);

    void update(Long id, T t);

    void delete(Long id);
}
