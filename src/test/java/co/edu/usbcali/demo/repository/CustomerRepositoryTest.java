package co.edu.usbcali.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.Customer;
@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class CustomerRepositoryTest {
	private final static Logger log = LoggerFactory.getLogger(CustomerRepository.class);
	private final static String email="juan-pablo01@hotmail.com";
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Test
	@Transactional
	@Order(1)
	void save() {
		log.info("Save");
		Optional<Customer> customerOptional = customerRepository.findById(email);
		assertFalse(customerOptional.isPresent(), "El customer ya existe");
		Customer customer = new Customer();
		customer.setAddress("Calle 4D #89-36");
		customer.setEmail(email);
		customer.setEnable("Y");
		customer.setName("Juan Orozco");
		customer.setPhone("3122523660");
		customer.setToken("KSDNFSDJ4566456DFG");
		customerRepository.save(customer);

	}
	@Test
	@Transactional
	@Order(2)
	void findByid() {
		log.info("find By Id");
		Optional<Customer> customerOptional = customerRepository.findById(email);
		assertTrue(customerOptional.isPresent(), "El customer no existe");
		

	}
	@Test
	@Transactional
	@Order(3)
	void update() {
		log.info("update");
		Optional<Customer> customerOptional = customerRepository.findById(email);
		// Siga si es true Quiere decir que existe
		assertTrue(customerOptional.isPresent(), "El customer no existe");
		Customer customer = customerOptional.get();
		customer.setEnable("N");
		customerRepository.save(customer);
		
		

	}
	@Test
	@Transactional
	@Order(4)
	void delete() {
		log.info("delete");

		Optional<Customer> customerOptional = customerRepository.findById(email);
		assertTrue(customerOptional.isPresent(), "El customer no existe");
		Customer customer = customerOptional.get();
		customerRepository.delete(customer);

		

	}
	@Test
	@Transactional
	@Order(5)
	void findAll() {
		log.info("findAll");
		customerRepository.findAll().forEach(customer->{
			log.info("Name:"+ customer.getName());
			log.info("Email:"+ customer.getEmail());

		});

		

	}
	@Test
	@Transactional
	@Order(6)
	void count() {
		log.info("count");
		log.info("Count:"+customerRepository.count());
		

		

	}
	@Test
	@Transactional
	@Order(7)
	void findByEnableAndEmail() {
		List<Customer> customers = customerRepository.findByEnableAndEmail("Y", "ablencoweo8@ucoz.com");
		assertFalse(customers.isEmpty());
		customers.forEach(customer->{
			log.info("Name:"+customer.getName());
			log.info("Email:"+customer.getEmail());

		});
		

		

	}
	@Test
	@Transactional
	@Order(8)
	void findCustomerLikemar() {
		List<Customer> customers = customerRepository.findCustomerLikemar();
		assertFalse(customers.isEmpty());
		customers.forEach(customer->{
			log.info("Name:"+customer.getName());
			log.info("Email:"+customer.getEmail());

		});
		

		

	}
	@Test
	@Transactional
	@Order(9)
	void OrderCustomerAsc() {
		List<Customer> customers = customerRepository.OrderCustomerAsc();
		assertFalse(customers.isEmpty());
		customers.forEach(customer->{
			log.info("Name:"+customer.getName());
			log.info("Email:"+customer.getEmail());

		});
		

		

	}
	@Test
	@Transactional
	@Order(10)
	void findCustomerPhone150() {
		List<Customer> customers = customerRepository.findCustomerPhone150();
		assertFalse(customers.isEmpty());
		customers.forEach(customer->{
			log.info("Name:"+customer.getName());
			log.info("Email:"+customer.getEmail());

		});
		

		

	}
	@Test
	@Transactional
	@Order(11)
	void findByNameorEmail() {
		List<Customer> customers = customerRepository.findByNameorEmail();
		assertFalse(customers.isEmpty());
		customers.forEach(customer->{
			log.info("Name:"+customer.getName());
			log.info("Email:"+customer.getEmail());

		});
		

		

	}
	@Test
	@Transactional
	@Order(12)
	void findByEnableOrderByNameAsc() {
		List<Customer> customers = customerRepository.findByEnableOrderByNameAsc("Y");
		assertFalse(customers.isEmpty());
		customers.forEach(customer->{
			log.info("Name:"+customer.getName());
			log.info("Email:"+customer.getEmail());

		});
		
	
	
	



}
}