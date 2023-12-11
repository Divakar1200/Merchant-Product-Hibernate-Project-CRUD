package org.jsp.hibernatetest.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.hibernatetest.dao.MerchantDao;
import org.jsp.hibernatetest.dao.ProductDao;
import org.jsp.hibernatetest.dto.Merchant;
import org.jsp.hibernatetest.dto.Product;

public class Merch_ProdController {
	
	static Scanner sc = new Scanner(System.in);
	static MerchantDao mdao = new MerchantDao();
	static ProductDao pdao= new ProductDao();
	
	public static void main(String[] args) {
		System.out.println("1. Save Merchant");
		System.out.println("2. update Merchant");
		System.out.println("3. Save Product");
		System.out.println("4. Update Product");
		System.out.println("5. Verify Merchant by id and password");
		System.out.println("6. Verify Merchant by phone and password");
		System.out.println("7. Verfiy Merchant by email and password");
		System.out.println("8. Find Product by Merchant id");
		System.out.println("9. Find Product by brand");
		System.out.println("10. Find Product by category");
		int choice = sc.nextInt();
		
		switch (choice) 
		{
		case 1:{
			saveMerch();
			break;
		}
		case 2:{
			updateMerch();
			break;
		}
		case 3:{
			saveProd();
			break;
		}
		case 4:{
			updateProd();
			break;
		}
		case 5:{
			verifyMerchById();
			break;
		}
		case 6:{
			verifyMerchByPhone();
			break;
		}
		case 7:{
			verifyMerchByEmail();
			break;
		}
		case 8:{
			findProdByMerchId();
			break;
		}
		case 9:{
			findProdByBrand();
			break;
		}
		case 10:{
			findProdByCate();
			break;
		}
		default:{
			System.err.println("Invalid input");
		}
		}
	}
	
	public static void saveMerch()
	{
		System.out.println("Enter the Merchant name, phone, email, gst, password - ");
		Merchant m =new Merchant();
		m.setName(sc.next());
		m.setPhone(sc.nextLong());
		m.setEmail(sc.next());
		m.setGst(sc.nextInt());
		m.setPassword(sc.next());
		m = mdao.saveMerchant(m);
		System.out.println("Merchant saved with ID : "+m.getId());
	}
	
	public static void updateMerch()
	{
		System.out.println("Enter Merchant Id to update - ");
		int mid = sc.nextInt();
		System.out.println("Enter the Merchant name, phone, email, gst, password - ");
		Merchant m = mdao.findbyId(mid);
		m.setId(mid);
		m.setName(sc.next());
		m.setPhone(sc.nextLong());
		m.setEmail(sc.next());
		m.setGst(sc.nextInt());
		m.setPassword(sc.next());
		m = mdao.saveMerchant(m);
		if(m!=null)
			System.out.println("Merchant updated with ID : "+m.getId());
		else
			System.err.println("Invalid Merchant Id");
	}
	
	private static void saveProd()
	{
		System.out.println("Enter the Merchant id to add Product - ");
		int mid = sc.nextInt();
		System.out.println("Enter the Product Name, Brand, Discription, Category and cost - ");
		Product p = new Product();
		p.setName(sc.next());
		p.setBrand(sc.next());
		p.setDiscription(sc.next());
		p.setCategory(sc.next());
		p.setCost(sc.nextDouble());
		p = pdao.saveProd(p, mid);
		if(p!=null) {
			System.out.println("Product saved with ID : "+p.getId());
		}
		else {
			System.err.println("Invalid Merchant id");
		}
	}
	
	private static void updateProd()
	{
		System.out.println("Enter the Merchant id to update Product - ");
		int mid = sc.nextInt();
		System.out.println("Enter Product id to update - ");
		int pid = sc.nextInt();
		System.out.println("Enter the Product Name, Brand, Discription, Category and cost - ");
		Product p = pdao.findbyId(pid);
		p.setId(pid);
		p.setName(sc.next());
		p.setBrand(sc.next());
		p.setDiscription(sc.next());
		p.setCategory(sc.next());
		p.setCost(sc.nextDouble());
		p = pdao.saveProd(p, mid);
		if(p!=null) {
			System.out.println("Product saved with ID : "+p.getId());
		}
		else {
			System.err.println("Invalid Merchant id");
		}
	}
	
	private static void verifyMerchById() {
		System.out.println("Enter the Merchant Id and password to verify");
		int mid = sc.nextInt();
		String password = sc.next();
		Merchant e = mdao.verifyMerchant(mid, password);
		if (e != null) {
			System.err.println("verification Succesfull");
			System.out.println("Merchant Id:" + e.getId());
			System.out.println("Merchant Name:" + e.getName());
			System.out.println("phone number:" + e.getPhone());
			System.out.println("Email id:" + e.getEmail());
			System.out.println("GST :" + e.getGst());
		} else {
			System.err.println("You have entered an Invalid Id or Password");
		}
	}
	
	private static void verifyMerchByPhone() {
		System.out.println("Enter the Merchant Phone and password to verify");
		long phone = sc.nextLong();
		String password = sc.next();
		Merchant e = mdao.verifyMerchant(phone, password);
		if (e != null) {
			System.err.println("verification Succesfull");
			System.out.println("Merchant Id:" + e.getId());
			System.out.println("Merchant Name:" + e.getName());
			System.out.println("phone number:" + e.getPhone());
			System.out.println("Email id:" + e.getEmail());
			System.out.println("GST :" + e.getGst());
		} else {
			System.err.println("You have entered an Invalid Phone or Password");
		}
	}
	
	private static void verifyMerchByEmail() {
		System.out.println("Enter the Merchant Email and password to verify");
		String email = sc.next();
		String password = sc.next();
		Merchant e = mdao.verifyMerchant(email, password);
		if (e != null) {
			System.err.println("verification Succesfull");
			System.out.println("Merchant Id:" + e.getId());
			System.out.println("Merchant Name:" + e.getName());
			System.out.println("phone number:" + e.getPhone());
			System.out.println("Email id:" + e.getEmail());
			System.out.println("GST :" + e.getGst());
		} else {
			System.err.println("You have entered an Invalid Email or Password");
		}
	}
	
	private static void findProdByBrand() {
		System.out.println("Enter the Product brand to find");
		String brand = sc.next();
		List<Product> products =  pdao.findProdByBrand(brand);
		if(products.size()>0) {
			for(Product p: products) {
				System.out.println("=======================================");
				System.out.println("Product Id :" + p.getId());
				System.out.println("Product Name :"+ p.getName());
				System.out.println("Product Brand :" + p.getBrand());
				System.out.println("Product Disc :" + p.getDiscription());
				System.out.println("Product Category :" + p.getCategory());
				System.out.println("Product Cost :" + p.getCost());
				System.out.println("=======================================");
			}
		}
		else
		{
			System.err.println("You have entered invalid Product brand");
		}
	}
	
	private static void findProdByCate() {
		System.out.println("Enter the Product Category to find");
		String cate = sc.next();
		List<Product> products =  pdao.findProdByCate(cate);
		if(products.size()>0) {
			for(Product p: products) {
				System.out.println("=======================================");
				System.out.println("Product Id :" + p.getId());
				System.out.println("Product Name :"+ p.getName());
				System.out.println("Product Brand :" + p.getBrand());
				System.out.println("Product Disc :" + p.getDiscription());
				System.out.println("Product Category :" + p.getCategory());
				System.out.println("Product Cost :" + p.getCost());
				System.out.println("=======================================");
			}
		}
		else
		{
			System.err.println("You have entered invalid Product Category");
		}
	}
	
	private static void findProdByMerchId() {
		System.out.println("Enter the Merchant id to find");
		int mid = sc.nextInt();
		List<Product> products = pdao.findProdByMerchId(mid);
		if(products.size()>0) {
			for(Product p: products) {
				System.out.println("=======================================");
				System.out.println("Product Id :" + p.getId());
				System.out.println("Product Name :"+ p.getName());
				System.out.println("Product Brand :" + p.getBrand());
				System.out.println("Product Disc :" + p.getDiscription());
				System.out.println("Product Category :" + p.getCategory());
				System.out.println("Product Cost :" + p.getCost());
				System.out.println("=======================================");
			}
		}
		else
		{
			System.err.println("You have entered invalid Merchant Id");
		}
	}
}
