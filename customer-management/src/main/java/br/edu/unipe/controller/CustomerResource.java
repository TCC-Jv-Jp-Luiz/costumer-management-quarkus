package br.edu.unipe.controller;

import br.edu.unipe.domain.customer.CustomerPaginationResponse;
import br.edu.unipe.domain.shared.BusinessException;
import br.edu.unipe.domain.shared.DuplicateCellPhoneException;
import br.edu.unipe.domain.shared.DuplicateCpfException;
import br.edu.unipe.domain.shared.DuplicateEmailException;
import br.edu.unipe.service.CustomerService;
import br.edu.unipe.domain.customer.dto.CustomerInputDTO;
import br.edu.unipe.domain.customer.dto.CustomerOutputDTO;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.Map;
import java.util.UUID;

@Path("/customers")
@Produces("application/json")
@Consumes("application/json")
public class CustomerResource {

    @Inject
    CustomerService customerService;

    @POST
    @Transactional
    public Response createCustomer(@Valid CustomerInputDTO input) {
        try {
            CustomerOutputDTO output = customerService.createCustomer(input);
            return Response.status(Response.Status.CREATED).entity(output).build();
        } catch (DuplicateCpfException | DuplicateEmailException | DuplicateCellPhoneException ex) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(Map.of("message", ex.getMessage()))
                    .build();
        } catch (BusinessException ex) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("message", ex.getMessage()))
                    .build();
        }
    }

    @GET
    @Path("/{publicId}")
    public Response getCustomer(@PathParam("publicId") UUID publicId) {
        CustomerOutputDTO output = customerService.getCustomer(publicId);
        return Response.ok(output).build();
    }

    @GET
    public Response listCustomers(@QueryParam("limit") @DefaultValue("10") int limit,
                                  @QueryParam("offset") @DefaultValue("0") int offset) {
        CustomerPaginationResponse response = customerService.listCustomers(limit, offset);
        return Response.ok(response).build();
    }

    @PUT
    @Path("/{publicId}")
    public Response updateCustomer(@PathParam("publicId") UUID publicId, @Valid CustomerInputDTO customerInputDTO) {
        try {
            CustomerOutputDTO output = customerService.updateCustomer(publicId, customerInputDTO);
            return Response.ok(output).build();
        } catch (BusinessException ex) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(Map.of("message", ex.getMessage()))
                    .build();
        }
    }

    @DELETE
    @Path("/{publicId}")
    public Response deleteCustomer(@PathParam("publicId") UUID publicId) {
        customerService.deleteCustomer(publicId);
        return Response.noContent().build();
    }
}
