package br.edu.unipe.domain.address.dto;


import br.edu.unipe.domain.address.Address;
import br.edu.unipe.domain.address.State;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class AddressOutputDTO {
    private String street;
    private String complement;
    private String city;
    private State state;
    private String postalCode;

    public static AddressOutputDTO from(Address address) {
        AddressOutputDTO dto = new AddressOutputDTO();
        dto.street = address.getStreet();
        dto.complement = address.getComplement();
        dto.city = address.getCity();
        dto.state = address.getState();
        dto.postalCode = address.getPostalCode();
        return dto;
    }

    public String getStreet() { return street; }
    public String getComplement() { return complement; }
    public String getCity() { return city; }
    public State getState() { return state; }
    public String getPostalCode() { return postalCode; }
}
