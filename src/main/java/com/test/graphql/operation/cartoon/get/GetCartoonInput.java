package com.test.graphql.operation.cartoon.get;

import com.test.graphql.operation.base.OperationInput;
import com.test.graphql.utils.ValidationMessage;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.hibernate.validator.constraints.UUID;

@Builder(toBuilder = true)
public record GetCartoonInput(
        @UUID(message = ValidationMessage.CARTOON_ID_UUID)
        @NotBlank(message = ValidationMessage.CARTOON_ID_NOT_BLANK)
        String id
) implements OperationInput {
}
