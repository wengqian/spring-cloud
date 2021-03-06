package com.wq.microserviceredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MicroServiceRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroServiceRedisApplication.class, args);
    }

    /**
     * 修改redisTemplated的学列方式
     * RedisTemplate使用的是 JdkSerializationRedisSerializer
     * StringRedisTemplate使用的是 StringRedisSerializer
     * */
//	@Bean
//	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
//		redisTemplate.setConnectionFactory(redisConnectionFactory);
//
//		// 使用Jackson2JsonRedisSerialize 替换默认序列化
//		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//
//		ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//		objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//
//		jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
//
//		// 设置value的序列化规则和 key的序列化规则
//		redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
//		redisTemplate.setKeySerializer(new StringRedisSerializer());
//		redisTemplate.afterPropertiesSet();
//		return redisTemplate;
//	}
}
