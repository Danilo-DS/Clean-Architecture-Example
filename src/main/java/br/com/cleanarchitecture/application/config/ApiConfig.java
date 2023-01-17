package br.com.cleanarchitecture.application.config;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

@Configuration
public class ApiConfig {
	
	@Bean
	BeanFactoryPostProcessor beanFactoryPostProcessor(ApplicationContext beanRegistry) {
	    return beanFactory -> {
	        genericApplicationContext(
	          (BeanDefinitionRegistry) ((AnnotationConfigServletWebServerApplicationContext) beanRegistry)
	            .getBeanFactory());
	    };
	}

	void genericApplicationContext(BeanDefinitionRegistry beanRegistry) {
	    ClassPathBeanDefinitionScanner beanScanner = new ClassPathBeanDefinitionScanner(beanRegistry);
	    beanScanner.addIncludeFilter(removeModelAndEntitiesFilter());
	    beanScanner.scan("br.com..cleanarchitecture.application.*");
	}

	static TypeFilter removeModelAndEntitiesFilter() {
	    return (MetadataReader mr, MetadataReaderFactory mrf) -> !mr.getClassMetadata()
	      .getClassName()
	      .matches(".*(DTO|Model).*");
	}
	
}
