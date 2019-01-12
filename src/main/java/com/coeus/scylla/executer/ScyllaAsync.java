package com.coeus.scylla.executer;


import com.coeus.scylla.ScyllaClassLoader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Future;

public class ScyllaAsync {

    public static Future execute(Class<?> clazz, String method, Object... params) throws ReflectiveOperationException {
        if (method == null) {
            throw new NullPointerException("Method name can not be null");
        }
        String className = clazz.getName();
        try {
            Constructor constructor = clazz.getDeclaredConstructor();
            Object instance = constructor.newInstance();
            Method executeMethod = ScyllaClassLoader.getMethod(clazz, method, params);
            if (executeMethod == null) {
                StringBuilder builder = new StringBuilder();
                if (params == null || params.length == 0){
                    builder.append("()");
                } else {
                    builder.append("(");
                    for (int i = 0;i < params.length;i++){
                        builder.append(params[i].getClass().getName());
                        if (i < params.length - 1){
                            builder.append(",");
                        }
                    }
                    builder.append(")");
                }
                throw new NoSuchMethodException("Please check the method name or parameter and no corresponding method is found"
                        + ", method: " + className + "." + method + builder.toString());
            }
            return execute(instance, executeMethod, params);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new ReflectiveOperationException("Instantiation" + className + "error", e);
        }
    }

    public static Future execute(Class<?> clazz, String method) throws ReflectiveOperationException {
        return execute(clazz, method, "test");
    }

    private static Future execute(Object instance, Method method, Object[] params) {
        return ScyllaPoolExecuter.submit(instance, method, params);
    }

    public static void main(String[] args) throws ReflectiveOperationException {
        ScyllaAsync.execute(ScyllaAsync.class, "test");
    }
}
