package co.edu.usbcali.demo.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PaymentMethodDTO {

	private Integer payId;
	private String enable;
	private String name;
	
	public PaymentMethodDTO() {
		super();
	}

	public PaymentMethodDTO(Integer payId, String enable, String name) {
		super();
		this.payId = payId;
		this.enable = enable;
		this.name = name;
	}
	@Id
	@Column(name = "pay_id", unique = true, nullable = false)
	@NotNull
	public Integer getPayId() {
		return payId;
	}

	public void setPayId(Integer payId) {
		this.payId = payId;
	}
	@Column(name = "enable", nullable = false)
	@Size(min=1, max = 1)
	@NotNull
	@NotEmpty
	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}
	@Column(name = "name", nullable = false)
	@NotNull
	@Size(min=4, max = 255)
	@NotEmpty
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
