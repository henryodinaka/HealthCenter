package health.center.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private String fullName;

    @Column(nullable = false)
    private String title;
        
    @Column(nullable = false)
    private boolean signature;
    
    @Column(nullable = false)
    private String purposeOfPayment;

    @Column(nullable = false)
    private String paymentVoucherNum;

    @Column(nullable = false)
    private String amountInWords;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String bank;
    
    @Column(nullable = false)
    private String receipt;
    
    @Column(nullable = false)
    private Date month;

    @Column(nullable = false)
    private Date dateOfPayment;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date modified;

    public Company() {
    }

    public Company(String username, String password, String companyName, String fullName,String title, boolean signature, String purposeOfPayment, String paymentVoucherNum, String amountInWords, double amount, String bank, String receipt, Date month, Date dateOfPayment) {
        this.username = username;
        this.password = password;
        this.companyName = companyName;
        this.fullName = fullName;
        this.title = title;
        this.signature = signature;
        this.purposeOfPayment = purposeOfPayment;
        this.paymentVoucherNum = paymentVoucherNum;
        this.amountInWords = amountInWords;
        this.amount = amount;
        this.bank = bank;
        this.receipt = receipt;
        this.month = month;
        this.dateOfPayment = dateOfPayment;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSignature() {
        return signature;
    }

    public void setSignature(boolean signature) {
        this.signature = signature;
    }

    public String getPurposeOfPayment() {
        return purposeOfPayment;
    }

    public void setPurposeOfPayment(String purposeOfPayment) {
        this.purposeOfPayment = purposeOfPayment;
    }

    public String getPaymentVoucherNum() {
        return paymentVoucherNum;
    }

    public void setPaymentVoucherNum(String paymentVoucherNum) {
        this.paymentVoucherNum = paymentVoucherNum;
    }

    public String getAmountInWords() {
        return amountInWords;
    }

    public void setAmountInWords(String amountInWords) {
        this.amountInWords = amountInWords;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public Date getMonth() {
        return month;
    }

    public void setMonth(Date month) {
        this.month = month;
    }

    public Date getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(Date dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
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
        return "Company{" + "companyId=" + companyId + ", username=" + username + ", password=" + password + ", companyName=" + companyName + ", fullName=" + fullName + ", signature=" + signature + ", purposeOfPayment=" + purposeOfPayment + ", paymentVoucherNum=" + paymentVoucherNum + ", amountInWords=" + amountInWords + ", amount=" + amount + ", bank=" + bank + ", receipt=" + receipt + ", month=" + month + ", dateOfPayment=" + dateOfPayment + ", created=" + created + ", modified=" + modified + '}';
    }
    
    
}
