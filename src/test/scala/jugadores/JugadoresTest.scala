package jugadores

import unidades.{Caballero, Enemigos}

class JugadoresTest extends munit.FunSuite:
  var jugador: Jugadores = null
  var caballero: Caballero = null
  var goblin: Enemigos = null

  override def beforeEach(context: BeforeEach): Unit =
    jugador = new Jugadores()
    caballero = new Caballero("artur", 100, 15, 70)
    goblin = new Enemigos("goblin", 50, 12, 30, 5)

  test("jugador nuevo tiene lista de unidades vacía"):
    assert(jugador.ListaUnidades.isEmpty)

  test("jugador sin unidades es derrotado,ya que ninguna unidad esta viva"):
    assert(jugador.VictoriaOrDefeat == true)

  test("jugador puede tener unidades"):
    jugador.agregarUnidad(caballero)
    assert(jugador.ListaUnidades == List(caballero))

  test("jugador puede tener personajes y enemigos"):
    jugador.agregarUnidad(caballero)
    jugador.agregarUnidad(goblin)
    assert(jugador.ListaUnidades == List(caballero, goblin))

  test("jugador con unidades vivas no derrotado"):
    jugador.agregarUnidad(caballero)
    assert(jugador.VictoriaOrDefeat == false)

  test("jugador con alguna unidad viva no derrotado"):
    goblin.HpPoints = 0
    jugador.agregarUnidad(caballero)
    jugador.agregarUnidad(goblin)
    assert(jugador.VictoriaOrDefeat == false)

  test("jugador con todas sus unidades en 0 de vida es derrotado"):
    caballero.HpPoints = 0
    jugador.agregarUnidad(caballero)
    assert(jugador.VictoriaOrDefeat == true)

  test("jugador queda derrotado cuando su última unidad viva muere"):
    jugador.agregarUnidad(goblin)
    assert(jugador.VictoriaOrDefeat == false)
    goblin.HpPoints = 0
    assert(jugador.VictoriaOrDefeat == true)