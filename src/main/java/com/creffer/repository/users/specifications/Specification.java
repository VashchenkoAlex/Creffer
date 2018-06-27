package com.creffer.repository.users.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import java.util.function.Predicate;

//!!!Not used!!!
//!!!Not used!!!
//!!!Not used!!!
public interface Specification<T> {
    boolean isSatisfiedBy(T t);
    Predicate toPredicate(Root<T> root, CriteriaBuilder cb);
}
