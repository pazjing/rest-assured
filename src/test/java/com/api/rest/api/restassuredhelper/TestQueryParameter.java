package com.api.rest.api.restassuredhelper;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Created by jingbai on 7/4/17.
 */
public class TestQueryParameter extends BaseClass {

    @Test
    public void testQueryPra() {

        /**
         * Given Accept the content in JSON format
         * And ID as 75
         * And Laptop Name "latitude"
         * When I perform the GET request
         * Then Status code 200 OK should be returned
         * And the response content should have Id as 75
         * ANd Hardware list should contain 8GB of SSD
         *
         */

        given()
                .accept(ContentType.JSON)
                .param("id","75")
                .param("laptopName","latitude")
                .when()
                .get("/query")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .assertThat()
                .body("Features.Hardware",hasItem("8GB SSD RAM"));

        //System.out.println(s);

    }
}
