package br.edu.unipe.domain.customer;

import br.edu.unipe.domain.customer.dto.CustomerInputDTO;
import br.edu.unipe.domain.customer.dto.CustomerOutputDTO;
import br.edu.unipe.domain.address.Address;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;

import java.util.UUID;

@ApplicationScoped
public class CustomerService {

    public CustomerOutputDTO createCustomer(CustomerInputDTO input) {
        Customer customer = new Customer();
        customer.setName(input.getName());
        customer.setCellPhone(input.getCellPhone());
        customer.setEmail(input.getEmail());
        customer.setCpf(input.getCpf());
        customer.setBirthDate(input.getBirthDate());

        Address address = new Address();
        address.setStreet(input.getAddress().getStreet());
        address.setComplement(input.getAddress().getComplement());
        address.setCity(input.getAddress().getCity());
        address.setState(input.getAddress().getState());
        address.setPostalCode(input.getAddress().getPostalCode());

        customer.setAddress(address);

        customer.persist();

        return CustomerOutputDTO.from(customer);
    }

    public CustomerOutputDTO getCustomer(UUID publicId) {
        Customer customer = Customer.find("publicId", publicId).firstResult();
        if (customer == null) {
            throw new NotFoundException("Customer not found");
        }
        return CustomerOutputDTO.from(customer);
    }

}
