package cl.uchile.dcc
package unidades
import acciones.{AbstractTarget, Accion,Target}
import mapajuego.Panel
import excepciones.defeatedUnitException
import observer.Observer
import scala.collection.mutable


abstract class AbstractUnidad(override val nombre: String, var hpPoints: Int,
                              var defensePoints: Int, var weight: Double)
  extends AbstractTarget, Unidad:

  /** Puntos de vida maximos: la vida con la que se creó la unidad. */
  val maxHpPoints: Int = hpPoints

  var panelActual: Option[Panel] = None

  private val observers: mutable.Set[Observer[Unidad]] =
    mutable.LinkedHashSet.empty[Observer[Unidad]]

  override def attach(observer: Observer[Unidad]): Unit =
    observers += observer

  override def detach(observer: Observer[Unidad]): Unit =
    observers -= observer

  override def notifyObservers(notification: Unidad): Unit =
    for observer <- observers do observer.update(this, notification)

  override def estaDerrotada: Boolean = hpPoints <=0

  override def doAction(accion: Accion, objetivo: Target): Boolean =
    if estaDerrotada then
      throw new defeatedUnitException(s"$nombre no tiene puntos de vida")
    ejecutar(accion, objetivo)

  protected def ejecutar(accion: Accion, target: Target): Boolean

  protected def verificarRecibe() : Unit =
    if estaDerrotada then
      throw new defeatedUnitException(s"$nombre derrotado, no recibir acciones")


  override def buscarAccion(nombreAccion: String): Option[Accion] =
    var encontrada: Option[Accion] = None
    for accion <- acciones do
      if encontrada.isEmpty && accion.nombre == nombreAccion then
        encontrada = Some(accion)
    encontrada


  override def recibirAtaque(danio: Int): Boolean =
    verificarRecibe()
    perderVida(danio- defensePoints)
    true


  override def recibirTrueno(danioMagico: Int): Boolean = {
    verificarRecibe()
    recibirDanioMagico(danioMagico)
    true
  }

  override def recibirDanioMagico(danio: Int): Unit =
    perderVida(danio)


  def curar(cantidad: Int): Unit =
    hpPoints = hpPoints + cantidad
    if hpPoints > maxHpPoints then hpPoints = maxHpPoints

  /** Resta puntos de vida, si unidad queda derrotada avisa observadores.
   *
   * @param cantidad puntos de vida perdidos
   */
  protected def perderVida(cantidad: Int): Unit =
    if cantidad > 0 && !estaDerrotada then
      hpPoints = hpPoints - cantidad
      if hpPoints < 0 then hpPoints = 0
      if estaDerrotada then notifyObservers(this)