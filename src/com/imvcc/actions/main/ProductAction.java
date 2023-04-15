package com.imvcc.actions.main;

import com.imvcc.domain.Product;
import com.imvcc.service.ServiceFacotry;

public class ProductAction {
	private Integer id;
	private Product product;
	
	public String execute(){
		product = ServiceFacotry.getProductService().findById(id);
		return "success";
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
}
