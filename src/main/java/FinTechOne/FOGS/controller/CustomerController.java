package FinTechOne.FOGS.controller;

import FinTechOne.FOGS.domain.Customer;
import FinTechOne.FOGS.repository.CustomerRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomerController {
    @Autowired
    private CustomerRepository repo;
    @Autowired
    EntityLinks entityLinks;

    static final String entity = "/Customer";

    @RequestMapping(value=MainController.springDataRestBasePath + entity + MainController.filter , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PagedResources<Resource<Customer>>> filterCustomer(
            @QuerydslPredicate(root = Customer.class) Predicate predicate,
            Pageable pageable,
            PagedResourcesAssembler<Customer> assembler){
        return new ResponseEntity<>(ResourceUtilities.getPagedResources(repo, predicate, pageable, assembler, entityLinks), HttpStatus.OK);
    }
}
