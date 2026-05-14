# Arkano - Rick and Morty Challenge

Este proyecto es una aplicación de Android que muestra los personajes de Rick and Morty usando su API pública
## Tecnologías usadas
- **Kotlin + Jetpack Compose**: Para toda la interfaz de usuario.
- **MVVM + Repository Pattern**: Estructura limpia que separa la UI de los datos.
- **Retrofit + OkHttp**: Para las llamadas a la red.
- **Glide (Compose)**: Manejo eficiente de imágenes con caché.
- **Hilt (DI)**: Inyección de dependencias para que el código sea fácil de probar y mantener.
- **StateFlow + Sealed Classes**: Para controlar los estados de la pantalla (Cargando, Éxito, Error).

## Decisiones técnicas
- Usé **MVVM** porque separa muy bien la lógica del diseño, haciendo que el código sea más ordenado.
- El **Repository Pattern** sirve para que el ViewModel no sepa de dónde vienen los datos, solo los pida.
- **Hilt** facilita mucho la vida con la inyección de dependencias, integrándose directo al ciclo de vida de Android.
- Elegí **Glide** porque es muy sólido manejando caché, lo que evita que las imágenes se vuelvan a bajar al hacer scroll.
- El estado de la pantalla se maneja con **sealed classes**, así la UI sabe exactamente qué mostrar en cada momento.
- En la lista (`LazyColumn`), usé `key = { it.id }` para que Compose sea más eficiente y no redibuje cosas que no cambiaron.

## Cómo funciona el estado
La pantalla tiene tres momentos claros:
1. **Cargando (Loading)**: Aparece un spinner en el centro mientras bajan los datos.
2. **Éxito (Success)**: Se muestra la lista de personajes con su foto, nombre y estado.
3. **Error**: Si algo sale mal, sale un mensaje avisando qué pasó.

## Lo que faltó por tiempo
- **Paginación**: La API tiene muchísimos personajes (42 páginas) y por ahora solo traigo la primera tanda.
- **Búsqueda**: Estaría bueno poder buscar personajes por nombre o filtrar por estado.
- **Pantalla de detalle**: Por ahora no se puede entrar a ver más info de cada personaje al tocarlo.

## Qué se podría mejorar
- Meter **Paging 3** para manejar el scroll infinito de forma nativa.
- Filtros por estado (Vivo, Muerto, Desconocido)
- Agregar **Room** para que la app funcione sin internet (offline-firts)

