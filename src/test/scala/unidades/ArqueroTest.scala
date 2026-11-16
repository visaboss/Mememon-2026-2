package cl.uchile.dcc
package unidades
class ArqueroTest extends munit.FunSuite:
  var arquero: Arquero = null

  override def beforeEach(context: BeforeEach): Unit =
    arquero = new Arquero("Legolas", 80, 8, 60)

  test("Un Arquero se crea con los valores correctos"):
    assert(arquero.nombre == "Legolas")
    assert(arquero.hpPoints == 80)
    assert(arquero.defensePoints == 8)
    assert(arquero.weight == 60.0)

  test("Un Arquero nuevo no tiene arma equipada"):
    assert(arquero.slotArma == None)

  test("Un Arquero nuevo tiene el inventario vacío"):
    assert(arquero.inventarioUtilizables.isEmpty)

  test("Dos Arqueros con los mismos parametros son iguales"):
    val otro = new Arquero("Legolas", 80, 8, 60)
    assertEquals(arquero, otro)
    assertEquals(otro, arquero)

  test("Dos Arqueros con distinto nombre no son iguales"):
    assertNotEquals(arquero, new Arquero("Otro", 80, 8, 60))

  test("Dos Arqueros con distinta vida no son iguales"):
    assertNotEquals(arquero, new Arquero("Legolas", 79, 8, 60))

  test("Dos Arqueros con distinta defensa no son iguales"):
    assertNotEquals(arquero, new Arquero("Legolas", 80, 9, 60))

  test("Dos Arqueros con distinto peso no son iguales"):
    assertNotEquals(arquero, new Arquero("Legolas", 80, 8, 61))

  test("Un Arquero y un Ladrón con los mismos parametros no son iguales"):
    val a: Personaje = arquero
    val b: Personaje = new Ladron("Legolas", 80, 8, 60)
    assertNotEquals(a, b)
    assertNotEquals(b, a)