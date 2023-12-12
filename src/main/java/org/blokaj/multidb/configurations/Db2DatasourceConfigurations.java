package org.blokaj.multidb.configurations;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@RequiredArgsConstructor
@EnableJpaRepositories (
        basePackages = "org.blokaj.multidb.repositories.db2",
        entityManagerFactoryRef = "db2ManagerFactory",
        transactionManagerRef = "db2TransactionManager"
)
public class Db2DatasourceConfigurations {

    private final Environment environment;

    @Bean(name = "db2ManagerFactory")
    public LocalContainerEntityManagerFactoryBean db2EntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(db2DataSource());
        em.setPackagesToScan("org.blokaj.multidb.models.entities.db2");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(db2Properties());

        return em;
    }

    @Bean(name = "db2TransactionManager")
    public PlatformTransactionManager db2TransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(db2EntityManagerFactory().getObject());

        return transactionManager;
    }

    @Bean(name = "db2DataSource")
    @ConfigurationProperties(prefix="app.db2.datasource")
    public DataSource db2DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "db2Properties")
    public Properties db2Properties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("app.db2.datasource.hibernate.hbm2ddl.auto"));
        properties.setProperty("hibernate.show_sql", environment.getProperty("app.db2.datasource.hibernate.show_sql"));
        properties.setProperty("hibernate.dialect", environment.getProperty("app.db2.datasource.hibernate.dialect"));

        return properties;
    }
}
