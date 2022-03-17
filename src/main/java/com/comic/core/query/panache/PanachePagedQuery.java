package com.comic.core.query.panache;

import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PanachePagedQuery {
    private Sort sort;
    private String query;
    private Page page;
    private Parameters params;
}
