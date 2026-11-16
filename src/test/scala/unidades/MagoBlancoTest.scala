package cl.uchile.dcc
package unidades
class MagoBlancoTest extends munit.FunSuite:
  var mago: MagoBlanco = null

  override def beforeEach(context: BeforeEach): Unit =
    mago = new MagoBlanco("Rosa", 60, 4, 45, 50)

  test("Un Mago Blanco se crea con los valores correctos"):
    assert(mago.nombre == "Rosa")
    assert(mago.hpPoints == 60)
    assert(mago.defensePoints == 4)
    assert(mago.weight == 45.0)
    assert(mago.manaPoints == 50)

  test("Un Mago Blanco nuevo no tiene arma equipada"):
    assert(mago.slotArma == None)

  test("Un Mago Blanco nuevo tiene el inventario vacío"):
    assert(mago.inventarioUtilizables.isEmpty)

  test("Dos Magos Blancos con los mismos parametros son iguales"):
    val otro = new MagoBlanco("Rosa", 60, 4, 45, 50)
    assertEquals(mago, otro)
    assertEquals(otro, mago)

  test("Dos Magos Blancos con distinto nombre no son iguales"):
    assertNotEquals(mago, new MagoBlanco("Otro", 60, 4, 45, 50))

  test("Dos Magos Blancos con distinta vida no son iguales"):
    assertNotEquals(mago, new MagoBlanco("Rosa", 59, 4, 45, 50))

  test("Dos Magos Blancos con distinta defensa no son iguales"):
    assertNotEquals(mago, new MagoBlanco("Rosa", 60, 5, 45, 50))

  test("Dos Magos Blancos con distinto peso no son iguales"):
    assertNotEquals(mago, new MagoBlanco("Rosa", 60, 4, 46, 50))

  test("Dos Magos Blancos con distinto mana no son iguales"):
    assertNotEquals(mago, new MagoBlanco("Rosa", 60, 4, 45, 99))

  test("Un Mago Blanco y un Mago Negro con los mismos parametros no son iguales"):
    val a: Personaje = mago
    val b: Personaje = new MagoNegro("Rosa", 60, 4, 45, 50)
    assertNotEquals(a, b)
    assertNotEquals(b, a)