package com.imvcc.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import com.imvcc.domain.User;
import com.imvcc.util.HibernateSessionFactory;

/**
 * user data access object
 * 
 * @author Administrator
 * 
 */
public class UserDaoImpl extends BaseDao implements UserDao {
	/**
	 * 向数据库插入用户，拦截器处理事务打开和提交
	 * 
	 * @param user
	 * @return
	 */
	private Logger log = Logger.getLogger(UserDaoImpl.class);

	public User addUser(User user) {
		log.debug("Dang:开始session,写入数据");
		Session session = getSession();
		session.save(user);
		return user;
	}

	/**
	 * 根据对象查找用户
	 * 
	 * @param user
	 * @return
	 */
	public User findUserByObject(User user) {
		return null;
	}
	/**
	 * 根据Id查找对象
	 */
	public User findById(Integer id) {
		log.debug("Dang:根据-"+id+"-超找对象");
		Session session=getSession();
		User tmpUser=(User) session.get(User.class, id);
		return tmpUser;
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<User> findByProperty(String propertyName, Object value) {
		log.debug("Dang:Dao根据属性查找实体");
		Session session = null;
		try {
			session = getSession();
			String queryString = "from User as model where model."
					+ propertyName + "= ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			List<User> list = queryObject.list();
			log.debug("Dang:查找结果：" + list.size());
			return list;
		} catch (RuntimeException re) {
			log.error("Dang：运行时");
			throw re;
		}
	}
	/**
	 * 根据不完整的User去超找对象
	 */
	@SuppressWarnings("unchecked")
	public List<User> findByExample(User userExample) {
		log.debug("finding User instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.imvcc.domain.User")
                    .add(Example.create(userExample))
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
	}

	public User delete(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	//适用于游离对象的持久化
	public User update(User user) {
		Session session=HibernateSessionFactory.getSession();
		session.update(user);
		return user;
	}

	public int updateByExample(User userExample) {
		// TODO Auto-generated method stub
		return 0;
	}
}
