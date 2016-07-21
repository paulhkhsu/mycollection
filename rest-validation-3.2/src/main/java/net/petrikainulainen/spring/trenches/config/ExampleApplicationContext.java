package net.petrikainulainen.spring.trenches.config;

import net.petrikainulainen.spring.trenches.comment.interceptor.LoggingInterceptor;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * @author Petri Kainulainen
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
        "net.petrikainulainen.spring.trenches.comment.controller"
})
//@Import({MessageContext.class, TestMessageContext.class})
@Import({MessageContext.class})
@PropertySource("classpath:application.properties")
public class ExampleApplicationContext extends WebMvcConfigurerAdapter {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    	System.out.println("here2");
    	configurer.enable();
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        System.out.println("here3");
        PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer();
        properties.setLocation(new ClassPathResource( "application.properties" ));
        properties.setIgnoreResourceNotFound(false);

        return properties;
    }

	public void configureContentNegotiation(
			ContentNegotiationConfigurer configurer) {
		System.out.println("ContentNegotiationConfigurer");
		configurer.defaultContentType(MediaType.APPLICATION_XML);
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(new LoggingInterceptor());
	} 
 }
