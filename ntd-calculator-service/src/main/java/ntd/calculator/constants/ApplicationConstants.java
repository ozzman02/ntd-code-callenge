package ntd.calculator.constants;

public class ApplicationConstants {

    public static final String USERNAME_EMAIL_VALIDATION_MSG =
            "must be a valid email address";

    public static final String USERNAME_NOT_EMPTY_VALIDATION_MSG =
            "must not be empty";

    public static final String USERNAME_SIZE_VALIDATION_MSG =
            "must have a valid length between 1 and 255 characters";

    public static final String PASSWORD_NOT_EMPTY_VALIDATION_MSG =
            "must not be empty";

    public static final String PASSWORD_SIZE_VALIDATION_MSG =
            "must have a valid length between 1 and 255 characters";

    public static final String USERNAME_NOT_FOUND_ERROR_MSG = "Username %s not found";

    public static final String ERRORS = "errors";

    public static final String ALL = "*";

    public static final String CORS_CONFIG_PATTERN = "/**";

    public static final String REACT_APP_ORIGIN = "http://localhost:3000";

    public static final String AUTH_URL = "/api/auth";

    public static final String CALCULATOR_URL = "/api/calculate";

    public static final Long MAX_AGE = 3600L;

}
