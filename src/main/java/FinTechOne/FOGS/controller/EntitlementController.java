package FinTechOne.FOGS.controller;

import com.querydsl.core.types.Predicate;
import FinTechOne.FOGS.domain.Entitlement;
import FinTechOne.FOGS.repository.EntitlementRepository;
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
public class EntitlementController {
    @Autowired
    private EntitlementRepository repo;
    @Autowired
    EntityLinks entityLinks;

    static final String entity = "/Entitlement";

    @RequestMapping(value=MainController.springDataRestBasePath + entity + MainController.filter , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PagedResources<Resource<Entitlement>>> filterEntitlement(
            @QuerydslPredicate(root = Entitlement.class) Predicate predicate,
            Pageable pageable,
            PagedResourcesAssembler<Entitlement> assembler){
        return new ResponseEntity<>(ResourceUtilities.getPagedResources(repo, predicate, pageable, assembler, entityLinks), HttpStatus.OK);
    }

}
