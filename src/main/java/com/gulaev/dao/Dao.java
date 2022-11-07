package com.gulaev.dao;

import com.gulaev.models.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface Dao<T extends Model> {

    Logger log = LoggerFactory.getLogger(Dao.class);

    T getById(int id);

    List<T> loadAll();

    void deleteById(int id);

    void update(int id, T t);
}
