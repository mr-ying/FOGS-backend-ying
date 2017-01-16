package FinTechOne.FOGS.resource;

import FinTechOne.FOGS.controller.SalesContractController;
import FinTechOne.FOGS.domain.SalesContract;
import FinTechOne.FOGS.repository.SalesContractRepository;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class SalesContractResourceAssembler extends ResourceAssemblerSupport<SalesContract, Resource> {

    public SalesContractResourceAssembler() {
       super(SalesContractController.class, Resource.class);
    }


    @Override
    public Resource toResource(SalesContract salesContract){
        return new Resource<SalesContract>(salesContract, linkTo(methodOn(SalesContractRepository.class).getOne(salesContract.getIdntty())).withSelfRel());
    }

    @Override
    public List<Resource> toResources(Iterable<? extends SalesContract> salesContracts) {
        List<Resource> resources = new ArrayList<Resource>();
        for(SalesContract salesContract : salesContracts) {
            Link link = linkTo(methodOn(SalesContractRepository.class).getOne(salesContract.getIdntty()
            )).withSelfRel();
            resources.add(new Resource<SalesContract>(salesContract, link));
        }
        return resources;
    }

}
