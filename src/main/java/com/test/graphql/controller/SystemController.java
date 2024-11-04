package com.test.graphql.controller;

import com.test.graphql.error.ErrorWrapper;
import com.test.graphql.operation.cartoon.get.GetCartoon;
import com.test.graphql.operation.cartoon.get.GetCartoonInput;
import com.test.graphql.operation.cartoon.get.GetCartoonOutput;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SystemController extends BaseController {
    private final GetCartoon getCartoon;


    @GetMapping("api/v1/cartoons/{cartoonId}")
    public ResponseEntity<?> getCartoons(@PathVariable String cartoonId) {
        GetCartoonInput input = GetCartoonInput.builder()
                .id(cartoonId)
                .build();
        Either<ErrorWrapper, GetCartoonOutput> output = getCartoon.process(input);
        return handleOutput(output, HttpStatus.OK);
    }

}
