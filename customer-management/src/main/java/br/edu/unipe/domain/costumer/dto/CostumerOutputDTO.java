package br.edu.unipe.domain.costumer.dto;

import java.time.LocalDate;
import java.util.UUID;

import br.edu.unipe.domain.address.Address;
import br.edu.unipe.domain.auditlogInfo.AuditLogInfo;
import br.edu.unipe.domain.costumer.Costumer;

public class CostumerOutputDTO {
    public UUID id;
    public String name;
    public String cellPhone;
    public String email;
    public String cpf;
    public LocalDate birthDate;
    public Address address;
    public AuditLogInfo auditLogInfo;

    public static CostumerOutputDTO from(Costumer costumer) {
        CostumerOutputDTO dto = new CostumerOutputDTO();
        dto.id = costumer.id;
        dto.name = costumer.name;
        dto.cellPhone = costumer.cellPhone;
        dto.email = costumer.email;
        dto.cpf = costumer.cpf;
        dto.birthDate = costumer.birthDate;
        dto.address = costumer.address;
        dto.auditLogInfo = costumer.auditLogInfo;
        return dto;
    }
}
