package com.javaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.entity.NewEntity;

public interface NewRepository extends JpaRepository<NewEntity, Long> {

}
