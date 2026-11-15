package cl.uchile.dcc
package unidades
class CaballeroTest extends munit.FunSuite:
  var caballero: Caballero = null

  override def beforeEach(context: BeforeEach): Unit =
    caballero = new Caballero("Arthur", 100, 15, 70)

  test("Un Caballero se crea con los valores correctos"):
    assert(caballero.nombre == "Arthur")
    assert(caballero.hpPoints == 100)
    assert(caballero.defensePoints == 15)
    assert(caballero.weight == 70.0)

  test("Un Caballero nuevo no tiene arma equipada"):
    assert(caballero.slotArma == None)

  test("Un Caballero nuevo tiene el inventario vacío"):
    assert(caballero.inventarioUtilizables.isEmpty)

  test("Dos Caballeros con los mismos parámetros son iguales"):
    val otro = new Caballero("Arthur", 100, 15, 70)
    assertEquals(caballero, otro)
    assertEquals(otro, caballero)

  test("Dos Caballeros con distinto nombre no son iguales"):
    assertNotEquals(caballero, new Caballero("Otro", 100, 15, 70))

  test("Dos Caballeros con distinta vida no son iguales"):
    assertNotEquals(caballero, new Caballero("Arthur", 99, 15, 70))

  test("Dos Caballeros con distinta defensa no son iguales"):
    assertNotEquals(caballero, new Caballero("Arthur", 100, 16, 70))

  test("Dos Caballeros con distinto peso no son iguales"):
    assertNotEquals(caballero, new Caballero("Arthur", 100, 15, 71))

  test("Un Caballero y un Arquero con los mismos parámetros no son iguales"):
    val a: Personaje = caballero
    val b: Personaje = new Arquero("Arthur", 100, 15, 70)
    assertNotEquals(a, b)
    assertNotEquals(b, a)
  test("caballero dañado no igual a sano"):
    val herido = new Caballero("Arthur", 100, 15, 70)
    herido.hpPoints = 10
    assertNotEquals(herido, new Caballero("Arthur", 100, 15, 70))