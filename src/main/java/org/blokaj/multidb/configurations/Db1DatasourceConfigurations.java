package org.blokaj.multidb.configurations;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@RequiredArgsConstructor
@EnableJpaRepositories(
        basePackages = "org.blokaj.multidb.repositories.db1",
        entityManagerFactoryRef = "db1ManagerFactory",
        transactionManagerRef = "db1TransactionManager"
)
public class Db1DatasourceConfigurations {

    private final Environment environment;

    @Primary
    @Bean("db1ManagerFactory")
    public LocalContainerEntityManagerFactoryBean db1ManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(db1DataSource());
        em.setPackagesToScan("org.blokaj.multidb.models.entities.db1");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(db1Properties());

        return em;
    }

    @Primary
    @Bean(name = "db1TransactionManager")
    public PlatformTransactionManager db1TransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(db1ManagerFactory().getObject());

        return transactionManager;
    }

    @Primary
    @Bean(name = "db1DataSource")
    public DataSource db1DataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("app.db1.datasource.driver", "org.postgresql.Driver"));
        dataSource.setUrl(environment.getProperty("app.db1.datasource.jdbcUrl"));
        dataSource.setUsername(environment.getProperty("app.db1.datasource.username"));
        dataSource.setPassword(environment.getProperty("app.db1.datasource.password"));

        return dataSource;
    }

    @Primary
    @Bean(name = "db1Properties")
    public Properties db1Properties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("app.db1.datasource.hibernate.hbm2ddl.auto"));
        properties.setProperty("hibernate.show_sql", environment.getProperty("app.db1.datasource.hibernate.show_sql"));
        properties.setProperty("hibernate.dialect", environment.getProperty("app.db1.datasource.hibernate.dialect"));

        return properties;
    }

}
