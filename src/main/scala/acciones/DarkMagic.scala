package acciones
import acciones.Target
import unidades.MagoNegro

abstract class MagiaOscura(nombre: String, costo:Int) extends Hechizo(nombre, costo):
  override def ejecutadaPorMagoNegro(fuente: MagoNegro, objetivo: Target): Boolean =
    lanzar(fuente, objetivo)
