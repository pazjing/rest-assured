package com.api.rest.api.restassuredhelper;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.Matchers.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jingbai on 6/29/17.
 */
public class TestGet extends BaseClass {

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

    @Test
    public void testStatusCode() throws URISyntaxException {

        /*
         * Given Accept the response in JSON format
         * When I perform the GET request
         * THen Status code 200 OK should be returned
         */

        /*int code = given()
                .accept(ContentType.JSON)
                .when()
                .get(new URI("http://localhost:8091/maps/api/directions/lotto"))
                .thenReturn()
                .statusCode();
        System.out.println(code);
        Assert.assertEquals(HttpStatus.SC_OK,code); */

        given()
                .accept(ContentType.JSON)
                .when()
                .get(new URI("http://localhost:8091/maps/api/directions/lotto/all"))
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

        // Capture the status code or any other info , use the : thenReturn()
        // Validate the response , use the :  then()

    }

    @Test
    public void testGetWithId() throws URISyntaxException {
        /*
         * Given Accept the content in JSON format
         * when I perform the GET request with Id 201
         * Then status code 201 OK should be returned
         */
        /*
        given()
                .accept(ContentType.JSON)
                .when()
                .get(new URI("http://localhost:8091/maps/api/directions/lotto/201"))
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED);
         */
        String body = given()
                .accept(ContentType.JSON)
                .when()
                .get(new URI("/201"))
                .thenReturn()
                .body()
                .asString();

        System.out.println(body);

    }

    @Test
    public void testGetWithNonExistId() throws URISyntaxException {
        /*
         * Given Accept the content in JSON format
         * when I perform the GET request with Id 404
         * Then status code 404 NOT FOUND should be returned
         */
        System.out.println(baseURI + port + basePath);
        given()
                .accept(ContentType.JSON)
                .when()
                .get(new URI("/404"))   //after extend the BaseClass, baseURI, port and basePath are not needed.
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND);

    }

    @Test
    public void testGetWithIdHeader() throws URISyntaxException {
        /*
         * Given Accept the content in JSON format
         * when I perform the GET request with Id 201
         * Then status code 201 OK should be returned
         */
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Accept", "application/json");

        String body = given()
                .headers(headers)
                .when()
                .get(new URI("/201"))
                .thenReturn()
                .body()
                .asString();

        System.out.println(body);

    }

    @Test
    public void testContent() throws URISyntaxException {
        /*
         * Given Accept the content in JSON format
         * when I perform the GET request with Id 201
         * Then the response should have BrandName as Dell
         */
        given()
                .accept(ContentType.JSON)
                .when()
                .get(new URI("/201"))
                .then()
                .body("BrandName",containsString("Dell"),
                        "Id", equalTo(203),"LaptopName",equalToIgnoringCase("latitude"));

        given()
                .accept(ContentType.JSON)
                .when()
                .get(new URI("/201"))
                .then()
                .body("Features.Hardware",hasItems("8GB RAM","1TB Hard Drive"),"Features.Hardware",hasSize(2));

    }

    @Test
    public void testContentJsonPath() throws URISyntaxException {
        /*
         * Given Accept the content in JSON format
         * when I perform the GET request with Id 201
         * Then the response should have BrandName as Dell
         */
        String s = given()
                .accept(ContentType.JSON)
                .when()
                .get(new URI("/201"))
                .thenReturn()
                .asString();

        System.out.println(s);

        JsonPath json = new JsonPath(s);

        Assert.assertEquals(203, json.getInt("Id"));
        Assert.assertEquals("Dell", json.getString("BrandName"));
        Assert.assertEquals("latitude", json.getString("LaptopName"));
        Assert.assertTrue(json.getList("Features.Hardware").contains("1TB Hard Drive"));


    }

}
