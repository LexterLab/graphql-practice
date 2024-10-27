package com.test.graphql.operation.cartoon.add;

import com.test.graphql.operation.base.OperationOutput;
import lombok.Builder;

@Builder
public record AddCartoonOutput(
        String name
) implements OperationOutput {
}
