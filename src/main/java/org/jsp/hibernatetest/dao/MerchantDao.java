package org.jsp.hibernatetest.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.hibernatetest.dto.Merchant;


public class MerchantDao {
	EntityManager manager= Persistence.createEntityManagerFactory("dev").createEntityManager();
	
	public Merchant saveMerchant(Merchant merchant)
	{
		EntityTransaction t = manager.getTransaction();
		manager.persist(merchant);
		t.begin();
		t.commit();
		return merchant;
	}
	
	public Merchant updateMerchant(Merchant merchant)
	{
		EntityTransaction t = manager.getTransaction();
		manager.merge(merchant);
		t.begin();
		t.commit();
		return merchant;
	}
	
	public Merchant verifyMerchant(int id, String password)
	{
		String qry= "select m from Merchant m where m.id=?1 and m.password=?2";
		Query q = manager.createQuery(qry);
		q.setParameter(1, id);
		q.setParameter(2, password);
		try {
			return (Merchant) q.getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	public Merchant verifyMerchant(long phone, String password)
	{
		String qry= "select m from Merchant m where m.phone=?1 and m.password=?2";
		Query q = manager.createQuery(qry);
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try {
			return (Merchant) q.getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	public Merchant verifyMerchant(String email, String password)
	{
		String qry= "select m from Merchant m where m.email=?1 and m.password=?2";
		Query q = manager.createQuery(qry);
		q.setParameter(1, email);
		q.setParameter(2, password);
		try {
			return (Merchant) q.getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}
	
	public Merchant findbyId(int id) {
		return manager.find(Merchant.class, id);
	}
}
