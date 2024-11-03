package com.test.graphql.operation.cartoon.get;

import com.test.graphql.operation.base.OperationOutput;
import lombok.Builder;

@Builder
public record GetCartoonOutput(
    String name
) implements OperationOutput {
}
