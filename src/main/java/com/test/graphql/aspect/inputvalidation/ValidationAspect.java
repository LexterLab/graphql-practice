package com.test.graphql.aspect.inputvalidation;

import com.test.graphql.error.Error;
import com.test.graphql.exception.InputValidationException;
import com.test.graphql.operation.base.OperationInput;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class ValidationAspect {
    private final Validator validator;

    @Around("@annotation(com.test.graphql.validator.input.ValidateInput)")
    public Object validateInput(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();

        if (args.length == 1 && args[0] instanceof OperationInput input) {
            Set<ConstraintViolation<OperationInput>> violations = validator.validate(input);
            if (!violations.isEmpty()) {
                List<Error> errors = mapConstraintViolations(violations);
                throw new InputValidationException(errors);
            }
        }


        return joinPoint.proceed();
    }

    private List<Error> mapConstraintViolations(Set<ConstraintViolation<OperationInput>> violations) {
        log.info("violations {}", violations);
        return violations.stream()
                .map(violation -> Error.builder()
                        .message(violation.getMessage())
                        .field(violation.getPropertyPath().toString())
                        .build())
                .collect(Collectors.toList());
    }
}
