package com.example.crudtestapis.Repositories;

import com.example.crudtestapis.model.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemModel,Integer> {
}
