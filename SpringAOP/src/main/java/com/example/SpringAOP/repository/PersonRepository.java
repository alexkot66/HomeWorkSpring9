package com.example.SpringAOP.repository;

import com.example.SpringAOP.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
