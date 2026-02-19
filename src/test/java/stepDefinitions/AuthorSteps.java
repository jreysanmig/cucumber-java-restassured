package stepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class AuthorSteps {

    static Response response;

    @Given("I call the Open Library author API")
    public void i_call_the_open_library_author_api() {
        response =
            given().
            when().
                get("https://openlibrary.org/authors/OL1A.json");
    }
    @Then("the response status should be {int}")
    public void the_response_status_should_be(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }
    @Then("the personal_name should be {string}")
    public void the_personal_name_should_be(String personalName) {
        response.then().body("personal_name", equalTo(personalName));
    }

    @Then("the alternate_names should include {string}")
    public void the_alternate_names_should_include(String alternateName) {
        response.then().body("alternate_names", hasItem(alternateName));
    }
}
