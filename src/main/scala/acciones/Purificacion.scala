package acciones

class Purificacion(nombre:String, costo:Int=25) extends MagiaDeLuz(nombre, costo):
  override protected def aplicarEfecto(danioMagico: Int, objetivo: Target): Boolean =
    objetivo.recibirPurificacion(danioMagico)