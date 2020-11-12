package co.edu.usbcali.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.domain.ShoppingProduct;
@SpringBootTest
@Rollback(false)
class CartServiceTest {
	
	@Autowired
	CartService cartService;
	@Autowired
	ShoppingProductService shoppingProductService;
	@Test
	void debeCrearUnShoppingCart() throws Exception {
		//Arange
		String email="afeaviour4@nba.com";
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
		Integer carId=8;
		String proId="APPL56";
		Integer quantity=5;
		ShoppingProduct shoppingProduct= null;
		shoppingProduct =cartService.addProduct(carId, proId, quantity);
		
		assertNotNull(shoppingProduct,"El shopping product es nulo");
	}
	@Test
	void removeItems() {
		//Arrange
			List<Integer> lista = null;
			String proId="APPL56";
			Integer carId=0;
			//Act
				lista=shoppingProductService.selectShpr(carId, proId);
				lista.forEach(shpro->{
					try {
						shoppingProductService.deleteById(shpro);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
				//Assert
						
				assertTrue(lista.size()>0,"La lista esta vacia");
	}
	@Test
	void removeAllItems() {
		//Arrange
			List<Integer> lista = null;
			Integer carId=8;
			//Act
				lista=shoppingProductService.selectShprIdByCardId(carId);
				lista.forEach(shpro->{
					try {
						shoppingProductService.deleteById(shpro);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
				//Assert
						
				assertTrue(lista.size()>0,"La lista esta vacia");
	}
}
