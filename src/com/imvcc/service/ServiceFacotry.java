package com.imvcc.service;

import java.util.Map;

import com.imvcc.common.SessionConstant;

public class ServiceFacotry {
	/**
	 * produce User service
	 * @return
	 */
	public static UserService getUserService() {
		return new UserServiceImpl();
	}
	public static CategoryService getCategoryService() {
		return new CategoryServiceImpl();
	}
	public static BookService getBookService() {
		return new BookServiceImpl();
	}
	public static ProductService getProductService() {
		return new ProductServiceImpl();
	}
	
	//说明将购物车存入到了Session中!
	public static CartService getCartService(Map<String,Object> session) {
		CartService cartService; 
		if(session.containsKey(SessionConstant.SESS_CART)) {
			cartService=(CartService)session.get(SessionConstant.SESS_CART);
		}
		else {
			cartService=new CartServiceImpl();
			session.put(SessionConstant.SESS_CART, cartService);
		}
		return cartService;
	}
	public static OrderService getOrderService() {
		return new OrderServiceImpl();
	}
}
