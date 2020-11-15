package co.edu.usbcali.demo.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.dto.ShoppingCartDTO;
import co.edu.usbcali.demo.dto.ShoppingProductDTO;
import co.edu.usbcali.demo.mapper.ShoppingCartMapper;
import co.edu.usbcali.demo.mapper.ShoppingProductMapper;
import co.edu.usbcali.demo.service.CartService;
import co.edu.usbcali.demo.service.ShoppingCartService;

@RestController
@RequestMapping("/api/shoppingCart")
@CrossOrigin("*")
public class ShoppingCartController {
	
	@Autowired
	ShoppingCartMapper shoppingCartMapper;
	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired
	ShoppingProductMapper shoppingProductMapper;
	@Autowired
	CartService cartService;
	
	private final static Logger log = LoggerFactory.getLogger(CustomerController.class);
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@Valid @RequestBody ShoppingCartDTO shoppingCartDTO)throws Exception{
	
			ShoppingCart shoppingCart =shoppingCartMapper.toShoppingCart(shoppingCartDTO);
			shoppingCart=shoppingCartService.save(shoppingCart);
			shoppingCartDTO= shoppingCartMapper.toShoppingCartDTO(shoppingCart);
			
			return ResponseEntity.ok().body(shoppingCartDTO);
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody ShoppingCartDTO shoppingCartDTO)throws Exception{
	
		ShoppingCart shoppingCart = shoppingCartMapper.toShoppingCart(shoppingCartDTO);
		shoppingCart=shoppingCartService.update(shoppingCart);
		shoppingCartDTO= shoppingCartMapper.toShoppingCartDTO(shoppingCart);
			
			return ResponseEntity.ok().body(shoppingCartDTO);
		
	}
	@DeleteMapping("/delete/{carId}")
	public ResponseEntity<?> delete(@PathVariable("carId") Integer carId) throws Exception{
		shoppingCartService.deleteById(carId);
			return ResponseEntity.ok().build();
			
			
		
	}
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() throws Exception{
		
			//Lista de Customer
			List<ShoppingCart> shoppingCart=shoppingCartService.findAll();
			//Declaro arreglo de DTOS
			List<ShoppingCartDTO> shoppingCartDTO = shoppingCartMapper.toListShoppingCartDTO(shoppingCart);
			
			return ResponseEntity.ok().body(shoppingCartDTO);
			
		
	}
	@GetMapping("/findById/{carId}")
	public ResponseEntity<?> findById(@PathVariable("carId") Integer carId) throws Exception{
			Optional<ShoppingCart>ShoppingCartOptional=shoppingCartService.findById(carId);
			if(ShoppingCartOptional.isPresent()==false) {
				return ResponseEntity.ok().body("ShoppingCart not found");
			}
			ShoppingCart shoppingCart=ShoppingCartOptional.get();
			ShoppingCartDTO shoppingCartDTO=shoppingCartMapper.toShoppingCartDTO(shoppingCart);
			return ResponseEntity.ok().body(shoppingCartDTO);
			
			
		
	}
	@GetMapping("/createCart/{email}")
	public ResponseEntity<?> createCart(@PathVariable("email") String email)throws Exception{
	
			cartService.createCart(email);
			
			return ResponseEntity.ok().body("se creo el cart con el email:"+email);
		
	}
	@GetMapping("/addProduct/{carId}/{proId}/{quantity}")
	public ResponseEntity<?> addProduct(@PathVariable("carId") Integer carId, @PathVariable("proId") String proId,@PathVariable("quantity") Integer quantity)throws Exception{
	
			cartService.addProduct(carId, proId, quantity);
			
			return ResponseEntity.ok().body("se agrego el producto al carro con carId: "+carId+" producto: "+proId+" cantidad: "+quantity);
		
	}
	@GetMapping("/removeProduct/{carId}/{proId}")
	public ResponseEntity<?> removeProduct(@PathVariable("carId") Integer carId, @PathVariable("proId") String proId) throws Exception{
			cartService.removeProduct(carId, proId);
			return ResponseEntity.ok().body("se elimino el producto con carId: "+carId+" y proId: "+proId);
			
			
		
	}
	@DeleteMapping("/clearCart/{carId}")
	public ResponseEntity<?> clearCart(@PathVariable("carId") Integer carId) throws Exception{
			cartService.clearCart(carId);
			return ResponseEntity.ok().body("Se limpio el carrito de compras con carId: "+carId);
			
			
		
	}
//	@GetMapping("/findShoppingProductByShoppingCart/{carId}")
//	public ResponseEntity<?> findShoppingProductByShoppingCart(@PathVariable("carId") Integer carId) throws Exception{
//		
//			List<ShoppingProductDTO> shoppingProductDTO =shoppingProductMapper.toShoppingProductDTO(shoppingProductDTO);
//			cartService.findShoppingProductByShoppingCart(carId);
//			
//			return ResponseEntity.ok().body(shoppingProductDTO);
//			
//		
//	}
	
}
