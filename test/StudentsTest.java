import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class StudentsTest {
	private Session session;
	private Transaction transaction;
	private SessionFactory sessionFactory;
	
	@Test
	public void testSaveStudents(){
		Students s = new Students(1,"张三丰","男",new Date(),"武当山");
		session.save(s);
	}
	
	@Before
	public void init(){
		Configuration config = new Configuration().configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		
		sessionFactory = config.buildSessionFactory(serviceRegistry);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}
	
	@After
	public void destory()
	{
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
}
