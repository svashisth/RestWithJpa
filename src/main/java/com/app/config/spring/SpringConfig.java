package com.app.config.spring;

import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
@ComponentScan("com.app")
@EnableWebMvc
@EnableTransactionManagement
@EnableJpaRepositories("com.app.repository")
public class SpringConfig extends WebMvcConfigurerAdapter {

    @Bean
    public UrlBasedViewResolver setupViewResolver() {
	UrlBasedViewResolver resolver = new UrlBasedViewResolver();
	resolver.setPrefix("/WEB-INF/jsp/");
	resolver.setSuffix(".jsp");
	resolver.setViewClass(JstlView.class);
	return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/*");
    }

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
	BasicDataSource dataSource = new BasicDataSource();
	dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	dataSource.setUrl("jdbc:mysql://localhost:3306/sakila");
	dataSource.setUsername("root");
	dataSource.setPassword("root");
	return dataSource;
    }

   /* @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
	LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
	sessionBuilder.scanPackages("com.app.model");
	sessionBuilder.addProperties(getHibernateProperties());
	return sessionBuilder.buildSessionFactory();
    }
    
    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
	return new HibernateTransactionManager(sessionFactory);
    }*/

    private Properties getHibernateProperties() {
	Properties properties = new Properties();
	properties.put("hibernate.show_sql", "true");
	properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
	return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
	JpaTransactionManager transactionManager = new JpaTransactionManager();
	transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
	return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
	entityManagerFactoryBean.setDataSource(getDataSource());
	entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
	entityManagerFactoryBean.setPackagesToScan("com.app.model");
	entityManagerFactoryBean.setJpaProperties(getHibernateProperties());

	return entityManagerFactoryBean;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {	
	super.configureMessageConverters(converters);
        converters.add(new MappingJackson2HttpMessageConverter());
    }
}
