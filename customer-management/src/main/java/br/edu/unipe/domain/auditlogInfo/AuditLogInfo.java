package br.edu.unipe.domain.auditlogInfo;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "audit_log_info")
public class AuditLogInfo {
    @Id
    @GeneratedValue
    public UUID id;

    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;
}
