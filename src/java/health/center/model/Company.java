package health.center.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author LEOGOLD
 */
@Entity
@Table(name = "company")
public class Company {

    @Id
    @Column(name = "companyId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String companyName;
    
    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String email;
    
    @OneToMany(fetch = FetchType.LAZY, 
            targetEntity = Payment.class,
            mappedBy = "companyId")
    private List<Payment> payments ;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date modified;

    public Company() {
    }

    public Company(String username, String password, String companyName, String phoneNumber, String email) {
        this.username = username;
        this.password = password;
        this.companyName = companyName; 
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.created = created;
        this.modified = modified;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
 
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    @Override
    public String toString() {
        return "Company{" + "companyId=" + companyId + ", username=" + username + ", password=" + password + ", companyName=" + companyName + ",phoneNumber=" + phoneNumber + ", email=" + email + ", payments=" + payments + ", created=" + created + ", modified=" + modified + '}';
    }
    
}
