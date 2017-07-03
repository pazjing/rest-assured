package com.api.rest.api.restassuredhelper;

import org.junit.BeforeClass;

import static io.restassured.RestAssured.*;

/**
 * Created by jingbai on 6/30/17.
 */
public class BaseClass {

    @BeforeClass
    public static void setUp() {

        baseURI = "http://localhost";
        port = 8091;
        basePath = "/maps/api/directions/lotto";

    }
}
