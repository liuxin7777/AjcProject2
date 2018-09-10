package org.ajc.project.child.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		/*
		 * <listener>
		 * 		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
		 * </listener>
		 * 
		 * <servlet> 
		 * 		<servlet-name>dispatcherServlet</servlet-name>
		 * 		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class> 
		 * 		<init-param> 
		 * 			<param-name>contextConfigLocation</param-name>
		 * 			<param-value>/WEB-INF/mvc-config.xml</param-value> 
		 * 		</init-param>
		 * 		<load-on-startup>1</load-on-startup> 
		 * </servlet>
		 * 
		 * <servlet-mapping> 
		 * 		<servlet-name>dispatcherServlet</servlet-name>
		 * 		<url-pattern>/</url-pattern> 
		 * </servlet-mapping>
		 */
		
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		
		rootContext.register(WebConfig.class);
		
		container.addListener(new ContextLoaderListener(rootContext));
		
		DispatcherServlet dispatcherServlet = new DispatcherServlet(rootContext);
		
		ServletRegistration.Dynamic registration = container.addServlet("dispatcherServlet", dispatcherServlet);
		
		registration.setLoadOnStartup(1);
		
		registration.addMapping("/");
	}

}
