package br.edu.unipe.controller;

import br.edu.unipe.domain.customer.CustomerPaginationResponse;
import br.edu.unipe.service.CustomerService;
import br.edu.unipe.domain.customer.dto.CustomerInputDTO;
import br.edu.unipe.domain.customer.dto.CustomerOutputDTO;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

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
        CustomerOutputDTO output = customerService.createCustomer(input);
        return Response.status(Response.Status.CREATED).entity(output).build();
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
        CustomerOutputDTO customerOutputDTO = customerService.updateCustomer(publicId, customerInputDTO);
        return Response.ok(customerOutputDTO).build();
    }

    @DELETE
    @Path("/{publicId}")
    public Response deleteCustomer(@PathParam("publicId") UUID publicId) {
        customerService.deleteCustomer(publicId);
        return Response.noContent().build();
    }
}
