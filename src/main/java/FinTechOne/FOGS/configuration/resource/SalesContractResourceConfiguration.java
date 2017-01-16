package FinTechOne.FOGS.configuration.resource;

import FinTechOne.FOGS.controller.SalesContractController;
import FinTechOne.FOGS.domain.SalesContractView;
import FinTechOne.FOGS.domain.SalesContract_Tx;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Configuration
public class SalesContractResourceConfiguration {
    @Bean
    public ResourceProcessor<Resource<SalesContract_Tx>> SalesContractProcessor() {

        return new ResourceProcessor<Resource<SalesContract_Tx>>() {

            @Override
            public Resource<SalesContract_Tx> process(Resource<SalesContract_Tx> resource) {
                Link link = linkTo(methodOn(SalesContractController.class).approve(resource.getContent().getIdntty())).withRel("approve");
                resource.add(link);
                return resource;
            }
        };
    }
    @Bean
    public ResourceProcessor<Resource<SalesContractView>> SalesContractViewProcessor() {

        return new ResourceProcessor<Resource<SalesContractView>>() {

            @Override
            public Resource<SalesContractView> process(Resource<SalesContractView> resource) {
                if (!resource.getContent().isMaster()) {
                    Link link = linkTo(methodOn(SalesContractController.class).approve(resource.getContent().getIdntty())).withRel("approve");
                    resource.add(link);
                }
                return resource;
            }
        };
    }
}
