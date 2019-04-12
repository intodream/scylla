package com.coeus.scylla.executer;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * method invoke
 */
public class ScyllaExecuter<T> implements Callable<T> {

    private Object instance;        //class instance
    private Method method;          //Method
    private Object[] params;        //method params

    ScyllaExecuter(Object instance, Method method, Object[] params){
        this.instance = instance;
        this.method = method;
        this.params = params;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T call(){
        if (!method.isAccessible()){
            method.setAccessible(Boolean.TRUE);
        }

        try{
            return (T) method.invoke(this.instance, this.params);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
