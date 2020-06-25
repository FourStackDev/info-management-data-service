package org.fourstack.personaldataservice.unittests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.fourstack.personaldataservice.entities.Address;
import org.fourstack.personaldataservice.repositories.AddressRepository;
import org.fourstack.personaldataservice.services.AddressServiceImpl;
import org.fourstack.personaldataservice.utils.CommonUtils;
import org.fourstack.personaldataservice.utils.EntityGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddressServiceTest {

	@Mock
	private AddressRepository addressRepository;

	@InjectMocks
	private AddressServiceImpl addressService;

	@BeforeEach
	public void initializeMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test_getAllAddressList() {
		log.info("AddressServiceTest: Start of test_getAllAddressList() method");
		List<Address> mockAddressList = EntityGenerator.getAddressList();

		// mock the repository
		when(addressRepository.findAll()).thenReturn(mockAddressList);

		// call the service layer method
		List<Address> addressList = addressService.getAllAddressList();

		// verify the results
		assertEquals(mockAddressList.size(), addressList.size());
		assertEquals(mockAddressList, addressList);
		log.info("AddressServiceTest: End of test_getAllAddressList() method");
	}

	@Test
	public void test_getAllAddressListByUsingPageableObject() {
		log.info("AddressServiceTest: Start of test_getAllAddressListByUsingPageableObject() method");
		List<Address> mockAddressList = EntityGenerator.getAddressList();

		// get Page Request
		PageRequest pageable = EntityGenerator.getPageableObject(1, 10);

		// get Page Object for Address List
		PageImpl<Address> addressPage = EntityGenerator.getPageObject(pageable, mockAddressList,
				mockAddressList.size());

		// mock the repository
		when(addressRepository.findAll(pageable)).thenReturn(addressPage);

		// call the service layer method
		Page<Address> savedAddressPage = addressService.getAllAddressList(pageable);
		List<Address> addressList = savedAddressPage.getContent();

		// verify the results
		assertEquals(mockAddressList.size(), addressList.size());
		assertEquals(mockAddressList, addressList);
		log.info("AddressServiceTest: Start of test_getAllAddressListByUsingPageableObject() method");
	}

	@Test
	public void test_getAddressById() {
		log.info("AddressServiceTest: Start of test_getAddressById() method");
		long id = CommonUtils.getRandomLong();
		Address mockAddress = EntityGenerator.getAddress(id);

		// mock the repository
		when(addressRepository.findById(id)).thenReturn(Optional.of(mockAddress));

		// call the service layer method
		Address actualAddress = addressService.getAddressById(id);

		// verify the results
		assertEquals(mockAddress.getId(), actualAddress.getId());
		assertEquals(mockAddress.getDoorNo(), actualAddress.getDoorNo());
		assertEquals(mockAddress.getAddressLine1(), actualAddress.getAddressLine1());
		assertEquals(mockAddress.getAddressLine2(), actualAddress.getAddressLine2());
		assertEquals(mockAddress.getTaluk(), actualAddress.getTaluk());
		assertEquals(mockAddress.getDistrict(), actualAddress.getDistrict());
		assertEquals(mockAddress.getState(), actualAddress.getState());
		assertEquals(mockAddress.getCountry(), actualAddress.getCountry());
		assertEquals(mockAddress.getPincode(), actualAddress.getPincode());
		assertEquals(mockAddress, actualAddress);
		log.info("AddressServiceTest: Start of test_getAddressById() method");
	}

	@Test
	public void test_saveAddress() {
		log.info("AddressServiceTest: Start of test_saveAddress() method");
		Address mockAddress = EntityGenerator.getAddress();

		// mock the repository
		when(addressRepository.save(mockAddress)).thenReturn(mockAddress);

		// call the service layer method
		Address savedAddress = addressService.saveAddress(mockAddress);

		log.info("Saved Address Object: " + savedAddress);

		// verify the results
		assertEquals(mockAddress, savedAddress);
		log.info("AddressServiceTest: Start of test_saveAddress() method");
	}

	@Test
	public void test_saveAllAddress() {
		log.info("AddressServiceTest: Start of test_saveAllAddress() method");
		List<Address> mockAddressList = EntityGenerator.getAddressList();

		// mock the respository layer
		when(addressRepository.saveAll(mockAddressList)).thenReturn(mockAddressList);

		// call the service layer method
		List<Address> savedAddressList = addressService.saveAllAddress(mockAddressList);

		// verify the results
		assertEquals(mockAddressList.size(), savedAddressList.size());
		assertEquals(mockAddressList, savedAddressList);
		log.info("AddressServiceTest: Start of test_saveAllAddress() method");
	}
}
