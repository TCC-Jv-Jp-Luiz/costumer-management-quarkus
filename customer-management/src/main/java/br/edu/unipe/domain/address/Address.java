package br.edu.unipe.domain.address;

import java.time.LocalDateTime;
import java.util.UUID;

import br.edu.unipe.domain.auditlogInfo.AuditLogInfo;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address extends PanacheEntityBase {
    @Id
    @GeneratedValue
    public UUID id;

    public String street;
    public String complement;
    public String city;
    public String state;
    public String postalCode;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "audit_log_info_id", referencedColumnName = "id")
    public AuditLogInfo auditLogInfo;

    public AuditLogInfo getAuditLogInfo() {
        return auditLogInfo;
    }

    public void setAuditInfo(AuditLogInfo auditLogInfo) {
        this.auditLogInfo = auditLogInfo;
    }

    @PrePersist
    public void onCreate() {
        if (this.auditLogInfo == null) {
            this.auditLogInfo = new AuditLogInfo();
        }
        this.auditLogInfo.createdAt = LocalDateTime.now();
        this.auditLogInfo.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.auditLogInfo.updatedAt = LocalDateTime.now();
    }
    
}
