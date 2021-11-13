package com.todolist.repository;

import com.todolist.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends JpaRepository<ItemEntity, Long> {

    @Query("from ItemEntity c order by c.date")
    List<ItemEntity> getAllByDate();
}
