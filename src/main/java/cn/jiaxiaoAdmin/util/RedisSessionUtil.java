package cn.jiaxiaoAdmin.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
@Component
public class RedisSessionUtil {

	@Resource
	private RedisTemplate<Object, Object> redisTemplate;
	
	/**
	 * 根据sessionid得到session对象
	 * @param sessionId
	 * @return session对象
	 */
	public Object getSession(String sessionId){
		Object object = null;
		object = redisTemplate.opsForValue().get(SerializeUtil.serialize(sessionId));
		
		if(object == null){
			return null;
		}
		
		object = toObject((byte[]) object);
		
		return object;
	}
	/**
	 * 根据sessionid得到session对象 并且延期timeout秒
	 * @param sessionId 需要得到的session对象sessionID
	 * @param timeout session延期时间 秒
	 * @return session对象
	 */
	public Object getSession(String sessionId,int timeout){
		Object object = null;
		object = redisTemplate.opsForValue().get(SerializeUtil.serialize(sessionId));
		
		redisTemplate.opsForValue().set(SerializeUtil.serialize(sessionId), object, timeout, TimeUnit.SECONDS);
		
		if(object==null){
			return null;
		}
		
		object = toObject((byte[]) object);
		
		
		return object;
	}
	
	
		
	
	
	/**
	 * 设置session
	 * @param sessionId sessionid
	 * @param value session值
	 * @param timeout session失效时间 单位秒
	 */
	public void setSession(String sessionId,Object value,int timeout) throws Exception{
	
		try {
			redisTemplate.opsForValue().set(SerializeUtil.serialize(sessionId), toByteArray(value), timeout, TimeUnit.SECONDS);
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	/**
	 * @Description: 移除session
	 * @Title: removeSession 
	 * @param sessionId
	 * @throws Exception
	 */
	public void removeSession(String sessionId) throws Exception{
		
		try {
			redisTemplate.delete(SerializeUtil.serialize(sessionId));
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	/**
	 * 描述 : byte[]转Object
	 */
	private Object toObject(byte[] bytes) {
		Object obj = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bis);
			obj = ois.readObject();
			ois.close();
			bis.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return obj;
	}
	/**
	 * 描述 : Object转byte[]>
	 */
	private byte[] toByteArray(Object obj) {
		byte[] bytes = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			oos.flush();
			bytes = bos.toByteArray();
			oos.close();
			bos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return bytes;
	}
}
