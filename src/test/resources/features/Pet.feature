@PetAll
Feature: APIS from Pet

  Como usuario de PetStore
  Quiero crear, leer, actualizar y eliminar mascotas
  Para gestionar el inventario de la tienda

  @CP_01Pet @Post_Pet @Create_Pet
   Scenario Outline: Crear una mascota
    Given el actor establece el endpoint de petStore
    When el actor crea una mascota con el "<name>" "<category>" "<photoUrl>" "<status>"
    Then el codigo de respuesta debe ser 200

    Examples:
    | name  | category  | photoUrl  | status  |
    | Chiqui | Perro      | https://example.com/photo.jpg | disponible |