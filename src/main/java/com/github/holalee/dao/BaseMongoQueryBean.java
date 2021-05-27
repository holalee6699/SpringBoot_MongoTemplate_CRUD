package com.github.holalee.dao;

import com.github.holalee.domain.MongoBean;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Collection;
import java.util.List;

/**
 * Basic CURD Interface
 */
public interface BaseMongoQueryBean<T> {

    //*************************************     查询      ************************************

    /**
     * Paging query
     * @param pageIndex
     * @param pageSize
     * @param <T>
     * @return
     */
    <T extends MongoBean> List<T> paging(Integer pageIndex, Integer pageSize);


//************************************************      查询      **************************************************
    /**
     * 查询所有数据
     */
    <T extends MongoBean> List<T> findAll(Class<T> entityClass);

    /**
     * 查询满足条件记录
     *
     * param: firstName
     */
    <T extends MongoBean> List<T> findByFirstName(String firstName, Class<T> entityClass);

    /**
     * 查询满足条件第一条记录
     */
    <T extends MongoBean> T findOneByFirstName(String name, Class<T> entityClass);

    /**
     * 查询满足条件多条记录
     * @param query
     * @param entityClass
     * @return java.util.List<T>
     */
    <T extends MongoBean> List<T> find(Query query, Class<T> entityClass);

    /**
     * 查询第一条
     * @param query
     * @param entityClass
     * @return T
     */
    <T extends MongoBean> T findOne(Query query, Class<T> entityClass);

    /**
     * 根据主键查询
     * @param id
     * @param entityClass
     * @return T
     */
    <T extends MongoBean> T findById(Object id, Class<T> entityClass);

    /**
     * 查询总数
     * @param query
     * @param entityClass
     * @return long
     */
    <T extends MongoBean> Long count(Query query, Class<T> entityClass);

    /**
     * 根据name模糊查询
     * "firstName"  --> database property (not related to java code)
     * select * from employee where lastName like %Gurung% limit 1 --> (case insensitive)
     */
    <T extends MongoBean> List<T> findByFirstNameLike(String firstName, Class<T> entityClass);

    /**
     * 根据lastName模糊查询
     */
    <T extends MongoBean> T findByLastNameLike(String lastName, Class<T> entityClass);

    /**
     * findBySalaryGreaterThan
     * @param salary
     * @param entityClass
     * @param <T>
     * @return
     */
    <T extends MongoBean> List<T> findBySalaryGreaterThan(int salary, Class<T> entityClass);

    T mongoSingleTableSelete(String key,String value);

    //**********************************************        插入      **************************************************
    /**
     * 插入数据
     * @param objToSave
     * @return T
     */
    <T extends MongoBean> T insert(T objToSave);

    /**
     * 批量插入数据
     * @param batchToSave
     * @return java.util.Collection<T>
     */
    <T extends MongoBean> Collection<T> insertAll(Collection<? extends T> batchToSave);

    void mongoSave(T obj);

    //**********************************************        更新      **************************************************
    /**
     * 更新第一条
     * @param query 查询条件
     * @param mongoBean 要更新的实体
     * @param updateFields
     * @return com.mongodb.client.result.UpdateResult
     */
    <T extends MongoBean> UpdateResult extUpdateFirst(Query query, T mongoBean, String... updateFields) throws Exception;

    /**
     * 批量更新
     * @param query 查询条件
     * @param mongoBean 要更新的实体
     * @param updateFields 要更新的字段（有参数时，更新指定的字段；无此参数时，更新mongoBean所有不为空的字段）
     * @return com.mongodb.client.result.UpdateResult
     */
    <T extends MongoBean> UpdateResult extUpdateMulti(Query query, T mongoBean,String... updateFields) throws Exception;

    //**********************************************        删除      **************************************************
    /**
     * 条件删除
     * @param query
     * @param entityClass
     * @return
     */
    <T extends MongoBean> DeleteResult remove(Query query, Class<T> entityClass);

    void mongoDelete(String key,String value);

    /**
     * 执行游标查询
     * @param query 查询器
     * @param entityClass 查询实体
     * @param batchSize 批次大小
     * @param pageNum 当前页
     * @param pageSize 每次大小
     * @param executor 执行器
     * @return void
     */
//    <T extends MongoBean> void extCursorQueryExe(Query query, Class<T> entityClass, Integer batchSize, Integer pageNum, Integer pageSize, Executor<T> executor) throws Exception;
}
