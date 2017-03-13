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

import com.order.entity.Product;
import com.order.dao.ProductDAO;


@Transactional
public class ProductDAO {
	private static final Logger log = LoggerFactory.getLogger(ProductDAO.class);
	// property constants
	public static final String PRODUCT_NAME = "productName";
	public static final String PRO_DESCRIBE = "proDescribe";
	public static final String PRICE = "price";
	public static final String IMAGE_URL = "imageUrl";
	public static final String PROPLACE = "proplace";
	public static final String PROSTATE = "prostate";
	public static final String GOPLACE = "goplace";
	public static final String PRODETAIL="prodetail";
	public static final String HOTELDETAIL="hoteldetail";

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(Product transientInstance) {
		log.debug("saving Product instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Product persistentInstance) {
		log.debug("deleting Product instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Product findById(java.lang.Long id) {
		log.debug("getting Product instance with id: " + id);
		try {
			Product instance = (Product) getCurrentSession().get(
					"com.order.entity.Product", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Product> findByExample(Product instance) {
		log.debug("finding Product instance by example");
		try {
			List<Product> results = (List<Product>) getCurrentSession()
					.createCriteria("com.order.entity.Product")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Product instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Product as model where model."
					+ propertyName + "= ? and model.state = '0'";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Product> findByProductName(Object productName) {
		return findByProperty(PRODUCT_NAME, productName);
	}

	public List<Product> findByProDescribe(Object proDescribe) {
		return findByProperty(PRO_DESCRIBE, proDescribe);
	}

	public List<Product> findByPrice(Object price) {
		return findByProperty(PRICE, price);
	}

	public List<Product> findByImageUrl(Object imageUrl) {
		return findByProperty(IMAGE_URL, imageUrl);
	}

	public List<Product> findByProstate(Object prostate) {
		return findByProperty(PROSTATE, prostate);
	}

	public List<Product> findByProplace(Object proplace) {
		return findByProperty(PROPLACE, proplace);
	}

	public List<Product> findByGoplace(Object goplace) {
		return findByProperty(GOPLACE, goplace);
	}
	public List<Product> findByProdetail(Object proDetail){
		return findByProperty(PRODETAIL,proDetail);
	}
	public List<Product> findByHoteldetail(Object hotelDetail){
		return findByProperty(HOTELDETAIL,hotelDetail);
	}
	public List findAll() {
		log.debug("finding all Product instances");
		try {
			String queryString = "from Product";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Product merge(Product detachedInstance) {
		log.debug("merging Product instance");
		try {
			Product result = (Product) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Product instance) {
		log.debug("attaching dirty Product instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Product instance) {
		log.debug("attaching clean Product instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

//	public List<Product> findProductBySearch(Shop shop, String text) {
//		try {
//			String queryString = "from Product as model where model.shop = ? "
//					+ "and model.productName like ? and model.state = '0'";
//			Query queryObject = getCurrentSession().createQuery(queryString);
//			queryObject.setParameter(0, shop);
//			queryObject.setParameter(1, "%" + text + "%");
//			return queryObject.list();
//		} catch (RuntimeException re) {
//			log.error("find by property name failed", re);
//			throw re;
//		}
//	}

	public static ProductDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ProductDAO) ctx.getBean("ProductDAO");
	}
}

