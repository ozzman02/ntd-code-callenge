package com.ntd.constants;

public class ApplicationConstants {

    public static final String SECRET = "9999999B59703373367639792F423F";

    public static final String[] API_ENDPOINTS = { "/api/auth/login", "/eureka" };

    public static final String BEARER = "Bearer ";

    public static final String AUTHORIZATION_SERVICE = "ntd-authorization-service";

    public static final String USER_SERVICE = "ntd-user-service";

    public static final String CALCULATOR_SERVICE = "ntd-calculator-service";

    public static final String AUTHORIZATION_SERVICE_ROUTE_PATH = "/api/auth/**";

    public static final String USER_SERVICE_ROUTE_PATH = "/api/user/**";

    public static final String CALCULATOR_SERVICE_ROUTE_PATH = "/api/calculator/**";

    //public static final String AUTHORIZATION_SERVICE_URI = "lb://ntd-authorization-service";
    public static final String AUTHORIZATION_SERVICE_URI = "http://localhost:8081";

    //public static final String USER_SERVICE_URI = "lb://ntd-user-service";
    public static final String USER_SERVICE_URI = "http://localhost:8082";

    //public static final String CALCULATOR_SERVICE_URI = "lb://ntd-calculator-service";
    public static final String CALCULATOR_SERVICE_URI = "http://localhost:8083";

}
