package com.junit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.junit.model.Item;

public interface ItemRepository extends JpaRepository<Item,Integer>{

	
}
