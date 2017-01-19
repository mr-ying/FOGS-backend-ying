package FinTechOne.FOGS.controller;

import FinTechOne.FOGS.domain.EntityBase;
import FinTechOne.FOGS.domain.EntityIdKeyBase;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ResourceUtilities {
    static public <S extends EntityBase> PagedResources<Resource<S>>
    getPagedResources(QueryDslPredicateExecutor<S> executor,
                      Predicate predicate,
                      Pageable pageable,
                      PagedResourcesAssembler<S> assembler,
                      EntityLinks entityLinks){
        Page<S> list = executor.findAll(predicate, pageable);
        Method method = null;
        for (S entity : list.getContent()){
            if (entity instanceof EntityIdKeyBase){
                entity.add(entityLinks.linkToSingleResource(entity.getClass(), ((EntityIdKeyBase)entity).getIdntty()));
                entity.add(entityLinks.linkForSingleResource(entity.getClass(), ((EntityIdKeyBase)entity).getIdntty()).withSelfRel());
            } else {
                try {
                    if (method == null) {
                        method = entity.getClass().getMethod("getIdntty");
                    }

                    try {
                        Long id = (Long) method.invoke(entity, (Object[])null);
                        entity.add(entityLinks.linkToSingleResource(entity.getClass(), id));
                        entity.add(entityLinks.linkForSingleResource(entity.getClass(), id).withSelfRel());
                    } catch (IllegalAccessException | InvocationTargetException e) {

                    }
                } catch (NoSuchMethodException e) {
                    break;
                }
            }
        }
        return assembler.toResource(list);
    }

}
