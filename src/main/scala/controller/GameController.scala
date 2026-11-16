package cl.uchile.dcc
package controller
import acciones.{Accion, Target}
import combate.Turnos
import unidades.Unidad

import excepciones.InvalidActionException
import unidades.Unidad
/**
  * Controlador principal del juego.
  * En esta clase debes implementar la lógica que conecta las distintas
  * entidades de tu modelo, maneja los turnos, y ejecuta las acciones.
  */
class GameController:
  private var turnos: Turnos = new Turnos()
  private var actual: Option[Unidad] = None
  private var contadorTurnos: Int = 0
  private val incremento: Double = 1.0
  private var error:Option[String] = None

/**
 *
 * @return retorna mensaje de error, o none si no hay
 * */
  def ultimoerror: Option[String] = error


  def agregarUnidad(unidad: Unidad): Unit =
    turnos.agregarUnidad(unidad)

  def quitarUnidad(unidad: Unidad): Unit =
    turnos.quitarUnidad(unidad)
    if actual.isDefined && actual.get == unidad then actual = None

  def unidadesEnJuego: List[Unidad] = turnos.unidadesEnBatalla

  def unidadActual: Option[Unidad] = actual

  def turnosJugados: Int = contadorTurnos

  def siguienteTurno(): Option[Unidad] =
    if turnos.unidadesEnBatalla.nonEmpty then
      while turnos.unidadConTurno.isEmpty do
        turnos.aumentarBarras(incremento)
      actual = turnos.unidadConTurno
    actual

  def accionesDe(unidad: Unidad): List[Accion] = unidad.acciones


  def ejecutarAccion(nombreAccion: String, objetivo: Target): Boolean =
    if actual.isEmpty then
      error = Some("No hay unidad jugando su turno")
      false
    else
      val unidad = actual.get
      val accion = unidad.buscarAccion(nombreAccion)
      if accion.isEmpty then
        error = Some(s"${unidad.nombre}  no tiene accion $nombreAccion")
        false
      else
        val exito =
          try unidad.doAction(accion.get, objetivo)
          catch {
            case e: InvalidActionException =>
              error = Some(e.getMessage)
              false
          }
        if exito then {
          turnos.reiniciarBarra(unidad)
          actual = None
          contadorTurnos += 1
          error = None
        }
        exito

  def reiniciar(): Unit =
    turnos = new Turnos()
    actual = None
    contadorTurnos = 0
    error = None
