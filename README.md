# Petstore Automation - Serenity BDD + Screenplay

## Objetivo
Automatización de pruebas API para el flujo CRUD de Swagger Petstore 
(Create, Read, Update, Delete)

## Tecnologías
- Java 17
- Serenity BDD 3.1.20
- Cucumber (Gherkin)
- RestAssured
- Maven

## Estructura del proyecto
- `Tasks/` → acciones que el actor realiza (PostPet, GetPet, PutPet, DeletePet)
- `StepDefinitions/` → conexión entre Gherkin y las Tasks
- `features/` → escenarios en Gherkin
- `Questions/ResponseCode` → verificación del código de respuesta

## Diseño de datos
- IDs generados dinámicamente (Random)
- Cada escenario crea su propia mascota.
