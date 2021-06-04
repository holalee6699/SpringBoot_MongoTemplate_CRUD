package com.github.holalee.dao.impl;

import com.github.holalee.dao.EmployeeQueryBean;
import com.github.holalee.domain.MongoBean;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class EmployeeQueryBeanImpl implements EmployeeQueryBean {
    @Override
    public List paging(Integer pageIndex, Integer pageSize) {
        return null;
    }

    @Override
    public List findAll(Class entityClass) {
        return null;
    }

    @Override
    public List findByFirstName(String firstName, Class entityClass) {
        return null;
    }

    @Override
    public MongoBean findOneByFirstName(String name, Class entityClass) {
        return null;
    }

    @Override
    public List find(Query query, Class entityClass) {
        return null;
    }

    @Override
    public MongoBean findOne(Query query, Class entityClass) {
        return null;
    }

    @Override
    public MongoBean findById(Object id, Class entityClass) {
        return null;
    }

    @Override
    public Long count(Query query, Class entityClass) {
        return null;
    }

    @Override
    public List findByFirstNameLike(String firstName, Class entityClass) {
        return null;
    }

    @Override
    public MongoBean findByLastNameLike(String lastName, Class entityClass) {
        return null;
    }

    @Override
    public List findBySalaryGreaterThan(int salary, Class entityClass) {
        return null;
    }

    @Override
    public Object mongoSingleTableSelete(String key, String value) {
        return null;
    }

    @Override
    public void mongoSave(Object obj) {

    }

    @Override
    public void mongoDelete(String key, String value) {

    }

    @Override
    public DeleteResult remove(Query query, Class entityClass) {
        return null;
    }

    @Override
    public UpdateResult extUpdateMulti(Query query, MongoBean mongoBean, String... updateFields) throws Exception {
        return null;
    }

    @Override
    public UpdateResult extUpdateFirst(Query query, MongoBean mongoBean, String... updateFields) throws Exception {
        return null;
    }

    @Override
    public Collection insertAll(Collection batchToSave) {
        return null;
    }

    @Override
    public MongoBean insert(MongoBean objToSave) {
        return null;
    }
}
