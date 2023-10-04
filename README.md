# Mobile Scaffolding 🧉

Este repositorio se presenta como una referencia para organizar un proyecto de Android. La elección de packages está
basada en las recomendaciones de android [1].

Existen packages principales que respetan esa arquitectura: Data, Domain y UI. Dentro de cada package encontrarás un
readme.md que explica un poco el rol de cada uno.

- Data: https://github.com/unlam-tec-movil/scaffolding/tree/main/app/src/main/java/ar/edu/unlam/mobile/scaffold/data
- Domain: https://github.com/unlam-tec-movil/scaffolding/tree/main/app/src/main/java/ar/edu/unlam/mobile/scaffold/domain
- UI: https://github.com/unlam-tec-movil/scaffolding/tree/main/app/src/main/java/ar/edu/unlam/mobile/scaffold/ui

Hay otro package, core, que contiene elementos que son comunes a todos los packages.

## The Cat API

Como ejemplo para esta aplicación consumimos una API de gatos: https://thecatapi.com/ Esta API nos permite obtener la
foto de un gato al azar. Por esto es que verás al modelo Kitty como nuestro modelo de negocio principal. Los compomentes
renderizarán la información recibida por dicha API.

Invitamos a recorrer el proyecto, a familizarizarse con la estructura y a leer los readme.md de cada package.


[1]: https://developer.android.com/topic/architecture#recommended-app-arch
