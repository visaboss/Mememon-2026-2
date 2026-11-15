package cl.uchile.dcc
package unidades
import acciones.{Accion, Atacar, Moverse, Target}
//capaz despues al igual que con personajes se va a tener que hacer una diferencia entre enemigo normal y magico
class Enemigo(nombre: String, hp: Int, defensa: Int, peso: Double, var attackPoints: Int) extends AbstractUnidad(nombre, hp, defensa, peso):
  override def barraAccion: Double = weight

  override def equals(obj: Any): Boolean =
    if getClass == obj.getClass then
      val other = obj.asInstanceOf[Enemigo]
      nombre == other.nombre &&
        hpPoints == other.hpPoints &&
        defensePoints == other.defensePoints &&
        weight == other.weight &&
        attackPoints == other.attackPoints
    else false

  override def acciones: List[Accion] =
    List(new Atacar("Atacar"), new Moverse("Moverse"))

  override def doAction(accion: Accion, objetivo: Target): Boolean =
    accion.ejecutadaPorEnemigo(this, objetivo)
  
  override def recibirPurificacion(danioMagico: Int): Boolean =
    perderVida(hpPoints * 15 / 100 + danioMagico / 2)
    true

  