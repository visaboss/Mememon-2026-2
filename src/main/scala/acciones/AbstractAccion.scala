package acciones

import unidades.{Enemigo, MagoBlanco, MagoNegro, Personaje}


abstract class AbstractAccion(override val nombre: String) extends Accion:
  override def ejecutadaPorPersonaje(fuente: Personaje, objetivo: Target): Boolean = false

  override def ejecutadaPorMagoNegro(fuente: MagoNegro, objetivo: Target): Boolean =
    ejecutadaPorPersonaje(fuente, objetivo)

  override def ejecutadaPorMagoBlanco(fuente: MagoBlanco, objetivo: Target): Boolean =
    ejecutadaPorPersonaje(fuente, objetivo)

  override def ejecutadaPorEnemigo(fuente: Enemigo, objetivo: Target): Boolean = false
