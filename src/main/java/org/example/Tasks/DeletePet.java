package org.example.Tasks;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DeletePet implements Task {
    private final String petId;

    public DeletePet() {
        this.petId = OnStage.theActorInTheSpotlight().recall("petId");
    }

    public static Performable delete(){
        return instrumented(DeletePet.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from("/pet/" + petId)
                        .with(requestSpecification -> requestSpecification
                                .header("Accept", "application/json")
                                .log().all())
        );

        SerenityRest.lastResponse().body().prettyPrint();
        System.out.println("ELIMINADO - PET ID: " + petId);

    }
}
