package cl.uchile.dcc
package controller
import acciones.{Accion, Target}
import combate.Turnos
import unidades.Unidad

import excepciones.InvalidActionException
import unidades.Unidad

import jugadores.Player
import observer.{Observer, Subject}
import scala.collection.mutable
/**
  * Controlador principal del juego.
  * En esta clase debes implementar la lógica que conecta las distintas
  * entidades de tu modelo, maneja los turnos, y ejecuta las acciones.
  */
class GameController extends Observer[Player]:
  private var turnos: Turnos = new Turnos()
  private var actual: Option[Unidad] = None
  private var contadorTurnos: Int = 0
  private val incremento: Double = 1.0
  private var error:Option[String] = None
  private var jugadores: mutable.ListBuffer[Player] = mutable.ListBuffer()
  private var terminado: Boolean = false
  private var mensaje: Option[String] = None

/**
 * @return retorna mensaje de error, o none si no hay
 * */
  def ultimoerror: Option[String] = error


  /** Indica si juego termino porque uno de los jugadores fue derrotado */
  def juegoTerminado: Boolean = terminado

  /** Entrega mensaje de resultado de ultima accion exitosa
   *
   * @return lo que hizo la unidad,aviso de fin de juego si accion lo
   *  termino,o None si aun no se ha realizado ninguna accion
   */
  def ultimoMensaje: Option[String] = mensaje

  /** Agrega jugador a partida: controlador attach a el y sus
   * unidades entran
   *
   * @param jugador jugador que se agrega
   */
  def agregarJugador(jugador: Player): Unit =
    if !jugadores.contains(jugador) then
      jugadores += jugador
      jugador.attach(this)
      for unidad <- jugador.listaUnidades do
        agregarUnidad(unidad)

  /** Se ejecuta cuando un jugador avisa que fue derrotado: el juego termina. */
  override def update(subject: Subject[Player], response: Player): Unit =
    if !terminado then
      terminado = true
      actual = None
      mensaje = Some(s"Fin del juego: ${response.nombre} fue derrotado")


  def agregarUnidad(unidad: Unidad): Unit =
    turnos.agregarUnidad(unidad)

  def quitarUnidad(unidad: Unidad): Unit =
    turnos.quitarUnidad(unidad)
    if actual.isDefined && actual.get == unidad then actual = None

  def unidadesEnJuego: List[Unidad] = turnos.unidadesEnBatalla

  def unidadActual: Option[Unidad] = actual

  def turnosJugados: Int = contadorTurnos

  def siguienteTurno(): Option[Unidad] =
    if turnos.unidadesEnBatalla.nonEmpty && !terminado then
      while turnos.unidadConTurno.isEmpty do
        turnos.aumentarBarras(incremento)
      actual = turnos.unidadConTurno
    actual

  def accionesDe(unidad: Unidad): List[Accion] = unidad.acciones


  def ejecutarAccion(nombreAccion: String, objetivo: Target): Boolean =
    if terminado then
      error = Some("El juego ya termino")
      false
    else if actual.isEmpty then
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
          if !terminado then
            mensaje = Some(s"${unidad.nombre} realizo $nombreAccion")
        }
        exito

  def reiniciar(): Unit =
    turnos = new Turnos()
    actual = None
    contadorTurnos = 0
    error = None
    for jugador <- jugadores do
      jugador.detach(this)
    jugadores = mutable.ListBuffer()
    terminado=false
    mensaje = None
