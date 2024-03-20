package in.ineuron.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;



/**
 * 
 * @author Sowmya Peddi
 * This Caching is associated with sessionFactory so it is called as "Global cache".
Application will start to search for entity Object in below mentioned order
   1. L1 cache of current session.
   2. If not present in L1 cache then searches in L2 cache.
   3. If not present in L2 cache then searches in Database, then obtained record is then placed in both L1 & L2 cache then give it to Application.
      L2 cache is not configured by Hibernate by default, Programmer need to configure manually, It can be enable or disable based on our requirement.
	  Hibernate supports L2 cache through "EHCache(EasyHibernateCache)".

To configure EHCache in our Hibernate projects we use 
------------------------------------------------------
   1. Add EHCache jars to the project (present in hibernate> lib> optional> ehcache)
   2. Make changes in hibernate.cfg.xml file as shown below
    <property name="hibernate.cache.use_second_level_cache" >true</property>
    <property name="hibernate.cache.region.factory_class" >org.hibernate.cache.ehcache.EhCacheRegionFactory</property>
   3. In the model class inform hibernate to use Caching strategy.
    @Cache(usage=CacheConcurrencyStrategy.READ_ONLY) after @Entity annotation
 */
@Entity
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)   // It Specifies Caching Strategy
public class InsurancePolicy implements Serializable {

	private static final long serialVersionUID = 3940931891329205809L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long policyNo;
	
	@Column(length = 20)
	private String policyName;
	private String policyType;
	private String company;
	private Integer tenure;
	
	public Long getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(Long policyNo) {
		this.policyNo = policyNo;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Integer getTenure() {
		return tenure;
	}

	public void setTenure(Integer tenure) {
		this.tenure = tenure;
	}

	public InsurancePolicy() {
	}
	
	@Override
	public String toString() {
		return "InsurancePolicy [policyNo=" + policyNo + ", policyName=" + policyName + ", policyType=" + policyType
				+ ", company=" + company + ", tenure=" + tenure + "]";
	}
	
	
}
