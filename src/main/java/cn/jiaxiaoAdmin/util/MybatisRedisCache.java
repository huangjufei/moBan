package cn.jiaxiaoAdmin.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.annotation.Resource;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class MybatisRedisCache implements Cache {

	@Resource
	private RedisTemplate<Object, Object> redisTemplate;
	private String name = "";

	public RedisTemplate<Object, Object> getredisTemplate() {
		return redisTemplate;
	}

	public void setredisTemplate(RedisTemplate<Object, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Object getNativeCache() {
		return this.redisTemplate;
	}

	@Override
	public ValueWrapper get(Object key) {
		Object object = null;
		object = redisTemplate.opsForValue().get(SerializeUtil.serialize(key));
		if (object == null) {
			return null;
		}

		object = SerializeUtil.unSerialize((byte[]) object);
		return (object != null ? new SimpleValueWrapper(object) : null);
	}

	@Override
	public <T> T get(Object key, Class<T> type) {
		Object object = null;
		object = redisTemplate.opsForValue().get(SerializeUtil.serialize(key));
		if (object == null) {
			return null;
		} else {
			try {
				T t_object = (T) object;
				return t_object;
			} catch (Exception e) {
				return null;
			}
		}
	}

	@Override
	public void put(Object key, Object value) {
		final long liveTime = 86400;
		redisTemplate.opsForValue().set(SerializeUtil.serialize(key), SerializeUtil.serialize(value));
	}

	@Override
	public ValueWrapper putIfAbsent(Object key, Object value) {
		Object existingValue = redisTemplate.opsForValue().get(SerializeUtil.serialize(key));
		if (existingValue == null) {
			redisTemplate.opsForValue().setIfAbsent(SerializeUtil.serialize(key), SerializeUtil.serialize(value));
			return null;
		} else {
			return new SimpleValueWrapper(existingValue);
		}
	}

	@Override
	public void evict(Object key) {
		redisTemplate.delete(SerializeUtil.serialize(key));
	}

	@Override
	public void clear() {
		redisTemplate.execute(new RedisCallback<String>() {
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				connection.flushDb();
				return "ok";
			}
		});
	}

}

// 自定义的序列化和反序列化
class SerializeUtil {

	public static byte[] serialize(Object object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			// 先得到对象outputStream对象.
			oos = new ObjectOutputStream(new ByteArrayOutputStream());
			oos.writeObject(object);
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != oos) {
					oos.close();
				}
				if (null != baos) {
					baos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return null;
	}

	// 反序列化
	public static Object unSerialize(byte[] bytes) {
		if (bytes == null)
			return null;
		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null;

		try {

			bais = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (null != ois) {
					ois.close();
				}
				if (null != bais) {
					bais.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
}
