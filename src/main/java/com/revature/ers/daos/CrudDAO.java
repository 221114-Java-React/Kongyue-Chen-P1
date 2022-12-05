package com.revature.ers.daos;

import java.util.List;

public interface CrudDAO <T>{
    // Return Type MethodName(PlaceHolder)
    void save(T obj);
    void delete(T obj);
    void update(T obj);
    T findById(String id);
    List<T> findAll();


}
