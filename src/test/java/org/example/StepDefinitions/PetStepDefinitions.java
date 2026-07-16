package org.example.StepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.example.Questions.ResponseCode;
import org.example.Tasks.GetPet;
import org.example.Tasks.PostPet;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.CoreMatchers.equalTo;

public class PetStepDefinitions {

    @Before
    public void setTheStage(){
        OnStage.setTheStage(new OnlineCast());
    }
    @Given("el {actor} establece el endpoint de petStore")
    public void elActorEstableceElEndpointDePetStore(Actor actor) {
        actor.whoCan(CallAnApi.at("https://petstore.swagger.io/v2"));

    }

    @When("el actor crea una mascota con el {string} {string} {string} {string}")
    public void elActorCreaUnaMascotaConEl(String name, String category, String photoUrl, String status) {
        theActorInTheSpotlight().attemptsTo(PostPet.fromPage(name,category,photoUrl,status));
    }

    @Then("el codigo de respuesta debe ser {int}")
    public void elCodigoDeRespuestaDebeSer(int responseCode) {
        theActorInTheSpotlight().should(seeThat("El código de respuesta", ResponseCode.getStatus(), equalTo(responseCode)));
    }

    @When("el actor consulta la mascota por su id")
    public void elActorConsultaLaMascotaPorSuId() {
        theActorInTheSpotlight().attemptsTo(GetPet.fromPage());
    }
}
