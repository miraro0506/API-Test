Evaluacion NTT DATA

Tecnologías usadas
Java 17
Spring 3.4.5
Base de datos en memoria H2

1) Instrucciones para ejecutar el proyecto
Abrir proyecto ntt, en tu IDE preferido.
Asegúrate de tener el JDK 17 instalado y configurado en tu entorno.
Ejecuta la clase principal NttApplication(clic derecho sobre ella -> Run NttApplication.main()).

2)Base de datos H2
Ingresar por navegador prederido con la siguiente url
http://localhost:8080/h2-console

3) API RestFull NTT  gestión de usuarios.

*POST- Crear Usuario
URL: POST http://localhost:8080/user
Request Body:
{
  "nombre": "Maria Rodriguez",
  "correo": "marRodriguez@gmail.com",
  "contraseña": "ntt2025A",
  "telefonos": [
    {
      "numero": "12345678A",
      "codigoCiudad": "12",
      "codigoPais": "56"
    }
  ]
}
Notas:
El campo nombre y contraseña son obligatorios.
La contraseña debe tener al menos 8 caracteres, una mayúscula y números.
El correo debe tener un formato válido.
Si el correo ya está registrado, se devolverá un error.
Respuesta:
{
  "id": 4,
  "creado": "2025-05-01T12:59:32.737996",
  "modificado": null,
  "ultimoLogin": "2025-05-01T12:59:32.738033",
  "token": "JWT_GENERADO",
  "activo": false
}

* Get -Listar usuarios
URL: GET http://localhost:8080/user
Respuesta: Lista de Usuarios.

*Put - Modificar usuario
URL: PUT http://localhost:8080/user
Descripción: Modifica un usuario existente,requiere ID.
Request Body:
{
  "id": 4,
  "nombre": "David Jose",
  "correo": "davJ@gmail.com",
  "contraseña": "3Rwwrrrwergetgtw",
  "telefonos": [
    {
      "numero": "355525",
      "codigoCiudad": "65",
      "codigoPais": "56"
    }
  ]
}
Respuesta, retorna mismo formato que la creación de usuario.

*Patch- Actualización parcial
Descripción: Permite modificar parcialmente un usuario.
Request Body:
{
  "nombre": "Margarita Rod",
  "correo": "margari@gmail.com"
}

*Delete- Eliminar usuario
Descripción: Elimina el usuario con el ID especificado.
url: http://localhost:8080/user/2
Respuesta
{
  "mensaje": "Usuario eliminado correctamente"
}

4) Diagrama solución
Se encuentra en la carpeta de Diagrama