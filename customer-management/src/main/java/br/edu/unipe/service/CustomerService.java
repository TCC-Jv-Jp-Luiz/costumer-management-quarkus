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
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@ApplicationScoped
public class CustomerService {

    @Transactional
    public CustomerOutputDTO createCustomer(CustomerInputDTO input) {
        if (Customer.existsByCpf(input.getCpf())) {
            throw new WebApplicationException(
                    Response.status(Response.Status.CONFLICT)
                            .entity(Map.of("message", "CPF already registered."))
                            .build()
            );
        }

        if (Customer.existsByEmail(input.getEmail())) {
            throw new WebApplicationException(
                    Response.status(Response.Status.CONFLICT)
                            .entity(Map.of("message", "Email already registered."))
                            .build()
            );
        }

        if (Customer.existsByCellPhone(input.getCellPhone())) {
            throw new WebApplicationException(
                    Response.status(Response.Status.CONFLICT)
                            .entity(Map.of("message", "CellPhone already registered."))
                            .build()
            );
        }

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

    @Transactional
    public CustomerOutputDTO updateCustomer(UUID publicId, CustomerInputDTO customerInputDTO) {
        Customer customer = Customer.find("publicId", publicId).firstResult();

        if (customer == null) {
            throw new NotFoundException("Customer not found");
        }

        customer.setName(customerInputDTO.getName());
        customer.setCellPhone(customerInputDTO.getCellPhone());
        customer.setEmail(customerInputDTO.getEmail());
        customer.setCpf(customerInputDTO.getCpf());
        customer.setBirthDate(customerInputDTO.getBirthDate());

        Address address = customer.getAddress();
        AddressInputDTO addressInputDTO = customerInputDTO.getAddress();
        address.setStreet(addressInputDTO.getStreet());
        address.setComplement(addressInputDTO.getComplement());
        address.setCity(addressInputDTO.getCity());
        address.setState(addressInputDTO.getState());
        address.setPostalCode(addressInputDTO.getPostalCode());

        customer.persistAndFlush();

        return CustomerOutputDTO.from(customer);
    }

    @Transactional
    public void deleteCustomer(UUID publicId) {
        Customer customer = Customer.find("publicId", publicId).firstResult();

        if (customer == null) {
            throw new NotFoundException("Customer not found");
        }

        customer.delete();
    }
}
