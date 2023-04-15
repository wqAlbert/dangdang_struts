package com.imvcc.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.imvcc.domain.Book;
/**
 * 图书访问
 * @author M.rong
 *
 */
public class BookDaoImpl extends BaseDao implements BookDao {
	/**
	 * 根据目录编号查找图书
	 */
	@SuppressWarnings("unchecked") //抑制泛型检查
	public List<Book> findBookByCatId(Integer id,int firstResult,int maxResult) {
		//构建多对多hql查询语句
		String strHql="select distinct books from Category cat join cat.products books where cat.id=:id";
		Query query=getSession().createQuery(strHql);
		query.setInteger("id", id);
		//分页设置
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		query.setCacheable(true);
		return query.list();
	}
	
	/**
	 * 查找最新上架的图书
	 */
	@SuppressWarnings("unchecked")
	public List<Book> findNewBook(int size){
		String strHql="select distinct book from Book book order by book.publishTime desc";
		Query query=getSession().createQuery(strHql);
		//获取前size条记录
		query.setFirstResult(0);
		query.setMaxResults(size);
		query.setCacheable(true);
		return query.list();
	}

	/**
	 * 查找最热卖的图书
	 */
	@SuppressWarnings("unchecked")
	public List<Book> findHotBook(int size) {
		String strHql="select distinct book from Book book,Item item where item.productId=book.id group by item.productId order by sum(item.amount) desc";
		Query query=getSession().createQuery(strHql);
		//获取前size条记录
		query.setFirstResult(0);
		query.setMaxResults(size);
		query.setCacheable(true);
		return query.list();
	}

	/**
	 * 编辑推荐
	 */
	public List<Book> findRecommendBook(int size) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 新书热卖榜,最近7天上架的图书
	 */
	@SuppressWarnings("unchecked")
	public List<Book> findNewBoardBook(int size){
		String strHql="select distinct book from Book book,Item item where item.productId=book.id and book.publishTime>=:time group by item.productId order by sum(item.amount) desc";
		Query query=getSession().createQuery(strHql);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -7);
		query.setParameter("time", cal.getTimeInMillis());
		//获取前size条记录
		query.setFirstResult(0);
		query.setMaxResults(size);
		query.setCacheable(true);
		return query.list();
	}
	
	
	
	public static void main(String[] args) {
		BookDaoImpl bd=new BookDaoImpl();
		List<Book> books=bd.findNewBoardBook(10);
		System.out.println("数量："+books.size());
		for(Book b:books) {
			System.out.println(b.getId());
		}
	}
}
