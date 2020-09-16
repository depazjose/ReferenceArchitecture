package com.mdt.architecture.applications.configuration;

import java.time.Duration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisConf = new RedisStandaloneConfiguration();
        redisConf.setHostName("localhost");
        redisConf.setPort(6379);
        redisConf.setDatabase(0);
        return new LettuceConnectionFactory(redisConf);
    }

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(
                Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration
                .defaultCacheConfig();
        redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(
                RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(new GenericJackson2JsonRedisSerializer(om))
        ).entryTtl(Duration.ofMillis(60000));

        return redisCacheConfiguration;
        //return RedisCacheConfiguration
        //        .defaultCacheConfig()
        //        .disableCachingNullValues()
        //        .serializeKeysWith( RedisSerializationContext
        //        .SerializationPair
        //        .fromSerializer(jackson2JsonRedisSerializer))
        //        .entryTtl(Duration.ofMillis(60000));
    }

    @Bean
    public RedisCacheManager cacheManager() {
        return RedisCacheManager
                .builder(redisConnectionFactory())
                .cacheDefaults(redisCacheConfiguration())
                .transactionAware()
                .build();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer(getClass().getClassLoader());

        template.setDefaultSerializer(serializer);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.afterPropertiesSet();


        //Use Jackson 2Json RedisSerializer to serialize and deserialize the value of redis (default JDK serialization)
        //Jackson2JsonRedisSerializer jacksonSerial = new Jackson2JsonRedisSerializer<>(Object.class);

        //ObjectMapper om = new ObjectMapper();
        // Specify the fields, fields, get and set to be serialized, and the range of modifiers. ANY includes both private and public.
        //om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // Specify the type of serialized input, the class must be non-final modified, final modified classes, such as String,Integer, etc., will run out of exceptions
        //om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        //om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //jacksonSerial.setObjectMapper(om);

        // Values are serialized using json
        //template.setValueSerializer(jacksonSerial);
        //Use String RedisSerializer to serialize and deserialize the key value of redis
        //template.setKeySerializer(new StringRedisSerializer());

        // Setting hash key and value serialization mode
        //template.setHashKeySerializer(new StringRedisSerializer());
        //template.setHashValueSerializer(jacksonSerial);
        //template.afterPropertiesSet();

        return template;
    }

}
