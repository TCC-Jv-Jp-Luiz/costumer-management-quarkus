package br.edu.unipe.domain.costumer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import br.edu.unipe.domain.address.Address;
import br.edu.unipe.domain.auditlogInfo.AuditLogInfo;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
public class Costumer extends PanacheEntityBase {
    @Id
    @GeneratedValue
    public UUID id;

    public String name;
    public String cellPhone;
    public String email;
    public String cpf;
    public LocalDate birthDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    public Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "audit_log_info_id", referencedColumnName = "id")
    public AuditLogInfo auditLogInfo;


    @PrePersist
    void onCreate() {
        if (this.auditLogInfo == null) {
            this.auditLogInfo = new AuditLogInfo();
        }
        this.auditLogInfo.createdAt = LocalDateTime.now();
        this.auditLogInfo.updatedAt = LocalDateTime.now();

        if (this.address != null && this.address.auditLogInfo == null) {
            this.address.onCreate();
        }

    }

    @PreUpdate
    void onUpdate() {
        this.auditLogInfo.updatedAt = LocalDateTime.now();
        if (this.address != null) {
            this.address.onUpdate();
        }
    }    
}
