package com.creffer.repository.offer;

import com.creffer.models.offer.GoalModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("goalRepository")
public interface GoalRepo extends JpaRepository<GoalModel,Long> {
    GoalModel save(GoalModel goalModel);
}
