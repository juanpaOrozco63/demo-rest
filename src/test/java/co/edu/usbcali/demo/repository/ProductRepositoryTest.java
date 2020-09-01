package co.edu.usbcali.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
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
@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class ProductRepositoryTest {
	
	private final static Logger log = LoggerFactory.getLogger(ProductRepository.class);
	private final static String proId="MOV01";
	
	@Autowired
	ProductRepository productRepository;
	
	@Test
	@Transactional
	@Order(1)
	void save() {
		log.info("Save");
		Optional<Product> productOptional = productRepository.findById(proId);
		assertFalse(productOptional.isPresent(), "El product ya existe");
		Product product = new Product();
		product.setName("Motorola One Action");
		product.setProId("MOV01");
		product.setPrice(750000);
		product.setDetail("Motorola One Action serie 2020");
		product.setImage("https://images-na.ssl-images-amazon.com/images/I/81CTQInQaPL._AC_SX522_.jpg");
		product.setEnable("Y");
		productRepository.save(product);

	}
	@Test
	@Transactional
	@Order(2)
	void findByid() {
		log.info("find By Id");
		Optional<Product> productOptional = productRepository.findById(proId);
		assertTrue(productOptional.isPresent(), "El product no existe");

		
		

	}
	@Test
	@Transactional
	@Order(3)
	void update() {
		log.info("update");
		Optional<Product> productOptional = productRepository.findById(proId);
		// Siga si es true Quiere decir que existe
		assertTrue(productOptional.isPresent(), "El product no existe");
		Product product = productOptional.get();
		product.setEnable("N");
		productRepository.save(product);
		
		

	}
	@Test
	@Transactional
	@Order(4)
	void delete() {
		log.info("delete");

		Optional<Product> productOptional = productRepository.findById(proId);
		assertTrue(productOptional.isPresent(), "El product no existe");
		Product product = productOptional.get();
		productRepository.delete(product);

		

	}
	@Test
	@Transactional
	@Order(5)
	void findAll() {
		log.info("findAll");
		productRepository.findAll().forEach(product->{
			log.info("Name:"+ product.getName());
			log.info("Email:"+ product.getDetail());

		});

		

	}
	@Test
	@Transactional
	@Order(6)
	void count() {
		log.info("count");
		log.info("Count:"+productRepository.count());
		

		

	}
	@Test
	@Transactional
	@Order(7)
	void OrderProductDesc() {
		List<Product> products = productRepository.OrderProductDesc();
		assertFalse(products.isEmpty());
		products.forEach(product->{
			log.info("Name:"+ product.getName());
			log.info("Detail:"+ product.getDetail());
		});
		

		

	}
	@Test
	@Transactional
	@Order(8)
	void productsGreaterThan() {
		List<Product> products = productRepository.productsGreaterThan();
		assertFalse(products.isEmpty());
		products.forEach(product->{
			log.info("Name:"+ product.getName());
			log.info("Detail:"+ product.getDetail());
		});
		

		

	}
	@Test
	@Transactional
	@Order(9)
	void productsBetweenPrices() {
		List<Product> products = productRepository.productsBetweenPrices();
		assertFalse(products.isEmpty());
		products.forEach(product->{
			log.info("Name:"+ product.getName());
			log.info("Detail:"+ product.getDetail());
		});
		

		

	}
	@Test
	@Transactional
	@Order(10)
	void findProductLikeA() {
		List<Product> products = productRepository.findProductLikeA();
		assertFalse(products.isEmpty());
		products.forEach(product->{
			log.info("Name:"+ product.getName());
			log.info("Detail:"+ product.getDetail());
		});
		

		

	}
	@Test
	@Transactional
	@Order(11)
	void findByProIdAndName() {
		List<Product> products = productRepository.findByProIdAndName("APPL90","iPad Pro");
		assertFalse(products.isEmpty());
		products.forEach(product->{
			log.info("Name:"+product.getName());
			log.info("Enable:"+product.getEnable());


		});
		
	
	
	



}
	
}
