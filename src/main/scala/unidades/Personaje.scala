package cl.uchile.dcc
package unidades
import scala.collection.mutable
import utilizables.Arma
import acciones.{Accion, Atacar, ConsumirPocion, EquiparArma, Moverse, Target}
import utilizables.Utilizable

import utilizables.{Arma, Utilizable}
//esto seria una clase abstracta,

abstract class Personaje(nombre: String, hp:Int, defensa: Int, peso: Double) extends AbstractUnidad(nombre, hp, defensa, peso):

  var slotArma: Option[Arma] = None
  private val inventario: mutable.ListBuffer[Utilizable] = mutable.ListBuffer()

  override def equals(obj: Any): Boolean =
    if getClass == obj.getClass then
      val other = obj.asInstanceOf[Personaje]
      nombre == other.nombre &&
        hpPoints == other.hpPoints &&
        defensePoints == other.defensePoints &&
        weight == other.weight
    else false
  // aqui deber ir slot de arma, unica supongo, e inventario(lista de cosas supongo, de utilizables)


  override def barraAccion: Double =
    if slotArma.isDefined then
      weight + 0.5 * slotArma.get.weight
    else
      weight  

  def inventarioUtilizables: List[Utilizable] = inventario.toList

  def agregarUtilizable(objeto: Utilizable): Unit =
    inventario += objeto

  def quitarUtilizable(objeto: Utilizable): Unit =
    inventario -= objeto

  override def acciones: List[Accion] =
    List(
      new Atacar("Atacar"),
      new Moverse("Moverse"),
      new EquiparArma("Equipar arma", inventarioUtilizables),
      new ConsumirPocion("Consumir pocion", inventarioUtilizables)
    )

  override def doAction(accion: Accion, objetivo: Target): Boolean =
    accion.ejecutadaPorPersonaje(this, objetivo)


  def puedeEquipar(arma: Arma): Boolean

  def aumentarDefensa(cantidad: Int): Unit =
    defensePoints = defensePoints + cantidad


  def recibirPocionMana(): Boolean = false

  def recibirFuerzaMagica(): Boolean = false

  override def recibirCuracion(danioMagico: Int): Boolean =
    curar(maxHpPoints * 20 / 100 + danioMagico / 4)
    true



