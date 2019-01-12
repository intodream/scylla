package com.coeus.scylla;

import java.lang.reflect.Method;

/**
 * classLoader
 */
public class ScyllaClassLoader {

    public static Method getMethod(Class clazz, String method, Object[] params) {
        Method[] methods = clazz.getDeclaredMethods();
        Method result = null;
        for (Method met : methods) {
            //same name method
            if (met.getName().equals(method)) {
                int parameterCount = met.getParameterCount();
                if (params == null || params.length == 0) {
                    if (parameterCount == 0) {
                        result = met;
                        break;
                    }
                } else {
                    if (parameterCount == params.length) {
                        Class<?>[] parameterTypes = met.getParameterTypes();
                        int count = 0;
                        for (int i = 0; i < parameterCount; i++) {
                            if (!parameterTypes[i].isAssignableFrom(params[i].getClass())) {
                                break;
                            } else {
                                count++;
                            }
                        }
                        if (count == parameterCount) {
                            result = met;
                        }
                    }
                }
            }
        }
        return result;
    }
}
