package co.edu.usbcali.demo.jpa;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;

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
class ProductTest {
	private final static Logger log = LoggerFactory.getLogger(ProductTest.class);
	private final static String prodID = "MOV01";

//	private final static Logger log = LoggerFactory.getLogger(ProductTest.class);

	@Autowired
	EntityManager entityManager;

	@Test
	@Transactional
	@Order(1)
	void save() {
		// Siga si no es nulo
		assertNotNull(entityManager, "El entityManager es nulo");
		Product product = entityManager.find(Product.class, prodID);
		// Siga si es nulo
		assertNull(product, "El producto con id" + prodID + " ya existe");
		product = new Product();
		product.setProId(prodID);
		product.setDetail("Motorola One vision el mejor de todos");
		product.setEnable("Y");
		product.setImage("https://images-na.ssl-images-amazon.com/images/I/81CTQInQaPL._AC_SX522_.jpg");
		product.setName("Motorola One vision");
		product.setPrice(750000);

		// entityManager.getTransaction().begin();
		entityManager.persist(product);
		// entityManager.getTransaction().commit();

	}

	@Test
	@Transactional
	@Order(2)
	void findById() {
		// Siga si no es nulo
		assertNotNull(entityManager, "El entityManager es nulo");
		Product product = entityManager.find(Product.class, prodID);
		// Siga si es nulo
		assertNotNull(product, "El producto con proId" + prodID + " no existe");
		log.info(product.getName());
	}

	@Test
	@Transactional
	@Order(3)
	void update() {
		assertNotNull(entityManager, "El entityManager es nulo");
		Product product = entityManager.find(Product.class, prodID);
		// Siga si es nulo
		assertNotNull(product, "El producto con id " + prodID + " no existe");
		product.setEnable("N");
		entityManager.merge(product);

	}

	@Test
	@Transactional
	@Order(4)
	void delete() {
		assertNotNull(entityManager, "El entityManager es nulo");
		Product product = entityManager.find(Product.class, prodID);
		// Siga si es nulo
		assertNotNull(product, "El productoc con id " + prodID + " no existe");
		entityManager.remove(product);

	}

}
