package isep.web.sakila.jpa.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="inventory")
@NamedQuery(name="Inventory.findAll", query="SELECT i FROM Inventory i")
public class Inventory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="inventory_id", unique=true, nullable=false)
	private int inventoryId;

	@Column(name="last_update", nullable=false)
	private Timestamp lastUpdate;

	@ManyToOne
	@JoinColumn(name="film_id", nullable=false)
	private Film film;

	@ManyToOne
	@JoinColumn(name="store_id", nullable=false)
	private Store store;

	@OneToMany(mappedBy="inventory")
	private List<Rental> rentals;

	public Inventory() {
	}

	public int getInventoryId() {
		return this.inventoryId;
	}

	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Film getFilm() {
		return this.film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public List<Rental> getRentals() {
		return this.rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	public Rental addRental(Rental rental) {
		getRentals().add(rental);
		rental.setInventory(this);

		return rental;
	}

	public Rental removeRental(Rental rental) {
		getRentals().remove(rental);
		rental.setInventory(null);

		return rental;
	}

}
