package com.creffer.repository.users.specifications;

import com.creffer.models.users.UserModel;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import java.util.function.Predicate;
//!!!Not used!!!
//!!!Not used!!!
//!!!Not used!!!
public class UserIsActive extends AbstractSpecification<UserModel> {
    @Override
    public boolean isSatisfiedBy(UserModel user) {
        return user.getActive()==1;
    }

    @Override
    public Predicate toPredicate(Root<UserModel> root, CriteriaBuilder cb) {
        return super.toPredicate(root, cb);
    }
}
