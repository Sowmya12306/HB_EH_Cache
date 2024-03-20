package in.ineuron.main;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import in.ineuron.model.InsurancePolicy;
import in.ineuron.util.HibernateUtil;

public class SecondLevelCacheDemo {	

	public static void main(String[] args) {
		Session session=null;
		boolean flag=false;
		SessionFactory sessionFactory = null;
		Transaction transaction = null;
		Long idValue = null;
		
		try {
			session = HibernateUtil.getSession();
			
			if(session!=null) {
				transaction = session.beginTransaction();
			}
			if(transaction!=null)
			{
				InsurancePolicy policy = new InsurancePolicy();
				policy.setCompany("LIC");
				policy.setPolicyName("Bhim Barosa");
				policy.setPolicyType("Health");
				policy.setTenure(12);
				idValue = (Long)session.save(policy);  //Record in l1-Cache
     			flag = true;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			flag = false;
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if(flag ==true) {
				transaction.commit(); // stored in DB through ORM
			}
			else transaction.rollback();
			HibernateUtil.closeSession(session);
		}
		
	}

}
