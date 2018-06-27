package com.creffer.repository.users.specifications;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.function.Predicate;

//!!!Not used!!!
//!!!Not used!!!
//!!!Not used!!!
abstract public class AbstractSpecification<T> implements Specification<T> {
    @Override
    public boolean isSatisfiedBy(T t) {
        throw new NotImplementedException();
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaBuilder cb) {
        throw new NotImplementedException();
    }

    public Class<T> getType(){
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<T>) type.getActualTypeArguments()[0];
    }
}
