package acciones

import unidades.{Personaje, Unidad}


trait Target:

  def recibirAtaque(danio: Int): Boolean


  def recibirMovimiento(unidad: Unidad): Boolean


  def equiparseEn(personaje: Personaje): Boolean


  def consumirsePor(personaje: Personaje): Boolean


  def recibirTrueno(danioMagico: Int): Boolean


  def recibirMeteorito(danioMagico: Int): Boolean


  def recibirCuracion(danioMagico: Int): Boolean


  def recibirPurificacion(danioMagico: Int): Boolean
