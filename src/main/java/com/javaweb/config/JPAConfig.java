package com.javaweb.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//sử dụng config để chạy những config trong spring
@Configuration
@EnableJpaRepositories(basePackages = {"com.javaweb.repository"})
//Cho phép quản lý tự động transaction
@EnableTransactionManagement
public class JPAConfig {
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		//method setDataSource dùng để thiết lập nguồn dữ liệu, cấu hình kết nối tới Database
		em.setDataSource(dataSource());
		//Để làm việc với JPA ta cần có 1 file persistence-data 
		//là 1 file chứa các nhóm enity
		//là file dùng để entity của các bảng sao
		//và các bảng gốc trong database giao tiếp với nhau
		em.setPersistenceUnitName("persistence-data");
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());
		return em;
	}
	
	@Bean
	//quản lý các transaction
	JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		//Gắn EntityManagerFactory vào transactionManager, để quản lý các entity và thực hiện giao dịch (transaction) với cơ sở dữ liệu.
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		dataSource.setUrl("jdbc:sqlserver://DESKTOP-5OGVL8L\\SQLEXPRESS:1433;databaseName=SpringMVC_DB;encrypt=true;trustServerCertificate=true;");
		dataSource.setUsername("sa");
		dataSource.setPassword("123456");
		return dataSource;
	}
	
	Properties additionalProperties() {
		Properties properties = new Properties();
		//properties.setProperty("hibernate.hbm2ddl.auto", "update");
		//lệnh creat để tạo database từ java class entity khi chưa có databse
		properties.setProperty("hibernate.hbm2ddl.auto", "create");
		//sau khi có database, đã ổn định và có data trong databse thì ta comment lệnh create lại và dùng lệnh none bên dưới
		//properties.setProperty("hibernate.hbm2ddl.auto", "none");
		//enable @ManyToMany(fetch = FetchType.LAZY)
		properties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
		return properties;
	}
}
