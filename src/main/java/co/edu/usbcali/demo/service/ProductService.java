package co.edu.usbcali.demo.service;

import java.util.List;

import co.edu.usbcali.demo.domain.Product;

public interface ProductService  extends GenericService<Product,String> {
	public List<Product>filterPrice(Integer p1, Integer p2);
	public List<Product>filterName(String name);

}
