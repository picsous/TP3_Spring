package isep.web.sakila.jpa.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;

@Entity
@Table(name="payment")
@NamedQuery(name="Payment.findAll", query="SELECT p FROM Payment p")
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="payment_id", unique=true, nullable=false)
	private int paymentId;

	@Column(nullable=false, precision=10, scale=2)
	private BigDecimal amount;

	@Column(name="last_update", nullable=false)
	private Timestamp lastUpdate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="payment_date", nullable=false)
	private Date paymentDate;

	@ManyToOne
	@JoinColumn(name="customer_id", nullable=false)
	private Customer customer;

	@ManyToOne
	@JoinColumn(name="rental_id")
	private Rental rental;

	@ManyToOne
	@JoinColumn(name="staff_id", nullable=false)
	private Staff staff;

	public Payment() {
	}

	public int getPaymentId() {
		return this.paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Date getPaymentDate() {
		return this.paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Rental getRental() {
		return this.rental;
	}

	public void setRental(Rental rental) {
		this.rental = rental;
	}

	public Staff getStaff() {
		return this.staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

}
