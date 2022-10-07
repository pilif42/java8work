package com.philippe.app.message;

import com.philippe.app.domain.LoadFullSupply;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Component
public class LoadFullSupplyValidator implements Validator {

    private static final String ERROR_CODE_EMPTY = "empty";
    private static final String ERROR_CODE_FUTURE = "future";
    private static final String ERROR_CODE_OUT_OF_DATE = "out-of-date";

    @Override
    public boolean supports(Class<?> clazz) {
        return LoadFullSupply.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fileLocationGB", ERROR_CODE_EMPTY, "fileLocationGB is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fileLocationISL", ERROR_CODE_EMPTY, "fileLocationISL is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "source", ERROR_CODE_EMPTY, "A source environment must be specified");
        ValidationUtils.rejectIfEmpty(errors, "extractionDate", ERROR_CODE_EMPTY, "An extraction date must be specified as YYYY-MM-DD");

        LoadFullSupply supply = (LoadFullSupply) target;
        LocalDate extractionDate = supply.getExtractionDate();
        if (extractionDate != null) {
            if (extractionDate.isAfter(LocalDate.now())) {
                errors.rejectValue("extractionDate", ERROR_CODE_FUTURE, "extractionDate must not be in the future");
            }
            if (extractionDate.isBefore(LocalDate.now().minusMonths(3))) {
                errors.rejectValue("extractionDate", ERROR_CODE_OUT_OF_DATE, "extractionDate must not be older than 3 months");
            }
        }
    }
}
