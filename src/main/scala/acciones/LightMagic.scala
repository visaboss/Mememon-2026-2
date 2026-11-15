package cl.uchile.dcc
package acciones
import acciones.Target
import unidades.MagoBlanco

abstract class MagiaDeLuz(nombre:String, costo:Int) extends Hechizo(nombre, costo):
  override def ejecutadaPorMagoBlanco(fuente: MagoBlanco, objetivo: Target): Boolean =
    lanzar(fuente, objetivo)
