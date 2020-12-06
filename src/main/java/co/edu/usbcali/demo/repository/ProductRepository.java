package co.edu.usbcali.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.usbcali.demo.domain.Product;

public interface ProductRepository  extends JpaRepository<Product, String> {
	//Lista los productos por el nombre descendentemente
	@Query("SELECT pro FROM Product pro ORDER BY name DESC")
	public List<Product> OrderProductDesc();
	//Lista los productos donde el precio sea mayor a 1000000
	@Query("SELECT pro FROM Product pro WHERE price>1000000")
	public List<Product> productsGreaterThan();
	//Lista los productos que esten entre los precios 500000 y 3000000
	@Query("SELECT pro FROM Product pro WHERE price BETWEEN 500000 AND 3000000")
	public List<Product> productsBetweenPrices(); 
	//Lista los producto que tenga una o en su nombre
	@Query("SELECT pro FROM Product pro WHERE pro.name LIKE '%o%'")
	public List<Product> findProductLikeA(); 
	//Busca por proId y nombre
	public List<Product> findByProIdAndName(String proId, String name);
//	Buscar productos entre precios
	@Query("SELECT pro FROM Product pro WHERE pro.enable='Y' AND pro.price BETWEEN :p1 AND :p2 ")
	public List<Product> filterPrice(Integer p1, Integer p2);
//	Buscar productos por nombre
	@Query("SELECT pro FROM Product pro WHERE pro.name LIKE %:name% AND pro.enable='Y'")
	public List<Product> filterName(String name);
}
