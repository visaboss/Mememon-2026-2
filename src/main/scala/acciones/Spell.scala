package cl.uchile.dcc
package acciones
import acciones.{AbstractAccion, Target}
import unidades.PersonajeMagico
import excepciones.{missingWeaponException,insufficienteManaException}


abstract class Hechizo(nombre: String, var costoMana: Int)  extends AbstractAccion(nombre):
  protected def lanzar(mago: PersonajeMagico, objetivo: Target): Boolean =
    if !mago.tieneArmaMagica then
      throw new missingWeaponException(s"${mago.nombre} necesita arma magica para lanzar")

    if mago.manaPoints < costoMana then
      throw new insufficienteManaException(s"${mago.nombre} necesita $costoMana pero tiene ${mago.manaPoints}")

    aplicarEfecto(mago.danioMagico, objetivo)
    mago.pagarHechizo(costoMana)
    true

  protected def aplicarEfecto(danioMagico: Int, objetivo: Target): Boolean