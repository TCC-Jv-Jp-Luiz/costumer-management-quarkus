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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID publicId;

    private String street;
    private String complement;
    private String city;

    @Enumerated(EnumType.STRING)
    private State state;

    private String postalCode;

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

    // Getters e Setters
    public Long getId() { return id; }
    public UUID getPublicId() { return publicId; }
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

}