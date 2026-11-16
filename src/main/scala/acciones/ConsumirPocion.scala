package cl.uchile.dcc
package acciones
import unidades.Personaje
import utilizables.Utilizable

import excepciones.InvalidActionException

class ConsumirPocion(nombre: String, objetos : List[Utilizable]) extends AccionConUtilizables(nombre, objetos):
  override def ejecutadaPorPersonaje(fuente: Personaje, objetivo: Target): Boolean =
    if !posibleObjects.contains(objetivo) then
      throw new InvalidActionException(s"${fuente.nombre} no tiene este objeto en inventario ")
    objetivo.consumirsePor(fuente)