package FinTechOne.FOGS.hibernate;

import FinTechOne.FOGS.exception.TemperedKeyException;
import FinTechOne.FOGS.exception.TemperedKeyException.KeyProperty;
import org.apache.commons.lang.WordUtils;
import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.core.annotation.AnnotationUtils;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by User on 14/1/2017.
 */
public class DomainInterceptor extends EmptyInterceptor {
    public boolean onFlushDirty(Object entity,Serializable id,
                                Object[] currentState,Object[] previousState,
                                String[] propertyNames,Type[] types)
            throws CallbackException {
        Annotation annotation = AnnotationUtils.findAnnotation(entity.getClass(), Table.class);
        if (annotation != null) {
            Table table = (Table)annotation;
            if (table.uniqueConstraints() !=null) {
                for (UniqueConstraint uniqueConstraint : table.uniqueConstraints()) {
                    if (uniqueConstraint.columnNames() != null) {
                        List<KeyProperty> keyProperties = new ArrayList<KeyProperty>();
                        boolean hasError = false;
                        for (String columnName: uniqueConstraint.columnNames()) {
//                            try {
//                                Object newValue = entity.getClass().getMethod("get" + WordUtils.capitalize(columnName)).invoke(entity,null);
//                            }catch (NoSuchMethodException e){
//
//                            }catch (InvocationTargetException e){
//
//                            }catch (IllegalAccessException e){
//
//                            }
                            int i = Arrays.asList(propertyNames).indexOf(columnName);
                            Object oldValue = previousState[i];
                            Object newValue = currentState[i];
                            keyProperties.add(KeyProperty.of(columnName, oldValue, newValue));
                            hasError = hasError || !(
                                    oldValue == null && newValue == null ||
                                    oldValue.equals(newValue));

                        }
                        if (hasError){
                            TemperedKeyException e = new TemperedKeyException(entity.getClass().getSimpleName(), keyProperties, "TemperedKey", "Key Tempered");
                            throw new CallbackException(e);
                        }
                    }
                }
            }
        }
        return false;

    }
    public void onDelete(Object entity, Serializable id,
                         Object[] state, String[] propertyNames,
                         Type[] types) {

        System.out.println("onDelete");

    }
}
