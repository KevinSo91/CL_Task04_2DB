package mainPackage.configuration;

import java.util.HashMap;
import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "usersEntityManagerFactory",
        basePackages = "mainPackage.entities.user",
        transactionManagerRef = "usersTransactionManager"
)
public class UserConfigDB {
	
	@Primary
	@Bean(name = "usersDataSourceProps")
	@ConfigurationProperties(prefix = "spring.datasource-user")
	public DataSourceProperties dataSourceProperties(){
	    return new DataSourceProperties();
	}
	
	
	@Primary
	@Bean(name = "usersDataSource")
	@ConfigurationProperties(prefix = "spring.datasource-user")
	public DataSource usersDataSource(@Qualifier("usersDataSourceProps") DataSourceProperties properties) {
		return properties.initializeDataSourceBuilder().build();
	}
	
	
	@Primary
	@Bean(name = "usersEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean usersEntityManagerFactory(
	        EntityManagerFactoryBuilder builder,
	        @Qualifier("usersDataSource") DataSource dataSource
	)
	{
	    return builder
	            .dataSource(dataSource)
	            .packages("com.mainPackage.entities.user")
	            .persistenceUnit("users")
	            .build();
	}	
	
	
	@Primary
	@Bean(name = "usersTransactionManager")
	@ConfigurationProperties("spring.jpa")
	public PlatformTransactionManager transactionManager(
	        @Qualifier("usersEntityManagerFactory") 
	        LocalContainerEntityManagerFactoryBean entityManagerFactory
	)
	{
	    return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
	}
	
	
}