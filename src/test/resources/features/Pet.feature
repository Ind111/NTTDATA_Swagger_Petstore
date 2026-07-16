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


    @CP_02Pet @Get_Pet @Read_Pet
      Scenario: Consultar mascota por id
      Given el actor establece el endpoint de petStore
      And el actor crea una mascota con el "Milanesa" "Gato" "https://example.com/photoMilanesa.jpg" "vendido"
      When el actor consulta la mascota por su id
      Then el codigo de respuesta debe ser 200

    @CP_03Pet @Put_Pet @Update_Pet
      Scenario Outline: Actualizar mascota
      Given el actor establece el endpoint de petStore
      And el actor crea una mascota con el "Tobias" "Cuy" "https://example.com/tobias.jpg" "frito"
      When el actor actualiza la mascota con "<name>" "<category>" "<photoUrl>" "<status>"
      Then  el codigo de respuesta debe ser 200
      Examples:
        | name | category | photoUrl | status |
        | Cirilo  | cabra | https://example.com/cabra.jpg | vendido|

    @CP_04Pet @Delete_Pet
      Scenario: Eliminar mascota
      Given el actor establece el endpoint de petStore
      And el actor crea una mascota con el "Mathilda" "Vaca" "https://example.com/vaca.jpg" "vendida"
      When el actor elimina a la mascota
      Then el codigo de respuesta debe ser 200

