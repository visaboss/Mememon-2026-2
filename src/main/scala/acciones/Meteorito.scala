package cl.uchile.dcc
package acciones
class Meteorito(nombre: String, costo:Int=50) extends MagiaOscura(nombre, costo):
  override protected def aplicarEfecto(danioMagico: Int, objetivo: Target): Boolean =
    objetivo.recibirMeteorito(danioMagico)