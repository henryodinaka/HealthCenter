package health.center.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.SessionFactory;
import health.center.model.Company;
import java.util.List;
/**
 *
 * @author LEOGOLD
 */
@Service
@Transactional
public class CompanyServiceImpl implements CompanyService{
    
    @Autowired 
    private SessionFactory sessionFactory;

    @Override
    public void save(Company company) {
        sessionFactory.getCurrentSession().save(company);
    }

    @Override
    public Company login(String username, String password) {
        return (Company) sessionFactory.getCurrentSession()
                .createQuery("FROM Company WHERE username = :username AND password = :password")
                .setParameter("username", username)
                .setParameter("password", password)
                .uniqueResult();
    }

    @Override
    public Company retrieveOne() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Company> retrieveAll() {
        return sessionFactory.getCurrentSession().createQuery("From Company").list();
    }
    
    
    
}