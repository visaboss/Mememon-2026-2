package cl.uchile.dcc
package acciones
import unidades.{Enemigo, MagoBlanco, MagoNegro, Personaje}
import excepciones.InvalidActionException

abstract class AbstractAccion(override val nombre: String) extends Accion:
  override def ejecutadaPorPersonaje(fuente: Personaje, objetivo: Target): Boolean =
    throw new InvalidActionException(s"${fuente.nombre} no puede realizar la acción $nombre")

  override def ejecutadaPorMagoNegro(fuente: MagoNegro, objetivo: Target): Boolean =
    ejecutadaPorPersonaje(fuente, objetivo)

  override def ejecutadaPorMagoBlanco(fuente: MagoBlanco, objetivo: Target): Boolean =
    ejecutadaPorPersonaje(fuente, objetivo)

  override def ejecutadaPorEnemigo(fuente: Enemigo, objetivo: Target): Boolean =
    throw new InvalidActionException(s"${fuente.nombre} no puede realizar la acción $nombre")
