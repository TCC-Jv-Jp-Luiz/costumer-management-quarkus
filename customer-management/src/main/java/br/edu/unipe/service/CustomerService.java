package br.edu.unipe.service;

import br.edu.unipe.domain.address.dto.AddressInputDTO;
import br.edu.unipe.domain.customer.Customer;
import br.edu.unipe.domain.customer.CustomerPaginationResponse;
import br.edu.unipe.domain.customer.dto.CustomerInputDTO;
import br.edu.unipe.domain.customer.dto.CustomerOutputDTO;
import br.edu.unipe.domain.address.Address;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
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

    public CustomerPaginationResponse listCustomers(int limit, int offset) {
        PanacheQuery<PanacheEntityBase> query = Customer.findAll();
        List<Customer> customers = query.range(offset, offset + limit - 1).list();

        List<CustomerOutputDTO> data = customers.stream()
                .map(CustomerOutputDTO::from)
                .toList();

        long totalCount = Customer.count();
        return new CustomerPaginationResponse(limit, offset, totalCount, data);
    }

}
