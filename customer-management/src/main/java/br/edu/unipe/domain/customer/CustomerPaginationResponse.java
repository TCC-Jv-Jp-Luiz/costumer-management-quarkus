package br.edu.unipe.domain.customer;

import br.edu.unipe.domain.customer.dto.CustomerOutputDTO;

import java.util.List;

public class CustomerPaginationResponse {
    public int limit;
    public int offset;
    public long totalCount;
    public List<CustomerOutputDTO> data;

    public CustomerPaginationResponse(int limit, int offset, long totalCount, List<CustomerOutputDTO> data) {
        this.limit = limit;
        this.offset = offset;
        this.totalCount = totalCount;
        this.data = data;
    }
}
