package com.imvcc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 购物车服务实现
 * 
 * @author Mr.rong
 * 
 */
public class CartServiceImpl implements CartService {
	private Map<Integer, CartItem> cartItems = new HashMap<Integer, CartItem>();
	private ProductService productService = ServiceFacotry.getProductService();

	/**
	 * 添加商品
	 */
	public void addCartItem(CartItem cartItem) {
		if(cartItems.containsKey(cartItem.getProduct().getId())) {
			cartItem=cartItems.get(cartItem.getProduct().getId());
			cartItem.setCount(cartItem.getCount()+1);
			if(cartItem.isDeleted()) {
				cartItem.setDeleted(false);
				return;
			}
		}
		cartItem.setProduct(productService.findById(cartItem.getProduct()
				.getId()));
		cartItems.put(cartItem.getProduct().getId(), cartItem);
	}

	/**
	 * 获取商品
	 * 
	 * @param id
	 * @return
	 */
	public CartItem getCartItem(Integer id) {
		return cartItems.get(id);
	}

	/**
	 * 删除商品
	 */
	public void deleteCartItem(CartItem cartItem) {
		if (cartItems.containsKey(cartItem.getProduct().getId())) {
			cartItems.get(cartItem.getProduct().getId()).setDeleted(true);
		}
	}

	/**
	 * Map to List
	 */
	public List<CartItem> productList() {
		List<CartItem> listCartItem = new ArrayList<CartItem>();
		Iterator<CartItem> it = cartItems.values().iterator();
		while (it.hasNext()) {
			listCartItem.add(it.next());
		}
		return listCartItem;
	}

	/**
	 * 恢复删除操作
	 */
	public void recoveyCartItem(CartItem cartItem) {
		if (cartItems.containsKey(cartItem.getProduct().getId())) {
			cartItems.get(cartItem.getProduct().getId()).setDeleted(false);
		}
	}

	/**
	 * 计算节省金额
	 */
	public double savePrice() {
		double dangSum = totalPrice();
		double fixSum = 0;
		List<CartItem> listCartItem = this.productList();
		for (CartItem cartItem : listCartItem) {
			if (!cartItem.isDeleted())
				fixSum += (cartItem.getProduct().getFixedPrice() * cartItem
						.getCount());
		}
		return (fixSum - dangSum);
	}

	/**
	 * 计算购物车总金额
	 */
	public double totalPrice() {
		double sum = 0;
		List<CartItem> listCartItem = this.productList();
		for (CartItem cartItem : listCartItem) {
			if (!cartItem.isDeleted())
				sum += (cartItem.getProduct().getDangPrice() * cartItem
						.getCount());
		}
		return sum;
	}

	/**
	 * 更新商品的数量
	 */
	public void updateCartItem(CartItem cartItem) {
		if (cartItems.containsKey(cartItem.getProduct().getId())) {
			if (cartItem.getCount() <= 0)
				cartItem.setCount(1);
			cartItems.get(cartItem.getProduct().getId()).setCount(
					cartItem.getCount());
		}

	}
	/**
	 * 清空购物车
	 * @param session
	 */
	public void clearCart() {
		cartItems.clear();
	}
}
