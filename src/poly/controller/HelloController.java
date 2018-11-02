package poly.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import java.util.List;
//
//import javax.management.Query;
////import javax.websocket.Session;
//import javax.servlet.http.HttpServletRequest;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
//import poly.Model.*;
//import org.hibernate.Query;
import org.thymeleaf.*;

import poly.Model.Catalog;
import poly.Model.UserAD;




@Controller
@RequestMapping("/index/")
public class HelloController {
	@Autowired
	SessionFactory sessionFactory;
@Transactional
@RequestMapping("thymeleaf")
public String Thunghiemthymeleaf(ModelMap model,HttpServletRequest request) {
   String action=request.getServletPath();
	Session session =sessionFactory.getCurrentSession();
	model.addAttribute("action",action);
	//// code moi nhat
    String sql2="select Max(productCode) from Catalog";
	org.hibernate.Query query2=session.createQuery(sql2);
	String list2 =(String) query2.uniqueResult();
    model.addAttribute("code1",list2);
    /// bai hat moi nhat
    String sql3=" select productDescription from Catalog where productCode=(select Max(productCode) from Catalog)";
 	org.hibernate.Query query3=session.createQuery(sql3);
 	String list3 =(String) query3.uniqueResult();
 	model.addAttribute("NoidungMax",list3);
    System.out.println("action là:"+action);
	return "HOME";
}
    @Transactional
	@RequestMapping("Home")
	public String sayHello(ModelMap model,HttpServletRequest request) {
	Session session =sessionFactory.getCurrentSession();
	///
	String action=request.getServletPath();
	model.addAttribute("action",action);
	System.out.println("action Home:"+action);
	///
	String sql2="select Max(productCode) from Catalog";
	org.hibernate.Query query2=session.createQuery(sql2);
	String list2 =(String) query2.uniqueResult();
 	model.addAttribute("code1",list2);
 	///
 	String sql3=" select productDescription from Catalog where productCode=(select Max(productCode) from Catalog)";
 	org.hibernate.Query query3=session.createQuery(sql3);
 	String list3 =(String) query3.uniqueResult();
 	model.addAttribute("NoidungMax",list3);
		return "HOME";
	}

@Transactional
@RequestMapping("Browse")
public String TrangCatalog(ModelMap model,HttpServletRequest request) {
	String action=request.getServletPath();
	model.addAttribute("action",action);
	
	Session session =sessionFactory.getCurrentSession();

	String sql1="from Catalog";
	org.hibernate.Query query1=session.createQuery(sql1);
	List<Catalog> list=query1.list();
 	model.addAttribute("listMusic",list);
	System.out.println("vào demo hello");
	
	//PHAN HIEN THI BAI HAT MOI NHAT
	String sql2="select Max(productCode) from Catalog";
	org.hibernate.Query query2=session.createQuery(sql2);
	String list2 =(String) query2.uniqueResult();
 	model.addAttribute("code1",list2);
 	/////////////////////////////////////
 	String sql3=" select productDescription from Catalog where productCode=(select Max(productCode) from Catalog)";
 	org.hibernate.Query query3=session.createQuery(sql3);
 	String list3 =(String) query3.uniqueResult();
 	model.addAttribute("NoidungMax",list3);
	return "HOME";
}
String action1;
@Transactional
@RequestMapping("listen")
public String Listen(ModelMap model, HttpServletRequest request) {
	action1=request.getQueryString();
	
	String action=request.getServletPath();
	model.addAttribute("action",action);
	
	Session session =sessionFactory.getCurrentSession();
	
	String sql1="from Catalog";
	org.hibernate.Query query1=session.createQuery(sql1);
	List<Catalog> list=query1.list();
 	model.addAttribute("listMusic",list);
 	
 	String b=action1;
 	
 	String Ten="select productDescription from Catalog where productCode=:a1 ";
	org.hibernate.Query query7=session.createQuery(Ten);
	query7.setParameter("a1",b);
	String list7 =(String) query7.uniqueResult();
	model.addAttribute("ten",list7);
	
	String Ten1="select productPrice from Catalog where productCode=:a2 ";
	org.hibernate.Query query8=session.createQuery(Ten1);
	query8.setParameter("a2",b);
	double list8 = (double) query8.uniqueResult();
	model.addAttribute("ten1",list8);
	
	model.addAttribute("MA",b);
	return "HOME";
}

@Transactional
@RequestMapping("Login")
public String TrangAdmin(ModelMap model,HttpServletRequest request) {
	String action=request.getServletPath();
	model.addAttribute("action",action);
	
	Session session =sessionFactory.getCurrentSession();	 
	String sql2="select Max(productCode) from Catalog";
	org.hibernate.Query query2=session.createQuery(sql2);
	String list2 =(String) query2.uniqueResult();
 	model.addAttribute("code1",list2);
 	
 	String sql3=" select productDescription from Catalog where productCode=(select Max(productCode) from Catalog)";
 	org.hibernate.Query query3=session.createQuery(sql3);
 	String list3 =(String) query3.uniqueResult();
 	model.addAttribute("NoidungMax",list3);
	return "HOME";
}
@Transactional
@RequestMapping("DangNhapLS")
public String DangNhapListen(ModelMap model, HttpServletRequest request) {
	 String action=request.getServletPath();
	 model.addAttribute("action",action);
	 return "HOME";
}
@Transactional
@RequestMapping("DangNhapTC")
public String DangNhapThanhCong(ModelMap model, HttpServletRequest request) {
	Session session =sessionFactory.getCurrentSession();	

	String mail=request.getParameter("email");
	String FN=request.getParameter("first");
	String LN=request.getParameter("last");
	String sql1="from UserAD where email=:email and firstName=:FN and lastName=:LN";
	org.hibernate.Query query1=session.createQuery(sql1);
	query1.setParameter("email",mail);
	query1.setParameter("FN",FN);
	query1.setParameter("LN",LN);
	List<UserAD> list=query1.list();
	if(list.size()==0) {
		String action="/index/DangNhapLS.htm";
		model.addAttribute("action",action);
		String tb="Thong Tin khong chinh xac";
		model.addAttribute("thongbao",tb);
		return "HOME";
	}else {
		String action=request.getServletPath();
		model.addAttribute("action",action);
		String a=action1;
		
		
		String Ten="select productDescription from Catalog where productCode=:a1 ";
		org.hibernate.Query query4=session.createQuery(Ten);
		query4.setParameter("a1",a);
		String list4 =(String) query4.uniqueResult();
		model.addAttribute("ten",list4);
		
		String Ten1="select productPrice from Catalog where productCode=:a2 ";
		org.hibernate.Query query5=session.createQuery(Ten1);
		query5.setParameter("a2",a);
		double list5 =(double) query5.uniqueResult();
		model.addAttribute("ten1",list5);
		
		model.addAttribute("MA",a);
	    return "HOME";
	}
}

// public  String ANHMAX() {
//	 Session session =sessionFactory.getCurrentSession();
//	 String sql2="select Max(productCode) from Catalog";
//	 org.hibernate.Query query2=session.createQuery(sql2);
//	 //List<Catalog> list2=query2.list();
//	 String list2 =(String) query2.uniqueResult();
//	 return list2;
//}



}
