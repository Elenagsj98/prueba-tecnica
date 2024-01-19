# Android Loarte prueba tecnica App

## Ejercicio 1

- Escribe una breve memoria explicando el propósito del código que realiza la función
  combineOutputs.

El propósito que realiza la función combineOutputs es combinar dos resultados tipo "CustomResult", 
priorizando la información del priorityResult dependiendo de unas condiciones, con dos parámetros: 
uno que es priorityResult, es decir, que se considera de alta prioridad, y el secondaryResult que es de menor prioridad,
ambos de tipo "CustomResult". En cuanto a la lógica de la función, se evalúan diferentes casos con el when. 
En caso de que ambos sean de tipo "CustomResult.RSuccess", 
se realiza una verificación adicional para identificar que ambos tengan el mismo id. 
En el caso de que no tengan el mismo id, se devuelve el priorityResult; en el caso contrario, 
se combinarán sumando las cantidades y agregando a los proveedores a la lista. 
Por último, si no es del tipo "CustomResult.RSuccess", se devolverá un "CustomResult.RCriticalError".


- Responde en ella también a las siguientes preguntas:
  o ¿Por qué en el código se usa la operación .copy?
  o ¿Por qué en el código se usan las operaciones .apply?¿Qué ocurriría si se
  cambiasen por un .also?
  o ¿Por qué es necesario utilizar la operación toMutableList?

*¿Por qué en el código se usa la operación .copy?*

Debido a que el objeto "product" es un objeto inmutable, 
se crea una copia en la cual puedes modificar y hacer cambios sin que esto afecte al objeto original. 
Así, en este caso, se puede actualizar la cantidad y la lista de proveedores.

*¿Por qué en el código se usan las operaciones .apply?¿Qué ocurriría si se cambiasen por un .also?*

La operación .apply se utiliza en este caso para modificar el objeto "product" dentro del bloque sin tener que 
encadenar variables temporales. En el caso de que se cambiase por un .also, el resultado del bloque es el mismo objeto,
pero no afectaría al resultado; seguiría siendo el objeto original. 
Por lo tanto, .also no es tan útil en este caso, ya que no afectaría a "product" directamente, 
y lo que buscas es actualizarlo, para eso se utiliza el .apply. Con el .copy, 
devolverás la nueva instancia modificada para obtenerla como resultado del bloque (el objeto modificado).

*¿Por qué es necesario utilizar la operación toMutableList?*

Para convertir la lista "product.providers" en una lista mutable antes de realizar cualquier modificación en ella. 
Debido a que es una List, las listas en Kotlin son inmutables, lo cual no permite ninguna modificación. 
Al utilizar toMutableList(), se crea una copia mutable permitiendo modificaciones, 
en este caso, agregar los "secondaryResult.product.providers".

- Completa los tres tests unitarios que verifiquen la función dada.

- Escribe un código alternativo en combineOutputsSimplified que simplifique o mejore la
  función combineOutputs.

## Ejercicio 2

Dados los modelos Movie y Platform (que representan respectivamente una película y una
plataforma de streaming donde pueda encontrarse este contenido) y una lista de ambos donde
éstos se relacionan por el campo movieId, completa la función getMoviesInPlatforms para que
ésta construya un nuevo modelo partiendo de los datos proporcionados. La función debe
omitir aquellas películas que no sean encontradas en ninguna plataforma y proporcionar como
salida la lista pedida en un nuevo modelo no existente llamado MovieInPlatform.

- Completa el código utilizando el test existente en la clase QuestionTest2 como ejemplo de
  ejecución.
- Sobre el listado result obtenido, completa el código Assert para obtener el número de
  películas que pertenecen al servicio 2.

** He dejado una anotación en el test **