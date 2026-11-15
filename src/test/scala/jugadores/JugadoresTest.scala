package jugadores

import unidades.{Caballero, Enemigo}

class JugadoresTest extends munit.FunSuite:
  var jugador: Jugador = null
  var caballero: Caballero = null
  var goblin: Enemigo = null

  override def beforeEach(context: BeforeEach): Unit =
    jugador = new Jugador()
    caballero = new Caballero("artur", 100, 15, 70)
    goblin = new Enemigo("goblin", 50, 12, 30, 5)

  test("jugador nuevo tiene lista de unidades vacía"):
    assert(jugador.listaUnidades.isEmpty)

  test("jugador sin unidades es derrotado,ya que ninguna unidad esta viva"):
    assert(jugador.derrotado == true)

  test("jugador puede tener unidades"):
    jugador.agregarUnidad(caballero)
    assert(jugador.listaUnidades == List(caballero))

  test("jugador puede tener personajes y enemigos"):
    jugador.agregarUnidad(caballero)
    jugador.agregarUnidad(goblin)
    assert(jugador.listaUnidades == List(caballero, goblin))

  test("jugador con unidades vivas no derrotado"):
    jugador.agregarUnidad(caballero)
    assert(jugador.derrotado == false)

  test("jugador con alguna unidad viva no derrotado"):
    goblin.hpPoints = 0
    jugador.agregarUnidad(caballero)
    jugador.agregarUnidad(goblin)
    assert(jugador.derrotado == false)

  test("jugador con todas sus unidades en 0 de vida es derrotado"):
    caballero.hpPoints = 0
    jugador.agregarUnidad(caballero)
    assert(jugador.derrotado == true)

  test("jugador queda derrotado cuando su última unidad viva muere"):
    jugador.agregarUnidad(goblin)
    assert(jugador.derrotado == false)
    goblin.hpPoints = 0
    assert(jugador.derrotado == true)