/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package health.center.controller;

import health.center.model.Administrator;
import health.center.model.Company;
import health.center.model.Payment;
import health.center.service.AdminService;
import health.center.service.CompanyService;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Blaccop Group
 */
@Named("AdminBean")
@SessionScoped
public class AdminController implements java.io.Serializable {

    private String username;
    private String fullName;
    private String password;
    private String confirmPassword;
    private Company company;
    @Autowired
    private AdminService adminService;
    @Autowired
    private CompanyService companyService;
    
    public AdminController() {
    }

    public String createAccount() {
        Administrator admin = new Administrator(username, fullName, password);
        adminService.createAccount(admin);
        return "adminLogin?faces-redirect=true";
    }
    
    public String login(){
        try{
            Administrator admin = adminService.login(username, password);
            setAdmin(admin);
            return "admin_dashBoard?faces-redirect=true";
        } catch (NullPointerException e){
            return "adminLogin";
        }
    }
    
    public void setAdmin(Administrator admin){
        setUsername(admin.getUsername());
        setFullName(admin.getFullName());
    }
    
    public String viewReceipts(Company company){
        this.company = company;
        return "all_receipt?faces-redirect=true";
    }
    
    public List<Payment> getCompanyReceipts(){
        return companyService.getAllPayments(company.getCompanyId());
    }
    
    public Company getCompany(){
        return company;
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
