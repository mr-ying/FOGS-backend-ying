package FinTechOne.FOGS.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@ComponentScan(basePackages = {
        "FinTechOne.FOGS.configuration",
        "FinTechOne.FOGS.component",
        "FinTechOne.FOGS.controller",
        "FinTechOne.FOGS.storage",
        "FinTechOne.FOGS.restEventHandler"
})
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(Application.class, args);


        listBeanNames( ctx.getBeanDefinitionNames());

    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    private static Class<Application> applicationClass = Application.class;
    public static void listBeanNames(String[] beanNames){
        System.out.println("Spring Boot instantiated beans:");
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }
}
