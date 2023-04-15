package com.imvcc.actions.interceptor;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.imvcc.util.HibernateSessionFactory;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * transcation interceptor for open and commit
 * @author Administrator
 *
 */
public class TransactionInterceptor extends AbstractInterceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1745553319828855304L;
	private Logger log=Logger.getLogger(TransactionInterceptor.class);
	@Override
	public String intercept(ActionInvocation invocation) {
		Transaction tx=null;
		try {
			log.debug("Dang:开始事务！");
			//HibernateSessionFactory.beginTransaction();
			Session session=HibernateSessionFactory.getSession();
			tx=session.beginTransaction();
			invocation.invoke();
			log.debug("Dang:事务准备提交");
			tx.commit();
			//HibernateSessionFactory.commit();
			log.info("Dang:事务提交成功!");
		} catch (HibernateException e) {
			log.error("Dang:事务提交失败");
			e.printStackTrace();
			try {
				//HibernateSessionFactory.rollBack();
				tx.rollback();
			} catch (HibernateException e1) {
				log.error("Dang:回滚错误");
				e1.printStackTrace();
			}			
			return "input";
		} catch (Exception e) {
			log.error("Dang:异常");
			e.printStackTrace();
			return "input";
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return "success";
	}
}
