package com.test.graphql.operation.cartoon.add;

import com.test.graphql.operation.base.OperationOutput;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record AddCartoonOutput(
        @Schema(example = "Courage the cowardly dog")
        String name
) implements OperationOutput {
}
