package health.center.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import health.center.controller.CompanyBean;
import health.center.model.Company;
import java.util.List;
/**
 *
 * @author LEOGOLD
 */
@Service
@Transactional
public class CompanyServiceImpl {
    
    @Autowired
    CompanyBean companyBean;
     
    Session session ;
    @Autowired 
    SessionFactory sessionFactory;

    
    public void save() {
        
    }

    
    public Company login() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public Company retrieveOne() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public List<Company> retrieveAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
