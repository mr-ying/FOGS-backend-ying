package FinTechOne.FOGS.controller;

import FinTechOne.FOGS.domain.SalesContract;
import FinTechOne.FOGS.domain.SalesContract_Tx;
import FinTechOne.FOGS.repository.SalesContractRepository;
import FinTechOne.FOGS.repository.SalesContract_TxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;


@RestController
public class SalesContractController {

    @Autowired
    SalesContractRepository masterRepo;
    @Autowired
    SalesContract_TxRepository txRepo;

    @Autowired
    EntityLinks entityLinks;

    static final String entity = "/SalesContract";

//    @RequestMapping(value = entity, method = RequestMethod.POST)
//    public ResponseEntity<SalesContract> entity(@RequestParam String scNum){
//        SalesContract_Tx sc_tx = txRepo.findByScNum(scNum);
//        SalesContract sc = masterRepo.entity(sc_tx);
//        sc.add(this.entityLinks.linkToSingleResource(SalesContract.class, sc.getIdntty()));
//        sc.add(this.entityLinks.linkForSingleResource(SalesContract.class, sc.getIdntty()).withSelfRel());
//
//        return new ResponseEntity<SalesContract>(sc, HttpStatus.OK);
//    }

    @RequestMapping(value = MainController.springDataRestBasePath + entity + MainController.Id + MainController.approve, method = RequestMethod.POST)
    public ResponseEntity<SalesContract> approve(@PathVariable Long idntty){
        SalesContract_Tx sc_tx;
        SalesContract sc;
        sc_tx = txRepo.getOne(idntty);
        sc = masterRepo.approve(sc_tx);
        sc.add(this.entityLinks.linkToSingleResource(SalesContract.class, sc.getIdntty()));
        sc.add(this.entityLinks.linkForSingleResource(SalesContract.class, sc.getIdntty()).withSelfRel());

        return new ResponseEntity<SalesContract>(sc, HttpStatus.OK);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleEntityNotFound (EntityNotFoundException e){
    }
}
