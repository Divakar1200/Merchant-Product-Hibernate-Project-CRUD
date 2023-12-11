package org.jsp.hibernatetest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.hibernatetest.dto.Merchant;
import org.jsp.hibernatetest.dto.Product;


public class ProductDao {
	EntityManager manager= Persistence.createEntityManagerFactory("dev").createEntityManager();
	
	public Product saveProd(Product product, int merchid)
	{
		Merchant m= manager.find(Merchant.class, merchid);
		if(m!=null)
		{
			product.setMerchant(m);        //Assigning Merchant for Product
			m.getProduct().add(product);  //Adding Product for Merchant
			EntityTransaction t = manager.getTransaction();
			manager.persist(product);
			t.begin();
			t.commit();
			return product;
		}
		return null;
	}
	
	public Product updateProd(Product product, int merchid)
	{
		Merchant m= manager.find(Merchant.class, merchid);
		if(m!=null)
		{
			product.setMerchant(m);        //Assigning Merchant for Product
			m.getProduct().add(product);  //Adding Product for Merchant
			EntityTransaction t = manager.getTransaction();
			manager.merge(product);
			t.begin();
			t.commit();
			return product;
		}
		return null;
	}
	
	public List<Product> findProdByMerchId(int merchid)
	{
		String qry = "select m.product from Merchant m where m.id=?1";
		Query q = manager.createQuery(qry);
		q.setParameter(1, merchid);
		return q.getResultList();
	}
	
	public List<Product> findProdByBrand(String brand)
	{
		String qry = "select p from Product p where p.brand=?1";
		Query q = manager.createQuery(qry);
		q.setParameter(1, brand);
		return q.getResultList();
	}
	
	public List<Product> findProdByCate(String category)
	{
		String qry = "select p from Product p where p.category=?1";
		Query q = manager.createQuery(qry);
		q.setParameter(1, category);
		return q.getResultList();
	}
	
	public Product findbyId(int id) {
		return manager.find(Product.class, id);
	}
}
