package org.fourstack.personaldataservice.services;

import java.util.List;

import org.fourstack.personaldataservice.entities.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AddressService {

	public List<Address> getAllAddressList();

	public Page<Address> getAllAddressList(Pageable pageable);
	
	public Address getAddressById(Long id);
	
	public Address saveAddress(Address address);
	
	public List<Address> saveAllAddress(List<Address> addresses);

}
