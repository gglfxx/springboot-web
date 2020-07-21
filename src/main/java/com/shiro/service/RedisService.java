package com.shiro.service;

import java.util.List;

public interface RedisService {
     String get(String key);
     String setex(String key, int seconds, String value);
     Long del(String key);
     Boolean exists(String key);
     <T> void setList(String key, List<T> list);
     <T> List<T> getList(String key);
     void setObject(String key,Object value,int seconds);
     Object getObject(String key);
     Long expire(String key,int seconds);
}
