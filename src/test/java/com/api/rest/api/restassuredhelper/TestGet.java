package com.api.rest.api.restassuredhelper;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by jingbai on 6/29/17.
 */
public class TestGet {

    @Test
    public  void testGet() throws URISyntaxException {

        /*
         * When I perform the GET request
         */

        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get(new URI("http://localhost:8091/maps/api/directions/lotto"));
        //URI uri = new URI("http://localhost:8091/maps/api/directions/lotto");
        //Response response = when().get(new URI("http://localhost:8091/maps/api/directions/lotto"));
        System.out.println(response.asString());
    }
}
