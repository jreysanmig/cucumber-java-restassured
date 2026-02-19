package stepDefinitions;

import context.TestContext;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import io.restassured.builder.RequestSpecBuilder;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AuthorSteps {

    private Scenario scenario;
    private final TestContext context;

    public AuthorSteps(TestContext context) {
        this.context = context;
    }

    @Before
    public void beforeHook(Scenario scenario) {
        this.scenario = scenario;
        this.context.requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://openlibrary.org/authors/OL1A.json")
                .build();
    }

    @Given("I call the Open Library author API")
    public void i_call_the_open_library_author_api() {
        context.response =
                given(context.requestSpec).
                when().get();
        context.requestSpec.log().all();
    }
    @Then("the response status should be {int}")
    public void the_response_status_should_be(int statusCode) {
        context.response.then().assertThat().statusCode(statusCode);
        scenario.attach(context.response.body().asPrettyString(), "application/json", "Response");
    }
    @Then("the personal_name should be {string}")
    public void the_personal_name_should_be(String personalName) {
        context.response.then().body("personal_name", equalTo(personalName));
    }

    @Then("the alternate_names should include {string}")
    public void the_alternate_names_should_include(String alternateName) {
        context.response.then().body("alternate_names", hasItem(alternateName));
    }
}
