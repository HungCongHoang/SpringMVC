package com.javaweb.dao;

import java.util.List;

import com.javaweb.model.NewModel;

public interface INewDAO extends GenericDAO<NewModel> {
	List<NewModel> findAll();
}
