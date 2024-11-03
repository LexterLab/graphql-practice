package com.test.graphql.utils;

public interface ValidationMessage {
    String CARTOON_NAME_NOT_BLANK = "Field name must be present";
    String CARTOON_NAME_LENGTH = "Field name must be between 2 and 100 characters";
    String CARTOON_ID_NOT_BLANK = "Field id must be present";
    String CARTOON_ID_UUID = "Field id must be uuid";
}
