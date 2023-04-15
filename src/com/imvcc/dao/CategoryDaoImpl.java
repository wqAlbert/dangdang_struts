package com.imvcc.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.imvcc.domain.Category;
import com.imvcc.util.HibernateSessionFactory;

public class CategoryDaoImpl implements CategoryDao {
	
	
	
	@SuppressWarnings("unchecked")
	public List<Category> findCatByParentId(Integer parentId) {
		Session session=HibernateSessionFactory.getSession();
		Query query=session.createQuery("select distinct cat from Category cat join fetch cat.subCats where cat.parentId=:id");
		//Query query=session.createQuery("select cat from Category cat join fetch cat.subCats where cat.parentId=:id");
		query.setInteger("id", parentId);
		query.setCacheable(true);
		return query.list();
	}
	
	/* (non-Javadoc)
	 * @see com.imvcc.dao.CategoryDao#findCatById(java.lang.Integer)
	 */
	public	Category findCatById(Integer id) {
		Session session=HibernateSessionFactory.getSession();
		//Query query=session.createQuery("from Category cat join fetch cat.subCats where cat.id=:id");
		Query query=session.createQuery("from Category cat join fetch cat.subCats where cat.id=?");
		//query.setInteger("id", id);
		query.setInteger(0, id);
		query.setCacheable(true);
		return (Category) query.uniqueResult();
	}
	
	public static void main(String[] args) {
		System.out.println("testit");
		
		CategoryDaoImpl cat=new CategoryDaoImpl();
		
		List<Category> cats=cat.findCatByParentId(2);
		for(Category tmpCat:cats) {
			System.out.println(tmpCat.getName());
			for(Category tmpCat2:tmpCat.getSubCats()) {
				System.out.println("-----------"+tmpCat2.getName());
			}
		}
		
		
		
		/*Category cat1=cat.findCatById(2);
		for(Category tmpCat:cat1.getSubCats()) {
			System.out.println("---------"+tmpCat.getName()+"("+tmpCat.getProductCount()+")");
		}
		
	    System.out.println();
		Category cat2=cat.findCatById(43);
		for(Category tmpCat:cat2.getSubCats()) {
			System.out.println("---------"+tmpCat.getName()+"("+tmpCat.getProductCount()+")");
		}*/
	}
}
