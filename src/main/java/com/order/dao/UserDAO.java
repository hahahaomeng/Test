package com.order.dao;
import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.order.entity.User;
import com.order.dao.UserDAO;
public class UserDAO {
	private static final Logger log = LoggerFactory.getLogger(UserDAO.class);
	//property constants
	public static final String USER_NAME = "userName";
	public static final String PASSWORD = "password";
	public static final String TYPE = "type";
	public static final String PHONE = "phone";
	public static final String EMAIL = "email";
	public static final String CHECK_CODE = "checkCode";
	public static final String STATE = "state";
	public static final String NOTICE = "notice";



	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession(){
		return sessionFactory.getCurrentSession(); 
	}
	protected void initDao() {
		//do nothing
	}

	public void save(User transientInstance) {
		log.debug("saving User instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(User persistentInstance) {
		log.debug("deleting User instance");
		try {
	        getCurrentSession().delete(persistentInstance);
	        log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public User findById( java.lang.Long id) {
		log.debug("getting User instance with id: " + id);
		try {
			User instance = (User) getCurrentSession()
                .get("com.order.entity.User", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}


	public List<User> findByExample(User instance) {
		log.debug("finding User instance by example");
		try {
			List<User> results = (List<User>) getCurrentSession() .createCriteria("com.order.entity.User").add( create(instance) ).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}    

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding User instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from User as model where model." 
     						+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<User> findByUserName(Object userName
			) {
		return findByProperty(USER_NAME, userName
				);
	}

	public List<User> findByPassword(Object password
			) {
		return findByProperty(PASSWORD, password
				);
	}

	public List<User> findByType(Object type
			) {
		return findByProperty(TYPE, type
				);
	}
	
	public List<User> findByPhone(Object phone
			) {
		return findByProperty(PHONE, phone
				);
	}

	public List<User> findByEmail(Object email
			) {
		return findByProperty(EMAIL, email
				);
	}

	public List<User> findByCheckCode(Object checkCode)
	 {
		return findByProperty(CHECK_CODE, checkCode
				);
	 }

	public List<User> findByState(Object state
			) {
		return findByProperty(STATE, state
				);
	}

	public List<User> findByNotice(Object notice
			) {
		return findByProperty(NOTICE, notice
				);
	}


	public List findAll() {
		log.debug("finding all User instances");
		try {
			String queryString = "from User";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}	

	public User merge(User detachedInstance) {
		log.debug("merging User instance");
		try {
			User result = (User) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(User instance) {
		log.debug("attaching dirty User instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(User instance) {
		log.debug("attaching clean User instance");
		try {
                  	getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
      	            log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UserDAO getFromApplicationContext(ApplicationContext ctx) {
		return (UserDAO) ctx.getBean("UserDAO");
	}

	public List findByUsername(String userName) {
		return findByProperty("userName", userName);
	}
}
