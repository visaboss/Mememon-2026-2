package acciones

import unidades.{Enemigo, MagoBlanco, MagoNegro, Personaje, PersonajeMagico}
import utilizables.Utilizable

trait Accion:
  val nombre: String

  def ejecutadaPorPersonaje(fuente: Personaje, objetivo: Target): Boolean

  def ejecutadaPorMagoNegro(fuente: MagoNegro, objetivo: Target): Boolean

  def ejecutadaPorMagoBlanco(fuente: MagoBlanco, objetivo: Target): Boolean

  def ejecutadaPorEnemigo(fuente: Enemigo, objetivo: Target): Boolean


abstract class Hechizo(override val nombre: String, var costoMana: Int)  extends AbstractAccion(nombre):
  protected def lanzar(mago: PersonajeMagico, objetivo: Target): Boolean =
    if mago.manaPoints >= costoMana && aplicarEfecto(mago.danioMagico, objetivo) then
      mago.pagarHechizo(costoMana)
      true
    else false

  protected def aplicarEfecto(danioMagico: Int, objetivo: Target): Boolean

abstract class MagiaOscura(nombre: String, ManaPoints:Int) extends Hechizo(nombre, ManaPoints):
  override def ejecutadaPorMagoNegro(fuente: MagoNegro, objetivo: Target): Boolean =
    lanzar(fuente, objetivo)

abstract class MagiaDeLuz(nombre:String, ManaPoints:Int) extends Hechizo(nombre, ManaPoints):
  override def ejecutadaPorMagoBlanco(fuente: MagoBlanco, objetivo: Target): Boolean =
    lanzar(fuente, objetivo)


abstract class AccionConUtilizables(override val nombre: String,val posibleObjects : List[Utilizable]) extends AbstractAccion(nombre)




