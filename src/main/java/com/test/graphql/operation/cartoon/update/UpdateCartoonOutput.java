package com.test.graphql.operation.cartoon.update;

import com.test.graphql.operation.base.OperationOutput;
import lombok.Builder;

@Builder
public record UpdateCartoonOutput(
) implements OperationOutput {
}
