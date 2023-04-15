package com.imvcc.service;

import java.util.List;
import java.util.Map;

import com.imvcc.domain.Item;
import com.imvcc.domain.Order;
import com.imvcc.domain.ReceiveAddress;
import com.imvcc.domain.User;

/**
 * the interface for order serivce
 * @author Mr.rong
 *
 */
public interface OrderService {
	/**
	 * 创建订单列表
	 * @param cartService
	 * @return
	 */
	public List<Item> createOrderItems(CartService cartService);
	/**
	 * 根据session中存储的信息生成Order
	 * @param session
	 * @return
	 */
	public Order createOrder(List<Item> items,CartService cartService);
	/**
	 * 根据用户的Id获取所有的接受地址
	 * @param userId
	 * @return
	 */
	public Map<Integer,ReceiveAddress> getReceiveAddressList(Integer userId);
	/**
	 * 提交订单
	 * @param user
	 * @param address
	 * @param order
	 */
	public void submitOrder(User user, ReceiveAddress address, Order order);
	
}
