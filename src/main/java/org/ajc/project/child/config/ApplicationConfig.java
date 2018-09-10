package org.ajc.project.child.config;



import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages= {"org.ajc.project.child.dao"})
public class ApplicationConfig {

	@Bean
	public DataSource dataSource() {		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
		dataSource.setUsername("sa");
		dataSource.setPassword("");		
		return dataSource;
	}
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();		
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);	
		
		return jpaTransactionManager;
		
	}
		
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		
		//JPA Java Persistence API (tHE Standard Specification for accessing databases through Java Objects)
		//Hibernate Implementation of JPA is used 
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		
		//JPA vendor adapter will set SQL syntax to H2
		jpaVendorAdapter.setDatabase(Database.H2);
		
		//Database tables-setGenerateDdl(true);
		//setting this will show SQLS on logs
		jpaVendorAdapter.setShowSql(true);
		
		return jpaVendorAdapter;
	}
	
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		
/*	     <!-- JPA Persistence Context and EntityManager configuration -->
	 	<bean id="entityManagerFactory"
	         class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean" >
	         <property name="packagesToScan" value="org.formation.spring.model" />
	         <property name="dataSource" ref="dataSource" />
	         <property name="jpaVendorAdapter">
	             <bean class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter" >
	             	<property name="generateDdl" value="true" />
	 				<property name="showSql" value="true" />				
	             </bean>
	         </property>
	         <property name="jpaProperties">
	             <props>
	                 <prop key="hibernate.dialect">org.hibernate.dialect.HSQLDialect</prop>
	                 <prop key="hibernate.show_sql">true</prop>
	                 <prop key="hibernate.format_sql">false</prop>
	                 <prop key="hibernate.hbm2ddl.auto">update</prop> 			                               
	             </props>
	         </property>
	     </bean>    
		*/
		
		//Entity Manager Factory configuration is required in order to manage entities (tables) in Spring MVC
		//Entity Manager Factory will manage the life cycle of entities in Java 
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		
		//Entity manager factory requires a data source in order to store/list entities
		entityManagerFactory.setDataSource(dataSource());
		
		//Java Config Dependency injection is provided here by setting JPA Vendor Adapter (Hibernate)
		entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter());
		
		//Entity Manager Factory will scan packages in order to find entities (@Entity)
		entityManagerFactory.setPackagesToScan("org.ajc.project.child.model");
		
		//Custom properties can be set using Properties
		Properties jpaProperties = new Properties();
		jpaProperties.setProperty("hibernate.hbm2ddl.auto", "create");
		
		entityManagerFactory.setJpaProperties(jpaProperties);
		
		return entityManagerFactory;
		
	}
}
