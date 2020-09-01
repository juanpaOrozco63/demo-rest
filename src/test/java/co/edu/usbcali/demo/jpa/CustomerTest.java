package co.edu.usbcali.demo.jpa;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.Customer;
@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class CustomerTest {
	private final static String email="juan-pablo01@hotmail.com";
	private final static Logger log = LoggerFactory.getLogger(CustomerTest.class);
	@Autowired
	EntityManager entityManager;
	@Test
	@Transactional
	@Order(1)
	void save() {
		//Siga si no es nulo
		assertNotNull(entityManager,"El entityManager es nulo");
		Customer customer  = entityManager.find(Customer.class, email);
		//Siga si es nulo
		assertNull(customer,"El cliente con email "+email+" ya existe");
		customer = new Customer();
		customer.setAddress("Avenida Siempre mala 123");
		customer.setEmail(email);
		customer.setEnable("Y");
		customer.setPhone("312 252 3660");
		customer.setName("Juan Pablo Orozco");
		customer.setToken("NDJFDBHOQHPFHFP43234");
		
		//entityManager.getTransaction().begin();
		entityManager.persist(customer);
		//entityManager.getTransaction().commit();
			
		
	}
	
	@Test
	@Transactional
	@Order(2)
	void findById() {
		assertNotNull(entityManager,"El entityManager es nulo");
		Customer customer  = entityManager.find(Customer.class, email);
		//Siga si es nulo
		assertNotNull(customer,"El cliente con email "+email+" ya existe");
		
		log.info(customer.getName());
	}
	@Test
	@Transactional
	@Order(3)
	void update() {
		assertNotNull(entityManager,"El entityManager es nulo");
		Customer customer  = entityManager.find(Customer.class, email);
		//Siga si es nulo
		assertNotNull(customer,"El cliente con email "+email+" ya existe");
		customer.setEnable("N");
		entityManager.merge(customer);
		
	}
	@Test
	@Transactional
	@Order(4)
	void delete() {
		assertNotNull(entityManager,"El entityManager es nulo");
		Customer customer  = entityManager.find(Customer.class, email);
		//Siga si es nulo
		assertNotNull(customer,"El cliente con email "+email+" ya existe");
		entityManager.remove(customer);
		
	}

}
