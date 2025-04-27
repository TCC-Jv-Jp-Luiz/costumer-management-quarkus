package br.edu.unipe.domain.customer.dto;

import br.edu.unipe.domain.address.dto.AddressInputDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class CustomerInputDTO {
    @NotBlank(message = "Name is required")
    private String name;

    @Pattern(regexp = "^\\+\\d{1,3} \\d{2} \\d{4,5}-\\d{4}$", message = "Invalid phone format")
    private String cellPhone;

    @Email(message = "Invalid email format")
    private String email;

    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", message = "Invalid CPF format")
    private String cpf;

    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;

    @Valid
    private AddressInputDTO address;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCellPhone() { return cellPhone; }
    public void setCellPhone(String cellPhone) { this.cellPhone = cellPhone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public AddressInputDTO getAddress() { return address; }
    public void setAddress(AddressInputDTO address) { this.address = address; }
}
