package com.test.graphql.controller;

import com.test.graphql.error.ErrorWrapper;
import com.test.graphql.operation.cartoon.add.AddCartoon;
import com.test.graphql.operation.cartoon.add.AddCartoonInput;
import com.test.graphql.operation.cartoon.add.AddCartoonOutput;
import com.test.graphql.operation.cartoon.update.UpdateCartoon;
import com.test.graphql.operation.cartoon.update.UpdateCartoonInput;
import com.test.graphql.operation.cartoon.update.UpdateCartoonOutput;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CartoonController extends BaseController {
    private final AddCartoon addCartoon;
    private final UpdateCartoon updateCartoon;


    @MutationMapping
    public Object createCartoon(@Argument AddCartoonInput input) {
        Either<ErrorWrapper, AddCartoonOutput> output = addCartoon.process(input);
        log.info("output: {}", output);
        var handled = handleOutput(output);
        log.info("handled: {}", handled);
        return handled;
    }

    @MutationMapping
    public Object updateCartoon(@Argument UpdateCartoonInput input) {
        Either<ErrorWrapper, UpdateCartoonOutput> output = updateCartoon.process(input);
        log.info("output: {}", output);
        var handled = handleOutput(output);
        log.info("handled: {}", handled);
        return handled;
    }


}
