package ies.sotero.cstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ies.sotero.cstore.model.Order;
import ies.sotero.cstore.model.User;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Integer> {

	List<Order> findByUser(User user);

}
