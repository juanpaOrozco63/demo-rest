package co.edu.usbcali.demo.repository;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.Product;
import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.domain.ShoppingProduct;
@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class ShoppingProductRepositoryTest {
	
	private static Integer carId = 6;
	private static String proId ="APPL45";
	private static Integer shprId=null;
	
	private final static Logger log = LoggerFactory.getLogger(ShoppingProductRepository.class);

	@Autowired
	ShoppingCartRepository shoppingCartRepository;
	
	@Autowired
	ShoppingProductRepository shoppingProductRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Test
	@Transactional
	@Order(1)
	void save() {
		ShoppingProduct shoppingProduct = new ShoppingProduct();
		shoppingProduct.setShprId(shprId);
		shoppingProduct.setQuantity(10);
		shoppingProduct.setTotal(1000L);
		
		Optional<Product> productOptional=productRepository.findById(proId);
		assertTrue(productOptional.isPresent(),"El product con proId " +proId+"No existe");
		
		Product product=productOptional.get();
		shoppingProduct.setProduct(product);
		
		Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findById(6);
		assertTrue(shoppingCartOptional.isPresent(),"El shoppingCart con id"+carId+" no existe");
		
		ShoppingCart shoppingCart=shoppingCartOptional.get();
		shoppingProduct.setShoppingCart(shoppingCart);
		
		shoppingProduct=shoppingProductRepository.save(shoppingProduct);
		
		shprId= shoppingProduct.getShprId();
		
		assertNotNull(shprId,"El shprId es nulo");
	}
	@Test
	@Transactional
	@Order(2)
	void findById() {
		Optional<ShoppingProduct> shoppingProductOptional =shoppingProductRepository.findById(shprId);
		assertTrue(shoppingProductOptional.isPresent(),"El shoppingProductOptional con shprId"+shprId+"no existe");
	}
	@Test
	@Transactional
	@Order(3)
	void update() {
		Optional<ShoppingProduct> shoppingProductOptional =shoppingProductRepository.findById(shprId);
		assertTrue(shoppingProductOptional.isPresent(),"El shoppingProductOptional con shprId"+shprId+"no existe");
		
		ShoppingProduct shoppingProduct=shoppingProductOptional.get();
		shoppingProduct.setQuantity(0);
		
		shoppingProductRepository.save(shoppingProduct);
	}
	@Test
	@Transactional
	@Order(4)
	void delete() {
		Optional<ShoppingProduct> shoppingProductOptional =shoppingProductRepository.findById(shprId);
		assertTrue(shoppingProductOptional.isPresent(),"El shoppingProductOptional con shprId"+shprId+"no existe");
		
		ShoppingProduct shoppingProduct=shoppingProductOptional.get();	
		
		shoppingProductRepository.delete(shoppingProduct);
	}

}
