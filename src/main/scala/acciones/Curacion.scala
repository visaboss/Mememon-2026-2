package cl.uchile.dcc
package acciones
class Curacion(nombre :String, costo:Int = 15) extends MagiaDeLuz(nombre, costo):
  override protected def aplicarEfecto(danioMagico: Int, objetivo: Target): Boolean =
    objetivo.recibirCuracion(danioMagico)

