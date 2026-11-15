package acciones
import unidades.Personajes
import utilizables.Utilizable

class EquiparArma(nombre:String,PosibleObjects : List[Utilizable]) extends AccionConUtilizables(nombre, PosibleObjects):
  override def ejecutadaPorPersonaje(fuente: Personajes, objetivo: Target): Boolean =
    PosibleObjects.contains(objetivo) && objetivo.equiparseEn(fuente)