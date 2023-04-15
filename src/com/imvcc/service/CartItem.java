package com.imvcc.service;

import com.imvcc.domain.Product;
/**
 * 购物车商品
 * @author Mr.荣
 */
public class CartItem {
	private Product product;
	private int count;
	private boolean isDeleted=false; //删除状态
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public CartItem(Product product, int count, boolean isDeleted) {
		super();
		this.product = product;
		this.count = count;
		this.isDeleted = isDeleted;
	}
	public CartItem(Integer id,int count) {
		this.product=new Product();
		product.setId(id);
		this.count=count;
	}
}
