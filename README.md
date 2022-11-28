# TP1-Algo3
1er TP de la materia Algoritmos y Programacion III de la Facultad de Ingeniería de la Universidad de Buenos Aires

## Integrantes
Agustín Conti y Luca Salluzzi 

## Descripción del Juego
>Un juego de cartas por turnos en las que las cartas no interactuan entre ellas sino que te dan mejoras o atacan directamente al jugador contrario. Tambien tenemos pensado desarollar "cartas trampa" que ante un evento en particular, se activen.

## Patrones a utilizar
### Strategy 
Este patrón lo utilizaremos para la creación e instanciación de cartas, ya que todas son parecidas en su core pero tienen caracterisicas que las diferencian y les dan individualidad.

### Observer
Para la confección de las cartas trampa pensamos utilizar el patron Observer; ya que estas deberan esperar hasta que algun evento en particular ocurra.

### Otros patrones: 
adicionalmente, pensamos que el patron MVC puede ser de gran utilidad a la hora de separar el codigo del propio juego de su interfaz gráfica.
