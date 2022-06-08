package ies.sotero.cstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.sotero.cstore.model.Category;
import ies.sotero.cstore.repository.ICategoryRepository;

@Service
public class CategoryServiceImpl implements ICategoryService{
	
	@Autowired
	private ICategoryRepository categoryRepository;

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category save(Category category) {
		return categoryRepository.save(category);
	}

}
