package com.globalroam.chenfei.yunCweb.redis.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

import com.globalroam.chenfei.yunCweb.domain.Userinfo;
import com.globalroam.chenfei.yunCweb.redis.dao.UserRedisDao;
/**
 * 
 * @author Chen.f 
 * @createTime：2015年9月17日 下午5:40:23 
 *
 */
@Repository
public class UserRedisDaoImpl implements UserRedisDao{
	
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Override
	public void putUser(final List<Userinfo> userList) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
		     public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException { 
		         RedisSerializer<String> redisSerializer = redisTemplate .getStringSerializer(); 
		         byte[] key = redisSerializer.serialize(userList.get(0).getUserId());
		         byte[] value = redisSerializer.serialize(userList.get(0).getUserName()); 
		         return redisConnection.setNX(key, value); } }); 
		}

	@Override
	public String getUserName(final String keyId) {
		 String result = redisTemplate.execute(new RedisCallback<String>() {  
	            public String doInRedis(RedisConnection connection)  
	                    throws DataAccessException {  
	                RedisSerializer<String> serializer = redisTemplate .getStringSerializer();
	                byte[] key = serializer.serialize(keyId);  
	                byte[] value = connection.get(key);  
	                if (value == null) {  
	                    return null;  
	                }  
	                String name = serializer.deserialize(value);  
	                return name;  
	            }  
	        });  
	        return result;  
	}
		
	}

