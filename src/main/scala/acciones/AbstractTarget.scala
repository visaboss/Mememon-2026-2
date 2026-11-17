package cl.uchile.dcc
package acciones
import unidades.{Personaje, Unidad}
import excepciones.InvalidTargetException

/**
 * Por defecto, ningun efecto se aplica y se lanza una excepcion
 * */

//Cada objetivo concreto sobrescribe solo los efectos que sí puede recibir.

abstract class  AbstractTarget extends Target:

  override def recibirAtaque(danio: Int): Boolean =
    throw new InvalidTargetException("Objetivo no puede recibir ataque")

  override def recibirMovimiento(unidad: Unidad): Boolean =
    throw new InvalidTargetException(s"${unidad.nombre} solo puede moverse un panel")

  override def equiparseEn(personaje: Personaje): Boolean =
    throw new InvalidTargetException(s"${personaje.nombre} solo puede equiparse armas")

  override def consumirsePor(personaje: Personaje): Boolean =
    throw new InvalidTargetException(s"${personaje.nombre} solo consume pociones")

  override def recibirTrueno(danioMagico: Int): Boolean =
    throw new InvalidTargetException("Trueno solo lanzable sobre unidad")

  override def recibirMeteorito(danioMagico: Int): Boolean =
    throw new InvalidTargetException("Meteorito lanzable solo sobre panel")

  override def recibirCuracion(danioMagico: Int): Boolean =
    throw new InvalidTargetException("Curacion recibida solo por personajes")

  override def recibirPurificacion(danioMagico: Int): Boolean =
    throw new InvalidTargetException("Purificacion solo recibida por enemigos")
