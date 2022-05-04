package ies.sotero.cstore.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String number;
	private Date creationDate;
	private Date receivedDate;
	private double total;

	@ManyToOne
	private User user;

	@OneToOne(mappedBy = "order")
	private OrderDetail orderDetail;

	public Order() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param number
	 * @param creationDate
	 * @param receivedDate
	 * @param total
	 * @param user
	 * @param orderDetail
	 */
	public Order(Integer id, String number, Date creationDate, Date receivedDate, double total) {
		super();
		this.id = id;
		this.number = number;
		this.creationDate = creationDate;
		this.receivedDate = receivedDate;
		this.total = total;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the receivedDate
	 */
	public Date getReceivedDate() {
		return receivedDate;
	}

	/**
	 * @param receivedDate the receivedDate to set
	 */
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	/**
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(double total) {
		this.total = total;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the orderDetail
	 */
	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	/**
	 * @param orderDetail the orderDetail to set
	 */
	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", number=" + number + ", creationDate=" + creationDate + ", receivedDate="
				+ receivedDate + ", total=" + total + "]";
	}

}
