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
        entityManagerFactoryRef = "productsEntityManagerFactory",
        basePackages = "mainPackage.entities.product",
        transactionManagerRef = "productsTransactionManager"
)
public class ProductConfigDB {
	
	@Primary
	@Bean(name = "productsDataSourceProps")
	@ConfigurationProperties(prefix = "spring.datasource-product")
	public DataSourceProperties dataSourceProperties(){
	    return new DataSourceProperties();
	}
	
	
	@Primary
	@Bean(name = "productsDataSource")
	@ConfigurationProperties(prefix = "spring.datasource-product")
	public DataSource productsDataSource(@Qualifier("productsDataSourceProps") DataSourceProperties properties) {
		return properties.initializeDataSourceBuilder().build();
	}
	
	
	@Primary
	@Bean(name = "productsEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean productsEntityManagerFactory(
	        EntityManagerFactoryBuilder builder,
	        @Qualifier("productsDataSource") DataSource dataSource
	)
	{
	    return builder
	            .dataSource(dataSource)
	            .packages("com.mainPackage.entities.product")
	            .persistenceUnit("products")
	            .build();
	}	
	
	
	@Primary
	@Bean(name = "productsTransactionManager")
	@ConfigurationProperties("spring.jpa")
	public PlatformTransactionManager transactionManager(
	        @Qualifier("productsEntityManagerFactory") 
	        LocalContainerEntityManagerFactoryBean entityManagerFactory
	)
	{
	    return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
	}
	
	
	@Bean
	public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
	   return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
	}
}
