package health.center.controller;

import health.center.model.Administrator;
import health.center.model.Company;
import health.center.model.Payment;
import health.center.service.AdminService;
import health.center.service.CompanyService;
import health.center.utils.PdfWriterClass;
import java.io.IOException;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Blaccop Group
 */
@Named("adminBean")
@SessionScoped
public class AdminController implements java.io.Serializable {

    private String username;
    private String fullName;
    private String password;
    private String confirmPassword;
    private Company company;
    private Payment payment;
    @Autowired
    private AdminService adminService;
    @Autowired
    private CompanyService companyService;

    public AdminController() {
    }

    public String createAccount() {
        Administrator admin = new Administrator(username, fullName, password);
        adminService.createAccount(admin);
        return "login?faces-redirect=true";
    }

    public String test(){
        return "file_upload?faces-redirect=true";
    }
    public String login() {
        try {
            Administrator admin = adminService.login(username, password);
            setAdmin(admin);
            return "admin_dashBoard?faces-redirect=true";
        } catch (NullPointerException e) {
            return "login";
        }
    }

    public void setAdmin(Administrator admin) {
        setUsername(admin.getUsername());
        setFullName(admin.getFullName());
    }

    public String viewPayments(Company company) {
        this.company = company;
        return "all_payment?faces-redirect=true";
    }

    public String viewPayment(Payment payment) {
        this.payment = payment;
        return "html_to_pdf?faces-redirect=true";
    }

    public List<Payment> getCompanyPayments() {
        return companyService.getAllPayments(company.getCompanyId());
    }

    public void printPayment() {

    }

    public String downloadPayment() {
        try {
            new PdfWriterClass(payment).writeToFile();
        } catch (IOException e) {
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, payment.getReceipt() + " downloaded", "to " + System.getProperty("user.home") + "\\Payments");
        FacesContext.getCurrentInstance().addMessage(null, message);
        return "html_to_pdf";
    }

    public Company getCompany() {
        return company;
    }
    
    public Payment getPayment() {
        return payment;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the confirmPassword
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * @param confirmPassword the confirmPassword to set
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
