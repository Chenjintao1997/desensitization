package cjt.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
@EnableRedisHttpSession
public class SpringSessionConfig {

    @Autowired
    Environment env;

    @Bean
    public CookieSerializer cookieSerializer(){
        DefaultCookieSerializer bean = new DefaultCookieSerializer();
        bean.setCookieName(env.getProperty("session.cookie.cookie-name","session"));
        bean.setDomainName(env.getProperty("session.cookie.domain-name"));
        bean.setCookiePath(env.getProperty("session.cookie.path","/"));
        bean.setCookieMaxAge(env.getProperty("session.cookie.max-age",int.class,-1));
        return bean;
    }
}

