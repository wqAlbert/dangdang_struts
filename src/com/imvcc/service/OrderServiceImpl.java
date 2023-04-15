package com.imvcc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.imvcc.dao.DaoFactory;
import com.imvcc.dao.OrderDao;
import com.imvcc.domain.Item;
import com.imvcc.domain.Order;
import com.imvcc.domain.ReceiveAddress;
import com.imvcc.domain.User;


public class OrderServiceImpl implements OrderService {

	private OrderDao orderDao=DaoFactory.getOrderDao();
	
	public List<Item> createOrderItems(CartService cartService) {
		//get cart items
		List<CartItem> cart=cartService.productList();
		//new items
		List<Item> items = new ArrayList<Item>();
		//iterator write information from cart to items
		for (CartItem cartItem : cart) {
			if (!cartItem.isDeleted()) {
				Item tmp = new Item();
				tmp.setProductId(cartItem.getProduct().getId());
				tmp.setProductName(cartItem.getProduct().getProductName());
				tmp.setDangPrice(cartItem.getProduct().getDangPrice());
				tmp.setProductNum(cartItem.getCount());
				tmp.setAmount(cartItem.getCount()
						* cartItem.getProduct().getDangPrice());
				items.add(tmp);
			}
		}
		return items;
	}


	@SuppressWarnings("unchecked")
	public Order createOrder(List<Item> items,CartService cartService) {
	
		Order order = new Order();
		order.setTotalPrice(cartService.totalPrice());
		order.setItems(new HashSet(items));
		return order;
	}

	public Map<Integer,ReceiveAddress> getReceiveAddressList(Integer userId) {
		List<ReceiveAddress> listAddress=orderDao.findAllAddress(userId);
		Map<Integer,ReceiveAddress> mapAdd=new HashMap<Integer,ReceiveAddress>();
		for(ReceiveAddress ra:listAddress) {
			mapAdd.put(ra.getId(), ra);
		}
		return mapAdd;
	}


	public void submitOrder(User user, ReceiveAddress address, Order order) {

		address.setUserId(user.getId());
		
		order.setUserId(user.getId());
		order.setOrderTime(System.currentTimeMillis());
		order.setAddress(address);
		/*
		order.setFullAddress(address.getFullAddress());
		order.setMobile(address.getMobile());
		order.setPhone(address.getPhone());
		order.setPostalCode(address.getPostalCode());
		order.setReceiveName(address.getReceiveName());
		*/
		order.setStatus(0);
		
		orderDao.attachingDirtyAddresss(address);
		orderDao.saveOrder(order);
		for(Item item:order.getItems()) {
			item.setOrder(order);
			orderDao.saveItem(item);
		}
		orderDao.attachClean(address);
	}
}
