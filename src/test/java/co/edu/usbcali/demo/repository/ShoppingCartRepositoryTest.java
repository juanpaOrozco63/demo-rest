package co.edu.usbcali.demo.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.Customer;
import co.edu.usbcali.demo.domain.PaymentMethod;
import co.edu.usbcali.demo.domain.ShoppingCart;
@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class ShoppingCartRepositoryTest {
	private static Integer carId=null;
	private static String email="abaglowbn@furl.net";
	private static Integer payId=1;
	
	private final static Logger log = LoggerFactory.getLogger(ShoppingCartRepository.class);
	@Autowired
	ShoppingCartRepository shoppingCartRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	PaymentMethodRepository paymentMethodRepository;
	
	@Test
	@Transactional
	@Order(1)
	void save() {
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setCarId(null);
		shoppingCart.setItems(2);
		shoppingCart.setTotal(155080700L);
		shoppingCart.setEnable("Y");
		
		Optional<Customer> customerOptional=customerRepository.findById(email);
		assertTrue(customerOptional.isPresent(),"El customer con email " +email+"No existe");
		
		Customer customer=customerOptional.get();
		shoppingCart.setCustomer(customer);
		
		Optional<PaymentMethod> paymentMethodOptional = paymentMethodRepository.findById(payId);
		assertTrue(paymentMethodOptional.isPresent(),"El paymenMethod con id"+payId+" no existe");
		
		PaymentMethod paymentMethod=paymentMethodOptional.get();
		shoppingCart.setPaymentMethod(paymentMethod);
		
		shoppingCart=shoppingCartRepository.save(shoppingCart);
		
		carId= shoppingCart.getCarId();
		
		assertNotNull(carId,"El carId es nulo");
		
		
	}
	@Test
	@Transactional
	@Order(2)
	void findById() {
		Optional<ShoppingCart> shoppingCartOptional =shoppingCartRepository.findById(carId);
		assertTrue(shoppingCartOptional.isPresent(),"El shoppingCartOptional con carId"+carId+"no existe");
	}
	@Test
	@Transactional
	@Order(3)
	void update() {
		Optional<ShoppingCart> shoppingCartOptional =shoppingCartRepository.findById(carId);
		assertTrue(shoppingCartOptional.isPresent(),"El shoppingCartOptional con carId"+carId+"no existe");
		
		ShoppingCart shoppingCart=shoppingCartOptional.get();
		shoppingCart.setEnable("N");
		
		shoppingCartRepository.save(shoppingCart);
	}
	@Test
	@Transactional
	@Order(4)
	void delete() {
		Optional<ShoppingCart> shoppingCartOptional =shoppingCartRepository.findById(carId);
		assertTrue(shoppingCartOptional.isPresent(),"El shoppingCartOptional con carId"+carId+"no existe");
		
		ShoppingCart shoppingCart=shoppingCartOptional.get();	
		
		shoppingCartRepository.delete(shoppingCart);
	}

}
