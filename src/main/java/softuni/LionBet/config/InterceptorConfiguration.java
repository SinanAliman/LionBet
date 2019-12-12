package softuni.LionBet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import softuni.LionBet.web.interceptors.AuthorizationInterceptor;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
    private final AuthorizationInterceptor authorizationInterceptor;

    @Autowired
    public InterceptorConfiguration(AuthorizationInterceptor authorizationInterceptor) {
        this.authorizationInterceptor = authorizationInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.authorizationInterceptor)
                .addPathPatterns("/", "/home");
    }
}
