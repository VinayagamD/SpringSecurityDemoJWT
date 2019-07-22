package com.vinay.springsecdemo.services;

import com.vinay.springsecdemo.models.BaseModel;

import java.util.List;

public interface CrudServices<T extends BaseModel, ID> {

    List<T> findAll();
    T findById(ID id);
    T save(T t);
    void delete(T t);
    void deleteById(ID id);
}
