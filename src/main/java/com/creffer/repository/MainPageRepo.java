package com.creffer.repository;

import com.creffer.models.MainPageModel;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MainPageRepo extends CrudRepository<MainPageModel,UUID> {
}
