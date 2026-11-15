package acciones
import unidades.Personajes
import utilizables.Utilizable

class ConsumirPocion(nombre: String, PosibleObjects : List[Utilizable]) extends AccionConUtilizables(nombre, PosibleObjects):
  override def ejecutadaPorPersonaje(fuente: Personajes, objetivo: Target): Boolean =
    PosibleObjects.contains(objetivo) && objetivo.consumirsePor(fuente)