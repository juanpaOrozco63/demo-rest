package co.edu.usbcali.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.usbcali.demo.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String>{
	public List<Customer> findByEnableAndEmail(String enable,String email);
	//Lista las personas que su nombre empieza por Mar
	@Query("SELECT cus FROM Customer cus WHERE cus.name LIKE 'Mar%'")
	public List<Customer> findCustomerLikemar();
	//Lista las personas odernados por nombre ascendentemente
	@Query("SELECT cus FROM Customer cus ORDER BY name ASC")
	public List<Customer> OrderCustomerAsc();
	//Lista las personas que su telefono empiece por 150
	@Query("SELECT cus FROM Customer cus WHERE cus.phone LIKE '%150%'")
	public List<Customer> findCustomerPhone150(); 
	//Lista las personas que su nombre empiece con la letra e o termine con la letra e
	@Query("select cus from Customer cus where cus.name LIKE 'e%' or cus.email LIKE '%e'")
	public List<Customer> findByNameorEmail(); 
	//Lista las personas odernados por nombre ascendentemente
	public List<Customer> findByEnableOrderByNameAsc(String enable);

}
