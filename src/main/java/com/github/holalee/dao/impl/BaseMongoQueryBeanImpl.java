package com.github.holalee.dao.impl;

import com.github.holalee.dao.BaseMongoQueryBean;
import com.github.holalee.domain.MongoBean;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BaseMongoQueryBeanImpl<T> implements BaseMongoQueryBean<T> {

    @Autowired
    MongoTemplate mongoTemplate;

    private Class<T> obj;

    @Override
    public <T extends MongoBean> List<T> paging(Integer pageIndex, Integer pageSize) {
        Query query = new Query();
        Pageable pageable = PageRequest.of(pageIndex ,pageSize);
        query.with(pageable);
        return (List<T>) mongoTemplate.find(query, obj);
    }

    @Override
    public <T extends MongoBean> List<T> findAll(Class<T> entityClass) {
        return mongoTemplate.findAll(entityClass);
    }

    @Override
    public <T extends MongoBean> List<T> findByFirstName(String firstName, Class<T> entityClass) {
        Query query = new Query();
        query.addCriteria(Criteria.where("firstName").is(firstName));
        return new ArrayList<>(mongoTemplate.find(query,entityClass));
    }

    @Override
    public <T extends MongoBean> T findOneByFirstName(String name, Class<T> entityClass) {
        Query query = new Query();
        query.addCriteria(Criteria.where("firstName").is(name));
        return mongoTemplate.findOne(query, entityClass);
    }

    @Override
    public <T extends MongoBean> List<T> find(Query query, Class<T> entityClass) {
        return mongoTemplate.find(query, entityClass);
    }

    @Override
    public <T extends MongoBean> T findOne(Query query, Class<T> entityClass) {
        return mongoTemplate.findOne(query, entityClass);
    }

    @Override
    public <T extends MongoBean> T findById(Object id, Class<T> entityClass) {
        return mongoTemplate.findById(id, entityClass);
    }

    @Override
    public <T extends MongoBean> Long count(Query query, Class<T> entityClass) {
        return mongoTemplate.count(query, entityClass);
    }

    @Override
    public <T extends MongoBean> List<T> findByFirstNameLike(String firstName, Class<T> entityClass) {
        Query query = new Query();
        query.addCriteria(Criteria.where("firstName").regex(firstName,"i"));
        return mongoTemplate.find(query,entityClass);
    }

    @Override
    public <T extends MongoBean> T findByLastNameLike(String lastName, Class<T> entityClass) {
        Query query = new Query();
        query.addCriteria(Criteria.where("lastName").regex(lastName,"i"));
        return mongoTemplate.findOne(query, entityClass);
    }

    @Override
    public <T extends MongoBean> List<T> findBySalaryGreaterThan(int salary, Class<T> entityClass) {
        Query query = new Query();
        query.addCriteria(Criteria.where("salary").gt(salary));
        query.with(Sort.by(Sort.Direction.ASC, "firstName"));
        query.with(Sort.by(new Sort.Order(Sort.Direction.DESC, "firstName").ignoreCase()));
        return mongoTemplate.find(query, entityClass);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T mongoSingleTableSelete(String key, String value) {
        Query query = new Query(Criteria.where(key).is(value));
        Type t = this.getClass().getGenericSuperclass();
        return (T) mongoTemplate.findOne(query, obj);
    }

    @Override
    public <T extends MongoBean> T insert(T objToSave) {
        return mongoTemplate.insert(objToSave);
    }

    @Override
    public <T extends MongoBean> Collection<T> insertAll(Collection<? extends T> batchToSave) {
        return mongoTemplate.insertAll(batchToSave);
    }

    @Override
    public void mongoSave(T obj) {
        mongoTemplate.save(obj);
    }

    @Override
    public <T extends MongoBean> UpdateResult extUpdateFirst(Query query, T mongoBean, String... updateFields) throws Exception {
        // todo update needs to fix
        Update update = new Update();
        return mongoTemplate.updateFirst(query,update,mongoBean.getClass());
    }

    @Override
    public <T extends MongoBean> UpdateResult extUpdateMulti(Query query, T mongoBean, String... updateFields) throws Exception {
        // todo update needs to fix
        Update update = new Update();
        return mongoTemplate.updateMulti(query, update, mongoBean.getClass());
    }

    @Override
    public <T extends MongoBean> DeleteResult remove(Query query, Class<T> entityClass) {
        return mongoTemplate.remove(query, entityClass);
    }

    @Override
    public void mongoDelete(String key, String value) {
        Query query = new Query(Criteria.where(key).is(value));
        mongoTemplate.remove(query,obj);
    }

//    @Override
//    public <T extends MongoBean> void extCursorQueryExe(Query query, Class<T> entityClass, Integer batchSize, Integer pageNum, Integer pageSize, Executor<T> executor) throws Exception {
//        if(executor==null){
//            return ;
//        }
//        try (MongoCursor<Document> cursor = this.extGetMongoCursor(query,entityClass,batchSize,pageNum,pageSize)) {
//            if(cursor==null){
//                return ;
//            }
//            T model;
//            while (cursor.hasNext()) {
//                model = mongoConverter.read(entityClass, cursor.next());
//                executor.invoke(model);
//            }
//        } catch (Exception e) {
//            throw e;
//        }
//    }

    /**
     * 获取mongo游标（需要手动关闭）
     * @param query 查询对象
     * @param entityClass 查询实体
     * @param batchSize 批次大小（默认1000，需大于0）
     * @param pageNum 当前页数
     * @param pageSize 每页大小
     * @return com.mongodb.client.MongoCursor<org.bson.Document>
     */
//    <T extends MongoBean> MongoCursor<Document> extGetMongoCursor(Query query, Class<T> entityClass, Integer batchSize, Integer pageNum, Integer pageSize){
//        if(query==null || entityClass==null){
//            return null;
//        }
//        MongoCollection<Document> collection=mongoTemplate.getCollection(mongoTemplate.getCollectionName(entityClass));
//        FindIterable<Document> findIterable=collection.find(query.getQueryObject());
//        ////----------填充游标属性----------
//        //（1）游标不超时
//        findIterable.noCursorTimeout(true);
//        //（2）批次拉取大小（默认1000）
//        if(batchSize==null || batchSize<=0){
//            batchSize=DEFAULT_CURSOR_BATCH_SIZE;
//        }
//        findIterable.batchSize(batchSize);
//        //（3）排序
//        findIterable.sort(query.getSortObject());
//        //（4）跳过记录数
//        if(pageNum!=null && pageSize!=null){
//            findIterable.skip((pageNum - 1) * pageSize);
//            findIterable.limit(pageSize);
//        }
//
//        return findIterable.cursor();
//    }
}
