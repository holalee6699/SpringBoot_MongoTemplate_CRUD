package com.github.holalee.dao;

/**
 * Mongo Cursor Actuator
 * @param <T>
 */
public interface Executor<T> {

    /**
     * Execution
     * @param cModel Execution entity class
     * @throws Exception
     * @return void
     */
    void invoke(T cModel) throws Exception;
}
