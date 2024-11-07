package com.test.graphql.operation.cartoon.list;

import com.test.graphql.operation.base.OperationInput;
import lombok.Builder;

@Builder(toBuilder = true)
public record ListCartoonInput(
    Integer pageNo,
    Integer PageSize,
    String sortBy,
    String sortDir
) implements OperationInput {
}
