package controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List; 
import java.util.Date;
import java.util.Iterator; 
import java.util.Map;

import model.KiemTra;

import org.hibernate.Criteria;
import org.hibernate.HibernateException; 
import org.hibernate.SQLQuery;
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;

public class ManageEmployee {
	   private static SessionFactory factory=new Configuration().configure().addAnnotatedClass(KiemTra.class).buildSessionFactory();
	   public static void main(String[] args) {
	      try{
//	         factory = new AnnotationConfiguration().
//	                   configure().
//	                   //addPackage("com.xyz") //add package if used.
//	                   addAnnotatedClass(Employee.class).
//	                   buildSessionFactory();
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
	      ManageEmployee ME = new ManageEmployee();
	      String pattern = "dd-MM-yyyy";
	      SimpleDateFormat format = new SimpleDateFormat(pattern);
	      Date date = null;
		try {
			date = format.parse("26-06-1994");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      /* Add few employee records in database */
			ME.addEmployee("TX001","Thiều anh nhất1", date);
//			ME.addEmployee("TX004","đặng Liên Minh1", date);
	      
	     
	      /* List down all the employees */
	    // ME.listEmployees();
//
//	      /* Update employee's records */
//	      ME.updateEmployee(empID1, 5000);
//
//	      /* Delete an employee from the database */
//	      ME.deleteEmployee(empID2);
//
//	      /* List down new list of the employees */
//	      ME.listEmployees();
	   }
	   /* Method to CREATE an employee in the database */
	   public void addEmployee(String MaTX,String HoTen, Date NgaySinh){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         KiemTra employee = new KiemTra();
	         employee.setMaTX(MaTX);
	         employee.setHoTen(HoTen);
	         employee.setNgaySinh(NgaySinh);
	         session.save(employee); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	    	 session.flush();
	         session.close(); 
	      }
	   }
//	   public void totalSalary(){
//		      Session session = factory.openSession();
//		      Transaction tx = null;
//		      try{
//		         tx = session.beginTransaction();
//		         Criteria cr = session.createCriteria(Employee.class);
//
//		         // To get total salary.
//		         cr.setProjection(Projections.sum("salary"));
//		         List totalSalary = cr.list();
//
//		         System.out.println("Total Salary: " + totalSalary.get(0) );
//		         tx.commit();
//		      }catch (HibernateException e) {
//		         if (tx!=null) tx.rollback();
//		         e.printStackTrace(); 
//		      }finally {
//		         session.close(); 
//		      }
//		   }
//	   /* Method to  READ all the employees */
	   public void listEmployees( ){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         List employees = session.createQuery("FROM KiemTra").list(); 
	         for (Iterator iterator = 
	                           employees.iterator(); iterator.hasNext();){
	        	 KiemTra employee = (KiemTra) iterator.next(); 
	            System.out.print("First Name: " + employee.getMaTX()); 
	            System.out.print("  Last Name: " + employee.getHoTen()); 
	            System.out.println("  Salary: " + employee.getNgaySinh()); 
	         }
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }
//	   /* Method to UPDATE salary for an employee */
//	   public void updateEmployee(Integer EmployeeID, int salary ){
//	      Session session = factory.openSession();
//	      Transaction tx = null;
//	      try{
//	         tx = session.beginTransaction();
//	         Employee employee = 
//	                    (Employee)session.get(Employee.class, EmployeeID); 
//	         employee.setSalary( salary );
//			 session.update(employee); 
//	         tx.commit();
//	      }catch (HibernateException e) {
//	         if (tx!=null) tx.rollback();
//	         e.printStackTrace(); 
//	      }finally {
//	         session.close(); 
//	      }
//	   }
//	   /* Method to DELETE an employee from the records */
//	   public void deleteEmployee(Integer EmployeeID){
//	      Session session = factory.openSession();
//	      Transaction tx = null;
//	      try{
//	         tx = session.beginTransaction();
//	         Employee employee = 
//	                   (Employee)session.get(Employee.class, EmployeeID); 
//	         session.delete(employee); 
//	         tx.commit();
//	      }catch (HibernateException e) {
//	         if (tx!=null) tx.rollback();
//	         e.printStackTrace(); 
//	      }finally {
//	         session.close(); 
//	      }
//	   }
//	   
//	   /* Method to  READ all the employees using Scalar Query */
//	   public void listEmployeesScalar( ){
//	      Session session = factory.openSession();
//	      Transaction tx = null;
//	      try{
//	         tx = session.beginTransaction();
//	         String sql = "SELECT first_name, salary FROM EMPLOYEE";
//	         SQLQuery query = session.createSQLQuery(sql);
//	         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
//	         List data = query.list();
//
//	         for(Object object : data)
//	         {
//	            Map row = (Map)object;
//	            System.out.print("First Name: " + row.get("first_name")); 
//	            System.out.println(", Salary: " + row.get("salary")); 
//	         }
//	         tx.commit();
//	      }catch (HibernateException e) {
//	         if (tx!=null) tx.rollback();
//	         e.printStackTrace(); 
//	      }finally {
//	         session.close(); 
//	      }
//	   }
//
//	   /* Method to  READ all the employees using Entity Query */
//	   public void listEmployeesEntity( ){
//	      Session session = factory.openSession();
//	      Transaction tx = null;
//	      try{
//	         tx = session.beginTransaction();
//	         String sql = "SELECT * FROM EMPLOYEE";
//	         SQLQuery query = session.createSQLQuery(sql);
//	         query.addEntity(Employee.class);
//	         List employees = query.list();
//
//	         for (Iterator iterator = 
//	                           employees.iterator(); iterator.hasNext();){
//	            Employee employee = (Employee) iterator.next(); 
//	            System.out.print("First Name: " + employee.getFirstName()); 
//	            System.out.print("  Last Name: " + employee.getLastName()); 
//	            System.out.println("  Salary: " + employee.getSalary()); 
//	         }
//	         tx.commit();
//	      }catch (HibernateException e) {
//	         if (tx!=null) tx.rollback();
//	         e.printStackTrace(); 
//	      }finally {
//	         session.close(); 
//	      }
//	   }
	}