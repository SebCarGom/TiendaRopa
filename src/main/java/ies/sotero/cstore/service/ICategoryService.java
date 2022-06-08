package ies.sotero.cstore.service;

import java.util.List;

import ies.sotero.cstore.model.Category;

public interface ICategoryService {
	public List<Category> findAll();
	
	public Category save(Category category);
}
