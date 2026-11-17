package cl.uchile.dcc
package unidades
class EnemigosTest extends munit.FunSuite:
  var enemigo: Enemigo= null

  override def beforeEach(context: BeforeEach): Unit =
    enemigo = new Enemigo("Goblin", 50, 12, 30, 5)

  test("Un Enemigo se crea con los valores correctos"):
    assert(enemigo.nombre == "Goblin")
    assert(enemigo.hpPoints == 50)
    assert(enemigo.defensePoints == 12)
    assert(enemigo.weight == 30.0)
    assert(enemigo.attackPoints == 5)

  test("Dos Enemigos con los mismos parametros son iguales"):
    val otro = new Enemigo("Goblin", 50, 12, 30, 5)
    assertEquals(enemigo, otro)
    assertEquals(otro, enemigo)

  test("Dos Enemigos con distinto nombre no son iguales"):
    assertNotEquals(enemigo, new Enemigo("Orco", 50, 12, 30, 5))

  test("Dos Enemigos con distinta vida no son iguales"):
    assertNotEquals(enemigo, new Enemigo("Goblin", 49, 12, 30, 5))

  test("Dos Enemigos con distinta defensa no son iguales"):
    assertNotEquals(enemigo, new Enemigo("Goblin", 50, 13, 30, 5))

  test("Dos Enemigos con distinto peso no son iguales"):
    assertNotEquals(enemigo, new Enemigo("Goblin", 50, 12, 31, 5))

  test("Dos Enemigos con distinto ataque no son iguales"):
    assertNotEquals(enemigo, new Enemigo("Goblin", 50, 12, 30, 6))

  test("enemigo dañado no igual a sano"):
    val herido = new Enemigo("Goblin", 50, 12, 30, 5)
    herido.hpPoints = 10
    assertNotEquals(herido, new Enemigo("Goblin", 50, 12, 30, 5))