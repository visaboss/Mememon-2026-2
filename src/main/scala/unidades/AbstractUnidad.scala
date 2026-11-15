package cl.uchile.dcc
package unidades
import acciones.{AbstractTarget, Accion}
import mapajuego.Panel


abstract class AbstractUnidad(override val nombre: String, var hpPoints: Int,
                              var defensePoints: Int, var weight: Double)
  extends AbstractTarget, Unidad:

  /** Puntos de vida máximos: la vida con la que se creó la unidad. */
  val maxHpPoints: Int = hpPoints

  var panelActual: Option[Panel] = None

  override def buscarAccion(nombreAccion: String): Option[Accion] =
    var encontrada: Option[Accion] = None
    for accion <- acciones do
      if encontrada.isEmpty && accion.nombre == nombreAccion then
        encontrada = Some(accion)
    encontrada


  override def recibirAtaque(danio: Int): Boolean =
    perderVida(danio - defensePoints)
    true

  override def recibirTrueno(danioMagico: Int): Boolean =
    recibirDanioMagico(danioMagico)
    true

  override def recibirDanioMagico(danio: Int): Unit =
    perderVida(danio)


  def curar(cantidad: Int): Unit =
    hpPoints = hpPoints + cantidad
    if hpPoints > maxHpPoints then hpPoints = maxHpPoints

  protected def perderVida(cantidad: Int): Unit =
    if cantidad > 0 then
      hpPoints = hpPoints - cantidad
      if hpPoints < 0 then hpPoints = 0
