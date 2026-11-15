package acciones
import acciones.{AbstractAccion, Target}
import unidades.PersonajeMagico

abstract class Hechizo(nombre: String, var costoMana: Int)  extends AbstractAccion(nombre):
  protected def lanzar(mago: PersonajeMagico, objetivo: Target): Boolean =
    if mago.manaPoints >= costoMana && aplicarEfecto(mago.danioMagico, objetivo) then
      mago.pagarHechizo(costoMana)
      true
    else false

  protected def aplicarEfecto(danioMagico: Int, objetivo: Target): Boolean