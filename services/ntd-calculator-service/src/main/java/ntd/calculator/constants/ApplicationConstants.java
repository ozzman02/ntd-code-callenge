package ntd.calculator.constants;

public class ApplicationConstants {

    public static final String BEARER_AUTHENTICATION = "Bearer Authentication";

    public static final String BEARER_SCHEME = "bearer";

    public static final String JWT_FORMAT = "JWT";

    public static final String BEARER = "Bearer ";

    public static final String ERROR = "error";

    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    public static final String[] IGNORED_REQUEST_MATCHERS = { "/swagger-ui.html/**",
            "/swagger-resources/**", "/swagger-ui/**", "/v3/api-docs/**" };

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

    public static final String SUCCESS_RECORD_CREATED_LOG_MSG =
            "Updated Balance is: {} - Success record will be created";

    public static final String INVALID_MATHEMATICAL_EXPRESSION_LOG_MSG =
            "Mathematical expression: {} is invalid. Invalid record will be created";

    public static final String MATHEMATICAL_EXPRESSION_NOT_SUPPORTED_LOG_MSG =
        "Mathematical expression: {} is not supported. Invalid record will be created";

    public static final String INSUFFICIENT_BALANCE_LOG_MSG =
            "Insufficient balance - Invalid record will be created";

    public static final String ALL = "*";

    public static final String CORS_CONFIG_PATTERN = "/**";

    public static final String REACT_APP_ORIGIN = "http://localhost:3000";

    public static final String AUTH_URL = "/api/auth";

    public static final String CALCULATOR_URL = "/api/calculate";

    public static final String GENERATOR_URL = "/api/generate";

    public static final String USER_RECORDS_URL = "/api/records";

    public static final String GET_RANDOM_NUMBERS_REQUEST =
            "https://www.random.org/integers/?num=15&min=1&max=1000&base=10&format=plain&col=1&rnd=new";

    public static final Long MAX_AGE = 3600L;

    public static final String[] ARITHMETIC_OPERATORS = new String[] { "+", "-", "*", "/" };

    public static final Integer PAGE_SIZE = 5;

    public static final String USER_SERVICE = "ntd-user-service";

    public static final String USER_SERVICE_URL = "/api/user";

    public static final String GET_USER_BY_USERNAME = "/getUserByUsername/{username}";

    public static final String GET_BY_USERNAME_AND_STATUS = "/getUserByUsername/{username}/{status}";

}
