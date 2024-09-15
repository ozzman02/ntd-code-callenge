package com.ntd.constants;

public class ApplicationConstants {

    public static final String BEARER_AUTHENTICATION = "Bearer Authentication";

    public static final String BEARER_SCHEME = "bearer";

    public static final String JWT_FORMAT = "JWT";

    public static final String BEARER = "Bearer ";

    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    public static final String ISSUER = "NTD SOFTWARE";

    public static final String[] IGNORED_REQUEST_MATCHERS = { "/swagger-ui.html/**",
            "/swagger-resources/**", "/swagger-ui/**", "/v3/api-docs/**" };

    public static final String CORS_CONFIG_PATTERN = "/**";

    public static final String REACT_APP_ORIGIN = "http://localhost:3000";

    public static final String ALL = "*";

    public static final Long MAX_AGE = 3600L;

    public static final String USER_SERVICE_URL = "/api/user";

    public static final String GET_BY_USERNAME = "/getUserByUsername/{username}";

    public static final String GET_BY_USERNAME_AND_STATUS = "/getUserByUsername/{username}/{status}";

}
