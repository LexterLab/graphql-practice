package com.test.graphql.operation.cartoon.add;

import com.test.graphql.operation.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

import static com.test.graphql.utils.ValidationMessage.CARTOON_NAME_LENGTH;
import static com.test.graphql.utils.ValidationMessage.CARTOON_NAME_NOT_BLANK;

@Builder
public record AddCartoonInput(
        @NotBlank(message = CARTOON_NAME_NOT_BLANK)
        @Length(min = 2, max = 100, message = CARTOON_NAME_LENGTH)
        String name

) implements OperationInput {
}
