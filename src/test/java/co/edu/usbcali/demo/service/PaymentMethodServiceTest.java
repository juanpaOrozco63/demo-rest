package co.edu.usbcali.demo.service;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import co.edu.usbcali.demo.domain.PaymentMethod;
@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
public class PaymentMethodServiceTest {
	
	private static Integer payId=null;
	private final static Logger log = LoggerFactory.getLogger(PaymentMethodServiceTest.class);
	
	@Autowired
	PaymentMethodService paymentMethodService;
	
	@Test
	@Order(1)
	void save() throws Exception{
		PaymentMethod paymentMethod = new PaymentMethod();
		paymentMethod.setEnable("Y");
		paymentMethod.setName("Bancolombia");
		paymentMethodService.save(paymentMethod);
		payId=paymentMethod.getPayId();
		log.info("payId:"+payId);
		log.info("-----------------------------------------------");
	}
	@Test
	@Order(2)
	void findById() throws Exception {
		log.info("findById");
		assertTrue(paymentMethodService.findById(payId).isPresent());
		paymentMethodService.findById(payId).get();
		
		log.info("-----------------------------------------------");

	}
	@Test
	@Order(3)
	void update() throws Exception {
		PaymentMethod paymentMethod = paymentMethodService.findById(payId).get();
		paymentMethod.setEnable("N");
		paymentMethodService.update(paymentMethod);
		log.info("-----------------------------------------------");

	}
	@Test
	@Order(4)
	void delete() throws Exception {
		PaymentMethod paymentMethod = paymentMethodService.findById(payId).get();
		paymentMethodService.delete(paymentMethod);
		log.info("-----------------------------------------------");

	}


}
