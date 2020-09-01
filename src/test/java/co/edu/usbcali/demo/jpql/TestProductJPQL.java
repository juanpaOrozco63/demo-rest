package co.edu.usbcali.demo.jpql;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.usbcali.demo.domain.Product;
@SpringBootTest
class TestProductJPQL {
	
	private final static Logger log = LoggerFactory.getLogger(TestProductJPQL.class);
	
	@Autowired
	private EntityManager entityManager;

	@BeforeEach
	public void beforeEach(){
		log.info("beforeAll");
		assertNotNull(entityManager,"El entityManager es nulo");
	}
	@Test
	public void selectWhereParam() {
		log.info("selectWhereParam");
		String jqpl="SELECT pro FROM Product pro WHERE pro.enable=:enable AND pro.proId=:proId";
		List<Product> products=entityManager
		.createQuery(jqpl,Product.class)
		.setParameter("enable", "Y")
		.setParameter("proId", "APPL45")
		.getResultList();
		products.forEach(product->{
			log.info(product.getName());
			log.info(product.getDetail());

		});		

		}
	@Test
	public void selectWhereEnable() {
		log.info("selectWhere");
		String jqpl="SELECT pro FROM Product pro WHERE pro.enable='Y'";
		List<Product> products=entityManager.createQuery(jqpl,Product.class).getResultList();
		products.forEach(product->{
			log.info(product.getName());
			log.info(product.getDetail());

		});		

		}
	@Test
	public void selectLike() {
		log.info("selectLike");
		String jqpl="SELECT pro FROM Product pro WHERE pro.name like 'iP%'";
		List<Product> products=entityManager.createQuery(jqpl,Product.class).getResultList();
		products.forEach(product->{
			log.info(product.getName());
			log.info(product.getDetail());
		});		

		}
	@Test
	public void selectAll() {
		log.info("selectAll");
		String jqpl="SELECT pro FROM Product pro";
		List<Product> products=entityManager.createQuery(jqpl,Product.class).getResultList();
		products.forEach(product->{
			log.info(product.getName());
			log.info(product.getDetail());
		});


		}

}
