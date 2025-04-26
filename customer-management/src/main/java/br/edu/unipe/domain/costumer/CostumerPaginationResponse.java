package br.edu.unipe.domain.costumer;

import br.edu.unipe.domain.costumer.dto.CostumerOutputDTO;

import java.util.List;

public class CostumerPaginationResponse {
    public int limit;
    public int offset;
    public long totalCount;
    public List<CostumerOutputDTO> data;

    public CostumerPaginationResponse(int limit, int offset, long totalCount, List<CostumerOutputDTO> data) {
        this.limit = limit;
        this.offset = offset;
        this.totalCount = totalCount;
        this.data = data;
    }
}
