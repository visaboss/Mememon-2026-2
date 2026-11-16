package cl.uchile.dcc
package controller

import unidades.{Caballero, Enemigo}
import mapajuego.{Panel}
class GameControllerTest extends munit.FunSuite:
  var game: GameController = null
  var knight: Caballero = null
  var light: Enemigo = null
  var medium: Enemigo = null

  override def beforeEach(context: BeforeEach): Unit =
    game = new GameController()
    knight = new Caballero("A", 100, 15, 70)   // barra max 70
    light = new Enemigo("B", 30, 2, 10, 20)       // barra max 10
    medium = new Enemigo("C", 50, 5, 12, 15)         // barra max 12

  test("juego nuevo no tiene unidades, turno actual ni turnos jugados"):
    assert(game.unidadesEnJuego.isEmpty)
    assert(game.unidadActual == None)
    assert(game.turnosJugados == 0)

  test("sin unidades no hay siguiente turno"):
    assert(game.siguienteTurno() == None)

  test("se puede agregar y quitar unidades"):
    game.agregarUnidad(knight)
    game.agregarUnidad(light)
    game.quitarUnidad(knight)
    assert(game.unidadesEnJuego == List(light))

  test("siguiente turno de unidad que primero llena su barra"):
    game.agregarUnidad(knight)
    game.agregarUnidad(medium)
    game.agregarUnidad(light)
    assert(game.siguienteTurno() == Some(light))
    assert(game.unidadActual == Some(light))

  test("pedir siguiente turno sin actuar mantiene misma unidad"):
    game.agregarUnidad(medium)
    game.agregarUnidad(light)
    game.siguienteTurno()
    assert(game.siguienteTurno() == Some(light))

  test("unidad con turno ejecuta accion contra objetivo"):
    game.agregarUnidad(light)
    game.agregarUnidad(knight)
    game.siguienteTurno()
    assert(game.ejecutarAccion("Atacar", knight))
    assert(knight.hpPoints == 95)

  test("accion exitosa termina turno y se cuenta"):
    game.agregarUnidad(light)
    game.siguienteTurno()
    game.ejecutarAccion("Atacar", knight)
    assert(game.unidadActual == None)
    assert(game.turnosJugados == 1)

  test("despues de actuar, barra reinicia y turno pasa a otra unidad"):
    game.agregarUnidad(light)
    game.agregarUnidad(medium)
    assert(game.siguienteTurno() == Some(light))
    game.ejecutarAccion("Atacar", knight)
    assert(game.siguienteTurno() == Some(medium))

  test("si accion falla, turno no cambia"):
    game.agregarUnidad(knight)
    game.siguienteTurno()
    assert(!game.ejecutarAccion("Atacar", light))
    assert(game.unidadActual == Some(knight))
    assert(game.turnosJugados == 0)

  test("accion que unidad no posee falla"):
    game.agregarUnidad(light)
    game.siguienteTurno()
    assert(!game.ejecutarAccion("Trueno", knight))
    assert(game.unidadActual == Some(light))

  test("sin unidad con turno no se puede ejecutar accion"):
    game.agregarUnidad(light)
    assert(!game.ejecutarAccion("Atacar", knight))

  test("unidad con turno puede moverse a panel vecino"):
    val origen = new Panel(1, 1)
    val destino = new Panel(2, 1)
    origen.addNeighbor(destino)
    origen.addUnit(light)
    game.agregarUnidad(light)
    game.siguienteTurno()
    assert(game.ejecutarAccion("Moverse", destino))
    assert(destino.unitsInPanel.contains(light))

  test("quitar unidad que tiene el turno le quita el turno"):
    game.agregarUnidad(light)
    game.siguienteTurno()
    game.quitarUnidad(light)
    assert(game.unidadActual == None)

  test("quitar a otra unidad no quita turno a actual"):
    game.agregarUnidad(light)
    game.agregarUnidad(knight)
    game.siguienteTurno()
    game.quitarUnidad(knight)
    assert(game.unidadActual == Some(light))

  test("reiniciar deja juego en estado inicial"):
    game.agregarUnidad(light)
    game.siguienteTurno()
    game.ejecutarAccion("Atacar", knight)
    game.reiniciar()
    assert(game.unidadesEnJuego.isEmpty)
    assert(game.unidadActual == None)
    assert(game.turnosJugados == 0)

  test("juego nuevo no tiene ultimo error"):
    assert(game.ultimoerror == None)

  test("si  accion falla, se guarda motivo"):
    game.agregarUnidad(knight)
    game.siguienteTurno()
    game.ejecutarAccion("Atacar", light)
    assert(game.ultimoerror == Some("A necesita un arma para atacar"))

  test("accion exitosa borra ultimo error"):
    game.agregarUnidad(light)
    game.siguienteTurno()
    game.ejecutarAccion("Atacar", new Panel(1, 1))
    assert(game.ultimoerror.isDefined)
    assert(game.ejecutarAccion("Atacar", knight))
    assert(game.ultimoerror == None)

  test("controlador captura error de unidad derrotada que intenta actuar"):
    game.agregarUnidad(light)
    game.siguienteTurno()
    light.hpPoints = 0
    assert(!game.ejecutarAccion("Atacar", knight))
    assert(game.ultimoerror == Some("B no tiene puntos de vida"))
    assert(game.unidadActual == Some(light))
    assert(knight.hpPoints == 100)

  test("reiniciar borra el ultimo error"):
    game.agregarUnidad(knight)
    game.siguienteTurno()
    game.ejecutarAccion("Atacar", light)
    game.reiniciar()
    assert(game.ultimoerror == None)

