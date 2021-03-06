package isep.web.sakila.webapi.service;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isep.web.sakila.dao.repositories.AddressRepository;
import isep.web.sakila.dao.repositories.CityRepository;
import isep.web.sakila.jpa.entities.Address;
import isep.web.sakila.webapi.model.AddressWO;

@Service("addressService")
@Transactional
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private CityRepository cityRepository;

	private static final Log log= LogFactory.getLog(AddressServiceImpl.class);

	@Override
	public AddressWO findById(int id) {
		log.debug(String.format("Looking for address by Id %s", id));
		Address address = addressRepository.findOne(id);

		if (address != null)
		{
			return new AddressWO(address);
		}
		return null;
	}

	@Override
	public Address saveAddress(AddressWO addressWO) {
		Address address = new Address();
		address.setAddress(addressWO.getAddress());
		address.setAddress2(addressWO.getAddress2());
		address.setDistrict("");
		address.setPostalCode(addressWO.getPostalCode());
		address.setPhone(addressWO.getPhone());
		address.setCity(cityRepository.findOne(addressWO.getCityId()));

		address.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		Address a = addressRepository.save(address);
		return a;
	}

	@Override
	public Address updateAddress(AddressWO addressWO) {
		Address address2update = addressRepository.findOne(addressWO.getAddressId());
		address2update.setAddress(addressWO.getAddress());
		address2update.setAddress2(addressWO.getAddress2());
		address2update.setDistrict(addressWO.getDistrict());
		address2update.setPostalCode(addressWO.getPostalCode());
		address2update.setPhone(addressWO.getPhone());
		address2update.setCity(cityRepository.findOne(addressWO.getCityId()));
		address2update.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		Address a = addressRepository.save(address2update);
		return a;

	}

	@Override
	public List<AddressWO> findAllAddresses() {
		List<AddressWO> addresses = new LinkedList<AddressWO>();

		for (Address address : addressRepository.findAll())
		{
			addresses.add(new AddressWO(address));
			log.debug("Adding " + address);
		}

		return addresses;
	}

}
