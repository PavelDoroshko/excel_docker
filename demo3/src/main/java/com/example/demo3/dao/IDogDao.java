package com.example.demo3.dao;

import com.example.demo3.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDogDao extends JpaRepository <Dog,Long>{
}
