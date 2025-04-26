package br.edu.unipe.domain.costumer.dto;

import br.edu.unipe.domain.address.dto.AddressInputDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class CostumerInputDTO {
    @NotBlank(message = "Name is required")
    public String name;

    @Pattern(regexp = "^\\+\\d{1,3} \\d{2} \\d{4,5}-\\d{4}$", message = "Invalid phone format")
    public String cellPhone;

    @Email(message = "Invalid email format")
    public String email;

    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", message = "Invalid CPF format")
    public String cpf;

    @Past(message = "Birth date must be in the past")
    public LocalDate birthDate;

    @Valid
    public AddressInputDTO address;
}
