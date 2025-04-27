package br.edu.unipe.domain.customer.dto;

import java.time.LocalDate;
import java.util.UUID;

import br.edu.unipe.domain.address.Address;
import br.edu.unipe.domain.auditlogInfo.AuditLogInfo;
import br.edu.unipe.domain.customer.Customer;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerOutputDTO {
    public UUID id;
    public String name;
    public String cellPhone;
    public String email;
    public String cpf;
    public LocalDate birthDate;
    public Address address;
    @JsonProperty("auditInfo")
    public AuditLogInfo auditLogInfo;

    public static CustomerOutputDTO from(Customer customer) {
        CustomerOutputDTO dto = new CustomerOutputDTO();
        dto.id = customer.getPublicId();
        dto.name = customer.getName();
        dto.cellPhone = customer.getCellPhone();
        dto.email = customer.getEmail();
        dto.cpf = customer.getCpf();
        dto.birthDate = customer.getBirthDate();
        dto.address = customer.getAddress();
        dto.auditLogInfo = customer.getAuditLogInfo();
        return dto;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getCellPhone() { return cellPhone; }
    public String getEmail() { return email; }
    public String getCpf() { return cpf; }
    public LocalDate getBirthDate() { return birthDate; }
    public Address getAddress() { return address; }
    public AuditLogInfo getAuditInfo() { return auditLogInfo; }
}
