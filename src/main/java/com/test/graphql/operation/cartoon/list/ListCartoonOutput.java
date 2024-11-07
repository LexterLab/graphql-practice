package com.test.graphql.operation.cartoon.list;

import com.test.graphql.operation.base.OperationOutput;
import lombok.Builder;

import java.util.List;

@Builder
public record ListCartoonOutput(
        Integer pageNo,
        Integer pageSize,
        Long totalElements,
        Integer totalPages,
        Boolean last,
        List<CartoonInfo> cartoons
) implements OperationOutput {
}
