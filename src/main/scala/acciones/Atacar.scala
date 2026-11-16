package cl.uchile.dcc
package acciones
import unidades.{Enemigo, Personaje}
import excepciones.missingWeaponException


class Atacar(override val nombre:String) extends AbstractAccion(nombre):
  override def ejecutadaPorPersonaje(fuente: Personaje, objetivo: Target): Boolean =
    if fuente.slotArma.isEmpty then
      throw new missingWeaponException(s"${fuente.nombre} necesita un arma para atacar")
    objetivo.recibirAtaque(fuente.slotArma.get.attackPoints)


  override def ejecutadaPorEnemigo(fuente: Enemigo, objetivo: Target): Boolean =
    objetivo.recibirAtaque(fuente.attackPoints)
