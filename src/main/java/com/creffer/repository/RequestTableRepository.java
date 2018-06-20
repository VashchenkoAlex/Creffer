package com.creffer.repository;

import com.creffer.models.RequestTable;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RequestTableRepository extends CrudRepository<RequestTable,UUID> {
}
