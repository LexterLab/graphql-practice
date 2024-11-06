package com.test.graphql.controller;

import com.test.graphql.error.ErrorWrapper;
import com.test.graphql.operation.cartoon.add.AddCartoon;
import com.test.graphql.operation.cartoon.add.AddCartoonInput;
import com.test.graphql.operation.cartoon.add.AddCartoonOutput;
import com.test.graphql.operation.cartoon.get.GetCartoon;
import com.test.graphql.operation.cartoon.get.GetCartoonInput;
import com.test.graphql.operation.cartoon.get.GetCartoonOutput;
import com.test.graphql.operation.cartoon.update.UpdateCartoon;
import com.test.graphql.operation.cartoon.update.UpdateCartoonInput;
import com.test.graphql.operation.cartoon.update.UpdateCartoonOutput;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CartoonController extends BaseController {
    private final AddCartoon addCartoon;
    private final UpdateCartoon updateCartoon;
    private final GetCartoon getCartoon;

    @MutationMapping
    public Object createCartoon(@Argument AddCartoonInput input) {
        Either<ErrorWrapper, AddCartoonOutput> output = addCartoon.process(input);
        log.info("output: {}", output);
        return handleOutput(output);
    }

    @MutationMapping
    public Object updateCartoon(@Argument UpdateCartoonInput input) {
        Either<ErrorWrapper, UpdateCartoonOutput> output = updateCartoon.process(input);
        log.info("output: {}", output);
        return handleOutput(output);
    }

    @QueryMapping
    public Object getCartoon(@Argument String cartoonId) {
        GetCartoonInput input = GetCartoonInput.builder()
                .id(cartoonId)
                .build();
        Either<ErrorWrapper, GetCartoonOutput> output = getCartoon.process(input);
        log.info("output: {}", output);
        return handleOutput(output);
    }


}
