package org.example.Tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.util.Random;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class PostPet implements Task {
    private final String name, category, photoUrl, status;
    private final int petId;

    public PostPet(String name, String category, String photoUrl, String status) {
        this.name = name;
        this.category = category;
        this.photoUrl = photoUrl;
        this.status = status;
        this.petId = new Random().nextInt(100);
    }


    public static Performable fromPage(String name, String category, String photoUrl, String status) {
        return instrumented(PostPet.class, name,category,photoUrl,status);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("/pet")
                        .with(requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .header("Accept","application/json")
                                .body("""
                                    {
                                    "id": %s,
                                    "category": {
                                    "id": 1,
                                    "name": "%s"
                                    },
                                    "name": "%s",
                                    "photoUrls": ["%s"],
                                    "status": "%s"
                                    }""".formatted(petId,category,name,photoUrl,status)
                                ).log().all())
        );

        SerenityRest.lastResponse().body().prettyPrint();
        if(SerenityRest.lastResponse().statusCode()==200){
            OnStage.theActorInTheSpotlight().remember("petId", SerenityRest.lastResponse().path("id").toString());
            String valorPetId = actor.recall("petId");
            System.out.println("PET ID:" + valorPetId);
        }

    }
}
