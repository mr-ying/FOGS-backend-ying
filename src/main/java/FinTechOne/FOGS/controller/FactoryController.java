package FinTechOne.FOGS.controller;

import FinTechOne.FOGS.domain.Factory;
import FinTechOne.FOGS.repository.FactoryRepository;
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
public class FactoryController {
    @Autowired
    private FactoryRepository repo;
    @Autowired
    EntityLinks entityLinks;

    static final String entity = "/Factory";

    @RequestMapping(value=MainController.springDataRestBasePath + entity + MainController.filter , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PagedResources<Resource<Factory>>> filterFactory(
            @QuerydslPredicate(root = Factory.class) Predicate predicate,
            Pageable pageable,
            PagedResourcesAssembler<Factory> assembler){
        return new ResponseEntity<>(ResourceUtilities.getPagedResources(repo, predicate, pageable, assembler, entityLinks), HttpStatus.OK);
    }
}
