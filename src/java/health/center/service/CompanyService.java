package health.center.service;

import java.util.List;
import health.center.model.Company;

/**
 *
 * @author LEOGOLD
 */
public interface CompanyService {
    
    public void save();
    public Company login();
    public Company retrieveOne();
    public List<Company> retrieveAll();
}
