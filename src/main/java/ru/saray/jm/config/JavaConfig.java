package ru.saray.jm.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Configuration
@ComponentScan("ru.saray.jm")
@EnableWebMvc
public class JavaConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;

    public JavaConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    // внедряем applicationContext, чтобы настроить шаблонизатор
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        // задаем папку, где будут лежать представления
        templateResolver.setPrefix("/WEB-INF/views/");
        // задаем расширение представлений
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    // конфигурация представлений
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    // метод, в котором мы задаём шаблонизатор, в данном случае Thymeleaf
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }
}
