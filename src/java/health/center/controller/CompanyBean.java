package health.center.controller;

import health.center.model.Company;
import health.center.service.CompanyService;
import health.center.model.Payment;
import health.center.utils.FileUpload;
import health.center.utils.SessionUtils;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author LEOGOLD
 */
@Named(value = "companyBean")
@SessionScoped
public class CompanyBean implements java.io.Serializable {

    private String email;
    private String phone;
    private String username;
    private String password;
    private String password2;
    private String title;
    private String companyName;
    private String fullName;
    private boolean signature;
    private String purposeOfPayment;
    private String paymentVoucherNum;
    private String amountInWords;
    private double amount;
    private String bank;
    private String receipt;
    private Date month;
    private Date createdDate;
    private Date modifiedDate;
    private Date dateOfPayment;
    private String currentForm = "New Account";
    private int currentStage = 1;
    private int pageCounter = 1;
    private String nextButton = "New Payment";
    private String previousButtton = "Cancel";
    private final int totalStage;
    private UploadedFile paymentReceipt;
    private final String pageNavigation[] = {"index", "new_account", "new_payment", "confirmation"};
    private final Map<String, String> pageMap;
    private List<Payment> allReceipt;
    @Autowired
    CompanyService companyService;

    public CompanyBean() {
        this.pageMap = new HashMap<>();
        pageMap.clear();
        pageMap.put("index", "Cancel");
        pageMap.put("new_account", "New Account Creation");
        pageMap.put("new_payment", "New Payment");
        pageMap.put("confirmation", "Details Confirmation");
        totalStage = pageNavigation.length - 1;
    }

    public String saveNewAccount() {
        Company company = new Company(username, password, companyName, fullName, phone, email);
        companyService.save(company);
        clearCompanyDetails();
//        pageCounter = 2;
//        dynamicText(pageCounter);
        FacesMessage message = new FacesMessage("Registration successful! Please login to make payment");
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        return "login";
    }

    public void setCompanyDetails(Company company) {
        setUsername(company.getUsername());
        setCompanyName(company.getCompanyName());
        setPhone(company.getPhoneNumber());
        setEmail(company.getEmail());
    }

    public void clearCompanyDetails() {
        setPassword(null);
        setCompanyName(null);
        setPhone(null);
        setEmail(null);
    }

    public void handleFileUpload(FileUploadEvent event) {
        receipt = new FileUpload().upload(event.getFile(), username);
        if (!receipt.equals("error")) {
            FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            FacesMessage message = new FacesMessage("Receipt upload FAILED", "Ensure that file is either a pdf or image file, and file size is less than 2MB");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public String makePayment() {
        Company company = new Company();
        company.setCompanyId(SessionUtils.getCompanyId());
        if (!receipt.equals("error") && receipt != null) {
            Payment payment = new health.center.model.Payment(company, fullName, title, signature, purposeOfPayment, paymentVoucherNum, amountInWords, amount, bank, receipt, month, dateOfPayment);
            companyService.makePayment(payment);
            pageCounter = 3;
            dynamicText(pageCounter);
            return "confirmation?faces-redirect=true";
        } else {
            FacesMessage message = new FacesMessage("Error", "No receipt has been uploaded");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "new_payment";
        }
    }

    public String paymentDetails(int paymentId) {
        //this method will show more details about the the current payment beign viewed
        return "confirmation?faces-redirect=true";
    }

    public String oneCompanyReceipt(int companyId) {
//        The list of all the receipts from the db for a particular company should asigned to allReceipt variabe
        return "all_receipt?faces-redirect=true";
    }

    public String receiptToPdf(int paymentId) {
        //add your code here 
        return "html_to_pdf?faces-redirect=true";
    }

    public void printReceipt() {

    }

    public String updateDetails() {
        return "confrimation?faces-redirect=true";
    }

    public String login() {
        try {
            Company company = companyService.login(username, password);
            SessionUtils.getSession().setAttribute("companyId", company.getCompanyId());
            SessionUtils.getSession().setAttribute("username", company.getUsername());
            setCompanyDetails(company);
        } catch (NullPointerException e) {
            return "login";
        }
        return "company_dashboard?faces-redirect=true";
    }

    public List allPayment() {
        return null;
    }

    public String newPayment() {
        String usrname = SessionUtils.getUserName();
        if (usrname != null) {
            return "new_payment?faces-redirect=true";
        } else {
            return saveNewAccount();
        }
    }

    public void dynamicText(int pageC) {
        currentStage = pageC;
        String cPage = null;
        while (pageC < pageNavigation.length) {
            cPage = pageNavigation[pageC];
            String mapValue = pageMap.get(cPage);
            setNextButton(mapValue);
            setCurrentForm(mapValue);

        }

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UploadedFile getPaymentReceipt() {
        return paymentReceipt;
    }

    public void setPaymentReceipt(UploadedFile paymentReceipt) {
        this.paymentReceipt = paymentReceipt;
    }

    public String getCurrentForm() {
        return currentForm;
    }

    public void setCurrentForm(String currentForm) {
        this.currentForm = currentForm;
    }

    public int getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(int currentStage) {
        this.currentStage = currentStage;
    }

    public CompanyService getCompanyService() {
        return companyService;
    }

    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    public String getNextButton() {
        return nextButton;
    }

    public void setNextButton(String nextButton) {
        this.nextButton = nextButton;
    }

    public String getPreviousButtton() {
        return previousButtton;
    }

    public void setPreviousButtton(String previousButtton) {
        this.previousButtton = previousButtton;
    }

//    public int getPageCounter() {
//        return pageCounter;
//    }
//
//    public void setPageCounter(int pageCounter) {
//        this.pageCounter = pageCounter;
//    }
    public int getTotalStage() {
        return totalStage;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public List<Payment> getAllReceipt() {
        return allReceipt;
    }

    public void setAllReceipt(List<Payment> allReceipt) {
        this.allReceipt = allReceipt;
    }

}
