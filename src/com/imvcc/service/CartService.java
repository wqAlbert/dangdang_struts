package com.imvcc.service;

import java.util.List;

/**
 * 提供购物车服务
 * @author Mr.rong
 *
 */
public interface CartService {
	/**
	 * 显示商品列表
	 * @return
	 */
	public List<CartItem> productList();
	/**
	 * 添加商品
	 * @param cartItem
	 */
	public void addCartItem(CartItem cartItem);
	/**
	 * 
	 * @param cartItem
	 */
	public void deleteCartItem(CartItem cartItem);
	/**
	 * 
	 * @param cartItem
	 */
	public void recoveyCartItem(CartItem cartItem);
	/**
	 * 
	 * @param cartItem
	 * @param count
	 */
	public void updateCartItem(CartItem cartItem);
	/**
	 * 返回购物车总金额
	 * @return
	 */
	public double totalPrice();
	/**
	 * 返回节省的金额 
	 * @return
	 */
	public double savePrice();
	public void clearCart();
}
