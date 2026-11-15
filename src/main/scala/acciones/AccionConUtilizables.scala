package cl.uchile.dcc
package acciones
import utilizables.Utilizable

abstract class AccionConUtilizables(nombre: String,val posibleObjects : List[Utilizable]) extends AbstractAccion(nombre)