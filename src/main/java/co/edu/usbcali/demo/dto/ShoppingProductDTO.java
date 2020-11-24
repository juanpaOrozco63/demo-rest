package co.edu.usbcali.demo.dto;

import javax.validation.constraints.NotNull;



public class ShoppingProductDTO  {
    
    @NotNull
    private Integer quantity;
    @NotNull
    private Integer shprId;
    @NotNull
    private Long total;
    @NotNull
    private String productId;
    @NotNull
    private Integer shoppingCartId;
    private String name;
    private String image;
    private Integer price;
    
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getShprId() {
		return shprId;
	}
	public void setShprId(Integer shprId) {
		this.shprId = shprId;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Integer getShoppingCartId() {
		return shoppingCartId;
	}
	public void setShoppingCartId(Integer shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
    
}