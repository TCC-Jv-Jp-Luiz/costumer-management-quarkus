package br.edu.unipe.controller;

import br.edu.unipe.domain.address.Address;
import br.edu.unipe.domain.costumer.Costumer;
import br.edu.unipe.domain.costumer.CostumerPaginationResponse;
import br.edu.unipe.domain.costumer.dto.CostumerInputDTO;
import br.edu.unipe.domain.costumer.dto.CostumerOutputDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Path("/costumers")
@Produces("application/json")
@Consumes("application/json")
public class CostumerService {
    @POST
    @Transactional
    public Response createCostumer(@Valid CostumerInputDTO input) {
        Costumer costumer = new Costumer();
        costumer.name = input.name;
        costumer.cellPhone = input.cellPhone;
        costumer.email = input.email;
        costumer.cpf = input.cpf;
        costumer.birthDate = input.birthDate;

        costumer.address = new Address();
        costumer.address.street = input.address.street;
        costumer.address.complement = input.address.complement;
        costumer.address.city = input.address.city;
        costumer.address.state = input.address.state;
        costumer.address.postalCode = input.address.postalCode;

        costumer.persist();

        return Response.status(Response.Status.CREATED)
                .entity(CostumerOutputDTO.from(costumer))
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getCostumer(@PathParam("id") UUID id) {
        Costumer person = Costumer.findById(id);
        if (person == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(CostumerOutputDTO.from(person)).build();
    }

    @GET
    public Response listCostumer(
            @QueryParam("limit") @DefaultValue("10") int limit,
            @QueryParam("offset") @DefaultValue("0") int offset) {

        var query = Costumer.findAll();
        long total = query.count();
        List<Costumer> persons = query.range(offset, offset + limit - 1).list();

        List<CostumerOutputDTO> data = persons.stream()
                .map(CostumerOutputDTO::from)
                .collect(Collectors.toList());

        return Response.ok(new CostumerPaginationResponse(limit, offset, total, data)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateCostumer(@PathParam("id") UUID id, @Valid CostumerInputDTO input) {
        Costumer costumer = Costumer.findById(id);
        if (costumer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        costumer.name = input.name;
        costumer.cellPhone = input.cellPhone;
        costumer.email = input.email;
        costumer.cpf = input.cpf;
        costumer.birthDate = input.birthDate;

        costumer.address.street = input.address.street;
        costumer.address.complement = input.address.complement;
        costumer.address.city = input.address.city;
        costumer.address.state = input.address.state;
        costumer.address.postalCode = input.address.postalCode;

        return Response.ok(CostumerOutputDTO.from(costumer)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteCostumer(@PathParam("id") UUID id) {
        Costumer person = Costumer.findById(id);
        if (person == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        person.delete();
        return Response.noContent().build();
    }
}
