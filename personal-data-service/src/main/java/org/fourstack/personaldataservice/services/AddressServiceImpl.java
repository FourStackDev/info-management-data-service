package org.fourstack.personaldataservice.services;

import java.util.List;
import java.util.Optional;

import org.fourstack.personaldataservice.entities.Address;
import org.fourstack.personaldataservice.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressRepository addressRepository;

	@Override
	public List<Address> getAllAddressList() {
		return addressRepository.findAll();
	}

	@Override
	public Page<Address> getAllAddressList(Pageable pageable) {
		return addressRepository.findAll(pageable);
	}

	@Override
	public Address getAddressById(Long id) {
		Optional<Address> optionalAddress = addressRepository.findById(id);
		return optionalAddress.get();
	}

	@Override
	public Address saveAddress(Address address) {
		return addressRepository.save(address);
	}

	@Override
	public List<Address> saveAllAddress(List<Address> addresses) {
		return addressRepository.saveAll(addresses);
	}

}
