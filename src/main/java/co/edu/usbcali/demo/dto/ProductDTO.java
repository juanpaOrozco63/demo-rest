package co.edu.usbcali.demo.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class ProductDTO {
	
	private String proId;
	private String detail;
	private String enable;
	private String image;
	private String name;
	private Integer price;
	
	public ProductDTO() {
		super();
	}
	
	public ProductDTO(String proId, String detail, String enable, String image, String name, Integer price) {
		super();
		this.proId = proId;
		this.detail = detail;
		this.enable = enable;
		this.image = image;
		this.name = name;
		this.price = price;
	}
	
	@Id
	@Column(name = "pro_id", unique = true, nullable = false)
	@NotNull
	@Size(min=3, max = 255)
	public String getProId() {
		return this.proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	@Column(name = "detail", nullable = false)
	@Size(min=3, max = 255)
	@NotNull
	@NotEmpty
	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Column(name = "enable", nullable = false)
	@Size(min=1, max = 1)
	@NotNull
	@NotEmpty
	public String getEnable() {
		return this.enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	@Column(name = "image", nullable = false)
	@Size(min=3, max = 255)
	@NotNull
	@NotEmpty
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "name", nullable = false)
	@NotNull
	@Size(min=4, max = 255)
	@NotEmpty
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "price", nullable = false)
	@NotNull
	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	
	
}
