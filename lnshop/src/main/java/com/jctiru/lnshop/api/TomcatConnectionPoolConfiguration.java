package com.jctiru.lnshop.api;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cloud.aws.jdbc.datasource.TomcatJdbcDataSourceFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/*
 * Default connection pool is HikariCP if available (see https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/reference/htmlsingle/#boot-features-connect-to-production-database)
 * but spring-cloud-aws-jdbc uses Tomcat (see https://cloud.spring.io/spring-cloud-aws/reference/html/#data-access-with-jdbc 
 * and https://github.com/spring-cloud/spring-cloud-aws/blob/master/spring-cloud-aws-jdbc/src/main/java/org/springframework/cloud/aws/jdbc/datasource/TomcatJdbcDataSourceFactory.java).
 * 
 * Apparently, using spring-cloud-aws-autoconfigure to configure data source in property file (cloud.aws.rds.testdb) will make spring boot data source auto-configuration "ignore"
 * data source config in property file (spring.datasource.tomcat.*).
 * 
 * See below links for more info:
 * https://stackoverflow.com/questions/31372150/spring-boot-web-app-loses-ability-to-connect-to-mysql-rds-after-a-while/41667187#41667187
 * https://github.com/spring-cloud/spring-cloud-aws/issues/518
 * https://stackoverflow.com/questions/44493796/spring-boot-and-spring-cloud-aws-data-source-pool-configuration
 */

@Component
@Profile("prod")
public class TomcatConnectionPoolConfiguration implements BeanPostProcessor {

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof TomcatJdbcDataSourceFactory) {
			TomcatJdbcDataSourceFactory tomcatJdbcDataSourceFactory = (TomcatJdbcDataSourceFactory) bean;
			tomcatJdbcDataSourceFactory.setTestOnBorrow(true);
			tomcatJdbcDataSourceFactory.setValidationQuery("/* ping */ SELECT 1");
			tomcatJdbcDataSourceFactory.setValidationInterval(3600000L); // 1 hour
			tomcatJdbcDataSourceFactory.setInitialSize(5);
			tomcatJdbcDataSourceFactory.setMaxActive(10);
		}

		return bean;
	}

}
