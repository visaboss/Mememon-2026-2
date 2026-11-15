package acciones

import unidades.{Personaje, Unidad}


//Cada objetivo concreto sobrescribe solo los efectos que sí puede recibir.
abstract class AbstractTarget extends Target:
  override def recibirAtaque(danio: Int): Boolean = false
  override def recibirMovimiento(unidad: Unidad): Boolean = false
  override def equiparseEn(personaje: Personaje): Boolean = false
  override def consumirsePor(personaje: Personaje): Boolean = false
  override def recibirTrueno(danioMagico: Int): Boolean = false
  override def recibirMeteorito(danioMagico: Int): Boolean = false
  override def recibirCuracion(danioMagico: Int): Boolean = false
  override def recibirPurificacion(danioMagico: Int): Boolean = false
