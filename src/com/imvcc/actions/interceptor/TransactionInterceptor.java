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
			log.debug("Dang:��ʼ����");
			//HibernateSessionFactory.beginTransaction();
			Session session=HibernateSessionFactory.getSession();
			tx=session.beginTransaction();
			invocation.invoke();
			log.debug("Dang:����׼���ύ");
			tx.commit();
			//HibernateSessionFactory.commit();
			log.info("Dang:�����ύ�ɹ�!");
		} catch (HibernateException e) {
			log.error("Dang:�����ύʧ��");
			e.printStackTrace();
			try {
				//HibernateSessionFactory.rollBack();
				tx.rollback();
			} catch (HibernateException e1) {
				log.error("Dang:�ع�����");
				e1.printStackTrace();
			}			
			return "input";
		} catch (Exception e) {
			log.error("Dang:�쳣");
			e.printStackTrace();
			return "input";
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return "success";
	}
}
