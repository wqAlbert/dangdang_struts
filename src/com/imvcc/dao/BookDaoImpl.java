package com.imvcc.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.imvcc.domain.Book;
/**
 * ͼ�����
 * @author M.rong
 *
 */
public class BookDaoImpl extends BaseDao implements BookDao {
	/**
	 * ����Ŀ¼��Ų���ͼ��
	 */
	@SuppressWarnings("unchecked") //���Ʒ��ͼ��
	public List<Book> findBookByCatId(Integer id,int firstResult,int maxResult) {
		//������Զ�hql��ѯ���
		String strHql="select distinct books from Category cat join cat.products books where cat.id=:id";
		Query query=getSession().createQuery(strHql);
		query.setInteger("id", id);
		//��ҳ����
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		query.setCacheable(true);
		return query.list();
	}
	
	/**
	 * ���������ϼܵ�ͼ��
	 */
	@SuppressWarnings("unchecked")
	public List<Book> findNewBook(int size){
		String strHql="select distinct book from Book book order by book.publishTime desc";
		Query query=getSession().createQuery(strHql);
		//��ȡǰsize����¼
		query.setFirstResult(0);
		query.setMaxResults(size);
		query.setCacheable(true);
		return query.list();
	}

	/**
	 * ������������ͼ��
	 */
	@SuppressWarnings("unchecked")
	public List<Book> findHotBook(int size) {
		String strHql="select distinct book from Book book,Item item where item.productId=book.id group by item.productId order by sum(item.amount) desc";
		Query query=getSession().createQuery(strHql);
		//��ȡǰsize����¼
		query.setFirstResult(0);
		query.setMaxResults(size);
		query.setCacheable(true);
		return query.list();
	}

	/**
	 * �༭�Ƽ�
	 */
	public List<Book> findRecommendBook(int size) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * ����������,���7���ϼܵ�ͼ��
	 */
	@SuppressWarnings("unchecked")
	public List<Book> findNewBoardBook(int size){
		String strHql="select distinct book from Book book,Item item where item.productId=book.id and book.publishTime>=:time group by item.productId order by sum(item.amount) desc";
		Query query=getSession().createQuery(strHql);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -7);
		query.setParameter("time", cal.getTimeInMillis());
		//��ȡǰsize����¼
		query.setFirstResult(0);
		query.setMaxResults(size);
		query.setCacheable(true);
		return query.list();
	}
	
	
	
	public static void main(String[] args) {
		BookDaoImpl bd=new BookDaoImpl();
		List<Book> books=bd.findNewBoardBook(10);
		System.out.println("������"+books.size());
		for(Book b:books) {
			System.out.println(b.getId());
		}
	}
}
