package cl.uchile.dcc
package acciones
import unidades.{Enemigo, MagoBlanco, MagoNegro, Personaje, PersonajeMagico}
import utilizables.Utilizable

trait Accion:
  val nombre: String

  def ejecutadaPorPersonaje(fuente: Personaje, objetivo: Target): Boolean

  def ejecutadaPorMagoNegro(fuente: MagoNegro, objetivo: Target): Boolean

  def ejecutadaPorMagoBlanco(fuente: MagoBlanco, objetivo: Target): Boolean

  def ejecutadaPorEnemigo(fuente: Enemigo, objetivo: Target): Boolean





