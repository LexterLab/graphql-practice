package com.test.graphql.operation.cartoon.update;

import com.test.graphql.operation.base.OperationInput;
import lombok.*;

@Builder
public record UpdateCartoonInput (
        String name
) implements OperationInput {}