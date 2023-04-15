package com.imvcc.dao;

import java.util.List;

import com.imvcc.domain.Item;
import com.imvcc.domain.Order;
import com.imvcc.domain.ReceiveAddress;

/**
 * 
 * @author Mr.rong
 *
 */
public interface OrderDao {
	/**
	 * 根据用户的Id获取曾经的地址
	 * @param userId
	 * @return
	 */
	public List<ReceiveAddress> findAllAddress(Integer userId);
	/**
	 * save or update 接受地址
	 * @param address
	 */
	public void attachingDirtyAddresss(ReceiveAddress address);
	/**
	 * save Order item
	 * @param item
	 */
	public void saveItem(Item item);
	/**
	 * save Order
	 * @param order
	 */
	public void saveOrder(Order order);
	/**
	 * what's men
	 * @param address
	 */
	public void attachClean(ReceiveAddress address);
}
