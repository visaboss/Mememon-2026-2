package cl.uchile.dcc
package acciones
import unidades.{Enemigo, Personaje}


class Moverse(override val nombre: String) extends AbstractAccion(nombre):
  override def ejecutadaPorPersonaje(fuente: Personaje, objetivo: Target): Boolean =
    objetivo.recibirMovimiento(fuente)

  override def ejecutadaPorEnemigo(fuente: Enemigo, objetivo: Target): Boolean =
    objetivo.recibirMovimiento(fuente)