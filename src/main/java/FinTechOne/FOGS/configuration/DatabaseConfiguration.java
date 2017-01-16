package FinTechOne.FOGS.configuration;

import FinTechOne.FOGS.storage.StorageProperties;
import FinTechOne.FOGS.storage.StorageService;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@EnableTransactionManagement
//@PropertySource("classpath:configure.properties")
@Configuration
@EnableJpaRepositories(basePackages = {"FinTechOne.FOGS.repository", "FinTechOne.FOGS.repositoryImpl"})
//@EnableAutoConfiguration(exclude = {JpaRepositoriesAutoConfiguration.class})
@EnableConfigurationProperties(StorageProperties.class)
@EnableSpringConfigured
@EnableAspectJAutoProxy
@EnableEntityLinks
@EntityScan(basePackages = {"FinTechOne.FOGS.domain"})
public class DatabaseConfiguration {

//    @Autowired
//    private Environment env;

    @Value("${FOGS.database.hostname}")
    private String databaseHostName;
    @Value("${FOGS.database.port}")
    private String databasePort;
    @Value("${FOGS.database.name}")
    private String databaseName;
    @Value("${FOGS.database.username}")
    private String databaseUserName;
    @Value("${FOGS.database.password}")
    private String databasePassword;
    @Value("${FOGS.hbm2ddl.auto}")
    private String hbm2ddlAuto;

    @Bean
    public DataSource dataSource() {
        DataSource ds = new DataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        ds.setUrl("jdbc:mysql://" + env.getProperty("database.hostname") + ":"
//                + env.getProperty("database.port") + "/" + env.getProperty("database.name")
//                + "?autoReconnect=true&useSSL=false");
//        ds.setUsername(env.getProperty("database.username"));
//        ds.setPassword(env.getProperty("database.password"));
        ds.setUrl("jdbc:mysql://" + databaseHostName + ":"
                + databasePort + "/" + databaseName
                + "?autoReconnect=true&useSSL=false");
        ds.setUsername(databaseUserName);
//        ds.setPassword(getSecurePassword(env.getProperty("database.password"), passKey));
        ds.setPassword(databasePassword);
        ds.setInitialSize(5);
        ds.setMaxActive(10);
        ds.setMaxIdle(10);
        return ds;
    }
    @Bean
    public HibernateJpaDialect jpaDialect(){
        return new HibernateJpaDialect();
    }

    @Bean
    public HibernateJpaVendorAdapter jpaVendorAdapter(){
        return new HibernateJpaVendorAdapter();
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean eb = new LocalContainerEntityManagerFactoryBean();
        eb.setPackagesToScan("FinTechOne.FOGS.domain");
        eb.setDataSource(dataSource());
        eb.setJpaVendorAdapter(jpaVendorAdapter());
        eb.setJpaDialect(jpaDialect());
        Properties props = new Properties();
        props.setProperty("hibernate.hbm2ddl.auto", hbm2ddlAuto);
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect"); // for MySQL
        props.setProperty("hibernate.id.new_generator_mappings", "false"); // hibernate_sequence table is not used.
        props.setProperty("hibernate.ejb.interceptor", "FinTechOne.FOGS.hibernate.DomainInterceptor");
        eb.setJpaProperties(props);
        eb.afterPropertiesSet();

        return eb;

    }


    @Bean
    public JpaTransactionManager transactionManager(){
        JpaTransactionManager jpaTM = new JpaTransactionManager();
        jpaTM.setEntityManagerFactory(entityManagerFactory().getObject());
        jpaTM.setDataSource(dataSource());
        jpaTM.setJpaDialect(jpaDialect());
        return jpaTM;
    }
    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }

}
