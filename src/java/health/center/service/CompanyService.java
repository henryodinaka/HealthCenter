package health.center.service;

import java.util.List;
import health.center.model.Company;

/**
 *
 * @author LEOGOLD
 */
public interface CompanyService {
    
    public void save(Company company);
    
    public Company login(String username, String password);
    
    public Company retrieveOne();
    
    public List<Company> retrieveAll();
}
