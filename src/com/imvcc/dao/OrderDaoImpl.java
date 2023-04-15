package com.imvcc.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;

import com.imvcc.domain.Item;
import com.imvcc.domain.Order;
import com.imvcc.domain.ReceiveAddress;

public class OrderDaoImpl extends BaseDao implements OrderDao {

	@SuppressWarnings("unchecked")
	public List<ReceiveAddress> findAllAddress(Integer userId) {
		Session session = getSession();
		String strHql = "from ReceiveAddress address where address.userId=:userId";
		Query query = session.createQuery(strHql);
		query.setInteger("userId", userId);
		List<ReceiveAddress> list=query.list();
		return list;
	}

	/**
	 * save or update 接受地址
	 */
	public void attachingDirtyAddresss(ReceiveAddress address) {
		Session session = getSession();
		session.saveOrUpdate(address);
	}

	public void attachClean(ReceiveAddress instance) {
		getSession().lock(instance, LockMode.NONE);
	}

	/**
	 * 保存订单项目
	 */
	public void saveItem(Item item) {
		Session session = getSession();
		session.save(item);
	}

	/**
	 * 保存订单
	 */
	public void saveOrder(Order order) {
		Session session = getSession();
		session.save(order);
	}

	public static void main(String[] args) {
		OrderDaoImpl od = new OrderDaoImpl();
		List<ReceiveAddress> listAdd = od.findAllAddress(6);
		for (ReceiveAddress ra : listAdd) {
			System.out.println(ra.getFullAddress());
		}
	}

}
