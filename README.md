# Tabla de resultados de pilotos de Fórmula 1
Esta aplicación JavaFX permite visualizar los resultados de los conductores de Fórmula 1 en un TableView. 
# Estructura 
Main.java:Clase principal que inicia la aplicación.

DriverResultsWindow.java:Clase que crea la ventana con el TableViewpara mostrar los resultados de los conductores.
Repositories/DriverResultRepository.java:Clase que maneja la conexión a la base de datos y las consultas de resultados de los conductores.
Repositories/SeasonRepository.java:Clase que maneja la conexión a la base de datos y las consultas de temporadas.
Models/DriverResult.java:Clase modelo para los resultados de los conductores.
Models/Season.java:Clase modelo para las temporadas.

# Configuración
Configurar las credenciales de la base de datos en la clase DriverResultRepository:

String jdbcURL = "jdbc:postgresql://localhost:5432/Formula1N";
String username = "postgres";
String password = "tu_password";

# Captura de la ejecución 
![image](https://github.com/MariaGisselie/Tabla-de-Conductores-/assets/169214799/ac562c53-4e71-4360-8ba9-299a5480146a)

![image](https://github.com/MariaGisselie/Tabla-de-Conductores-/assets/169214799/0ef90fe5-6d32-4cff-b0ef-bff4154813a2)

