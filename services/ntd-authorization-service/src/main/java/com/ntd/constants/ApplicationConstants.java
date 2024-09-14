package com.ntd.constants;

public class ApplicationConstants {

    public static final String BEARER_AUTHENTICATION = "Bearer Authentication";

    public static final String BEARER = "bearer";

    public static final String JWT = "JWT";

    public static final String ERROR = "error";

    public static final String AUTH_URL = "/api/auth";

    public static final String AUTH_URL_MATCHERS = "/api/auth/**";

    public static final String LOGIN = "/login";

    public static final String REGISTER = "/register";

    public static final String USER_SERVICE = "ntd-user-service";

    public static final String USER_SERVICE_URL = "/api/user";

    public static final String SAVE_USER_URL = "/save";

    public static final String GET_USER_BY_USERNAME_URL = "/{username}";

    public static final String[] IGNORED_REQUEST_MATCHERS = { "/v1/auth/**", "/swagger-resources/**",
            "/swagger-ui.html/**", "/swagger-resources/**", "/swagger-ui/**", "/v3/api-docs/**" };

    public static final String CORS_CONFIG_PATTERN = "/**";

    public static final String REACT_APP_ORIGIN = "http://localhost:3000";

    public static final String ALL = "*";

    public static final Long MAX_AGE = 3600L;
}
