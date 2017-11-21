package cilicili.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInterceptor())
                .addPathPatterns("/course/**")
                .addPathPatterns("/registerCourse/**")
                .addPathPatterns("/personal_center")
                .addPathPatterns("/change_password")
                .addPathPatterns("/change_personal_info")
                .addPathPatterns("/personal/**")
                .addPathPatterns("/change_personal_info");
        super.addInterceptors(registry);
    }
}
