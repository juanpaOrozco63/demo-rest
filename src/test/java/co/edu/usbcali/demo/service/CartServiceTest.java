package co.edu.usbcali.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.domain.ShoppingProduct;
@SpringBootTest
@Rollback(false)
class CartServiceTest {
	private final static Logger log = LoggerFactory.getLogger(PaymentMethodServiceTest.class);

	@Autowired
	CartService cartService;
	@Autowired
	ShoppingProductService shoppingProductService;
	@Test
	void debeCrearUnShoppingCart() throws Exception {
		//Arange
		String email="fgiraudot0@economist.com";
		ShoppingCart shoppingCart=null;
		//Act
		shoppingCart=cartService.createCart(email);
		//Assert
		assertNotNull(shoppingCart);
	}
	@Test
	void noDebeCrearUnShoppingCart() throws Exception {
		//Arrange
		String email="abeamondqq@harvard.edu";
		//Act
		assertThrows(Exception.class, ()->cartService.createCart(email));
	}
	@Test
	void noDebeCrearUnShoppingCartPorCustomerNull()throws Exception {
		//Arrange
		String email=null;
		
		//Act
		assertThrows(Exception.class, ()->cartService.createCart(email));
		
	}
	
	@Test
	void noDebeCrearUnShoppingCartPorCustomerNoExiste()throws Exception {
		//Arrange
		String email="jperez@vvv.com";
		
		//Act
		assertThrows(Exception.class, ()->cartService.createCart(email));
		
	}
	@Test
	void debeAgregarProductShoppingCart() throws Exception{
		//Arrange
		Integer carId=21;
		String proId="MOV01";
		Integer quantity=3;
		ShoppingProduct shoppingProduct= null;
		shoppingProduct =cartService.addProduct(carId, proId, quantity);
		
		assertNotNull(shoppingProduct,"El shopping product es nulo");
	}
	@Test
	void eliminarProductoDelCarrito() throws Exception {
			//Arrange
			String proId="MB4";
			Integer carId=21;
			//Act
			cartService.removeProduct(carId, proId);
	}
	@Test
	void limpiarCarrito() throws Exception {
			//Arrange
			Integer carId=21;
			cartService.clearCart(carId);
	}
	@Test
	void buscarProductoPorCarrito() throws Exception {
		Integer carId=20;
		//Arrange
		//Act
		cartService.findShoppingProductByShoppingCart(carId);
		 

	}
	@Test
	void buscarShoppingCartEmail() throws Exception {
		String email="fgiraudot0@economist.com";
		//Arrange
		//Act
		cartService.findCarIdShoppingCartsByEmail(email);

	}
	@Test
	void findShoppingProductByShoppingCart() throws Exception {
		Integer carId=21;
		//Arrange
		//Act
		cartService.findShoppingProductByShoppingCart(carId);

	}
	
	
}
