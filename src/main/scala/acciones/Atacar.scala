package cl.uchile.dcc
package acciones
import unidades.{Enemigo, Personaje}

class Atacar(override val nombre:String) extends AbstractAccion(nombre):
  override def ejecutadaPorPersonaje(fuente: Personaje, objetivo: Target): Boolean =
    if fuente.slotArma.isDefined then objetivo.recibirAtaque(fuente.slotArma.get.attackPoints)
    else false

  override def ejecutadaPorEnemigo(fuente: Enemigo, objetivo: Target): Boolean =
    objetivo.recibirAtaque(fuente.attackPoints)
