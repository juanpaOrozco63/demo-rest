package co.edu.usbcali.demo.service;

import static org.junit.jupiter.api.Assertions.*;

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

import co.edu.usbcali.demo.domain.Product;

@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class ProductServiceTest {

	private final static Logger log=LoggerFactory.getLogger(ProductServiceTest.class);
	
	private final static String proId = "MOV01";
	
	@Autowired
	ProductService productService;
	
	@Test
	@Order(1)
	void save() throws Exception{
		log.info("save");
		
		Product product=new Product();
		product.setProId(proId);
		product.setName("Motorola One Vision");
		product.setDetail("Nuevo Motorola lo mas top");
		product.setEnable("Yes");
		product.setPrice(380000);
		product.setImage("https://www.google.com/search?=608#imgrc=UKzy8o5FwEvtUM");
		
		productService.save(product);
	}
	
	@Test
	@Order(2)
	void findById() throws Exception{
		log.info("findById");
		
		Optional<Product> productOptional=productService.findById(proId);
		
		assertTrue(productOptional.isPresent(),"El producto no existe");
	}
	
	@Test
	@Order(3)
	void update() throws Exception{
		log.info("update");
		
		Optional<Product> productOptional=productService.findById(proId);
		
		assertTrue(productOptional.isPresent(),"El producto no existe");
		
		Product product=productOptional.get();
		product.setEnable("N");
		
		productService.update(product);
	}
	
	@Test
	@Order(4)
	void delete() throws Exception{
		log.info("delete");
		
		Optional<Product> productOptional=productService.findById(proId);
		
		assertTrue(productOptional.isPresent(),"El producto no existe");
		
		Product product=productOptional.get();
		
		productService.delete(product);
	}
}
