package acciones

class Trueno(nombre:String, costo: Int = 20) extends MagiaOscura(nombre, costo):
  override protected def aplicarEfecto(danioMagico: Int, objetivo: Target): Boolean =
    objetivo.recibirTrueno(danioMagico)


