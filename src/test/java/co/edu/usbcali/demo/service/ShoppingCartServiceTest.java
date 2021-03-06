package co.edu.usbcali.demo.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import co.edu.usbcali.demo.domain.Customer;
import co.edu.usbcali.demo.domain.PaymentMethod;
import co.edu.usbcali.demo.domain.ShoppingCart;

@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
public class ShoppingCartServiceTest {


	private final static Logger log=LoggerFactory.getLogger(ShoppingCartServiceTest.class);
	private static Integer carId=null;
	private static Integer payId=1;
	private static String email="abaglowbn@furl.net";

	
	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	PaymentMethodService paymentMethodService;
	
	@Test
	@Order(1)
	void save() throws Exception {
		ShoppingCart shoppingCart=new ShoppingCart();
		shoppingCart.setCarId(carId);
		shoppingCart.setItems(5);
		shoppingCart.setTotal(83424L);
		shoppingCart.setEnable("Y");
		
		Optional<Customer> customerOptional=customerService.findById(email);
		assertTrue(customerOptional.isPresent(),"El customer con email "+email+" no existe");
		
		Customer customer=customerOptional.get();
		shoppingCart.setCustomer(customer);
		
		Optional<PaymentMethod> paymentMethodOptional=paymentMethodService.findById(payId);
		assertTrue(paymentMethodOptional.isPresent(),"El payment method con id "+payId+" no existe");
		
		PaymentMethod paymentMethod=paymentMethodOptional.get();
		shoppingCart.setPaymentMethod(paymentMethod);
		
		shoppingCart=shoppingCartService.save(shoppingCart);
		
		carId=shoppingCart.getCarId();
		
		log.info("--------------");
	}
	@Test
	@Order(2)
	void findById()throws Exception {
		Optional<ShoppingCart> shoppingCartOptional =shoppingCartService.findById(carId);
		assertTrue(shoppingCartOptional.isPresent(),"El shoppingCartOptional con carId"+carId+"no existe");
	}
	@Test
	@Order(3)
	void update() throws Exception{
		Optional<ShoppingCart> shoppingCartOptional =shoppingCartService.findById(carId);
		assertTrue(shoppingCartOptional.isPresent(),"El shoppingCartOptional con carId"+carId+"no existe");
		ShoppingCart shoppingCart=shoppingCartOptional.get();
		shoppingCart.setEnable("N");
		
		shoppingCartService.save(shoppingCart);
	}
	@Test
	@Order(4)
	void delete() throws Exception{
		Optional<ShoppingCart> shoppingCartOptional =shoppingCartService.findById(carId);
		
		ShoppingCart shoppingCart=shoppingCartOptional.get();	
		
		shoppingCartService.delete(shoppingCart);
	}
	
}
