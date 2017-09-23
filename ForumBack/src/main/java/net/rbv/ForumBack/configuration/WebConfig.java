package net.rbv.ForumBack.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/*Dispatcher-servlet.xml*/
@Configuration
@EnableWebMvc
@ComponentScan(basePackages="net.rbv.*")
public class WebConfig extends WebMvcConfigurerAdapter {

	public void addResourceHandlers(ResourceHandlerRegistry reg) {
		reg.addResourceHandler("/Resources/**").addResourceLocations("/WEB-INF/Resources/");
	}
}
