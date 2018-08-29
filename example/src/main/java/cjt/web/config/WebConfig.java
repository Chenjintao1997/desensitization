package cjt.web.config;

import cjt.components.InfoMasksAdvice;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    Environment env;


    @Bean
    public InfoMasksAdvice infoMasksAdvice(){ return new InfoMasksAdvice();}
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String allowedOrigins = env.getProperty("cors.allowed-origins", "*");
        registry.addMapping("/**").allowedOrigins(allowedOrigins);
    }

//    @Bean
//    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
//        RedisTemplate<String,Object> redisTemplate = new RedisTemplate();
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//        redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer());
//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//        redisTemplate.setKeySerializer(stringRedisSerializer);
//        redisTemplate.setHashKeySerializer(stringRedisSerializer);
//        return redisTemplate;
//    }
//    @Bean
//    public Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer(){
//        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        return jackson2JsonRedisSerializer;
//    }
}
