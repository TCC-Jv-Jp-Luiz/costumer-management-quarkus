package br.edu.unipe.domain.customer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import br.edu.unipe.domain.address.Address;
import br.edu.unipe.domain.auditlogInfo.AuditLogInfo;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
public class Customer extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID publicId;

    private String name;

    private String cellPhone;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String cpf;

    private LocalDate birthDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "audit_log_info_id", referencedColumnName = "id")
    private AuditLogInfo auditLogInfo;


    @PrePersist
    void initializeFields() {
        if (this.publicId == null) {
            this.publicId = UUID.randomUUID();
        }
        if (this.auditLogInfo == null) {
            this.auditLogInfo = new AuditLogInfo();
        }
        this.auditLogInfo.setCreatedAt(LocalDateTime.now());
        this.auditLogInfo.setUpdatedAt(LocalDateTime.now());
    }


    @PreUpdate
    void onUpdate() {
        this.auditLogInfo.setUpdatedAt(LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public UUID getPublicId() {
        return publicId;
    }

    public String getName() {
        return name;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Address getAddress() {
        return address;
    }

    public AuditLogInfo getAuditLogInfo() {
        return auditLogInfo;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setPublicId(UUID publicId) {
        this.publicId = publicId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setAuditLogInfo(AuditLogInfo auditLogInfo) {
        this.auditLogInfo = auditLogInfo;
    }
}
