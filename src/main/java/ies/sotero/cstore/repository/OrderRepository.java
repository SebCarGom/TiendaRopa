package ies.sotero.cstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ies.sotero.cstore.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

}
