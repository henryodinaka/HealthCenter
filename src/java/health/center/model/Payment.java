/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package health.center.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author LEOGOLD
 */
@Entity
@Table(name = "company")
public class Payment {
    
    @Id
    @Column(name = "paymentId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Company.class)
    @JoinColumn(name = "companyId",foreignKey = @ForeignKey (name= "FK_Payment_Company"))
    private Company companyId;

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

}
