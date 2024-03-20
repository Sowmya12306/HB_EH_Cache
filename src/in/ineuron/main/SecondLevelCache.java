package in.ineuron.main;

import org.hibernate.Session;

import in.ineuron.model.InsurancePolicy;
import in.ineuron.util.HibernateUtil;

public class SecondLevelCache {

	public static void main(String[] args) {
		Session session = null;
		boolean flag = false;
		Long id = 1L;
		InsurancePolicy policy = null;
		session = HibernateUtil.getSession();
		
		try {
			if(session!=null) {
				policy = session.get(InsurancePolicy.class, id);  // gets from DB(hibernate query is generated) and puts in L1 cache
				
				if(policy!=null) {
				System.out.println("1 :: "+ policy);
				System.in.read();
				policy = session.get(InsurancePolicy.class, id);  // gets from L1 cache
				System.out.println("2 :: "+ policy);
				System.out.println("=========================================");
				System.in.read();
                session.evict(policy);   //removing policy object from l1 cache
                System.in.read();
				policy = session.get(InsurancePolicy.class, id);  // gets from L2 cache and keeping it in L1 cache for usage
				System.out.println("3 :: "+ policy);
//				System.in.read();
//				policy = session.get(InsurancePolicy.class, id);  // gets from L1 cache
//				System.in.read();
//				System.out.println("4 :: "+ policy);				
//				System.in.read();
//				session.clear();
//				System.in.read();
//				policy = session.get(InsurancePolicy.class, id);  // gets from DB and puts in L1 cache
//				System.out.println("5 :: "+ policy);
				}
				else{
					System.err.println("Record doesn't exists!....");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

}
