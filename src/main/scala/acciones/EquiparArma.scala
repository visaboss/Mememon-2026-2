package cl.uchile.dcc
package acciones
import unidades.Personaje
import utilizables.Utilizable

class EquiparArma(nombre:String,PosibleObjects : List[Utilizable]) extends AccionConUtilizables(nombre, PosibleObjects):
  override def ejecutadaPorPersonaje(fuente: Personaje, objetivo: Target): Boolean =
    PosibleObjects.contains(objetivo) && objetivo.equiparseEn(fuente)