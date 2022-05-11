package ies.sotero.cstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ies.sotero.cstore.model.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {

}
