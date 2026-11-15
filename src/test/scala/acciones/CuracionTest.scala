package cl.uchile.dcc
package acciones
import unidades.{MagoBlanco, MagoNegro, Caballero, Enemigo}
import utilizables.{Baston}

class CuracionTest extends munit.FunSuite:
  var curacion: Curacion = null
  var white: MagoBlanco = null
  var knight: Caballero = null

  override def beforeEach(context: BeforeEach): Unit =
    curacion = new Curacion("curacion")
    white = new MagoBlanco("A", 60, 4, 45, 50)
    white.slotArma = Some(new Baston("B", 5, 4, white, 20))
    knight = new Caballero("C", 100, 15, 70)

  test("built with default mana cost"):
    val accion = new Curacion("curacion")
    assertEquals(accion.nombre, "curacion")
    assertEquals(accion.costoMana, 15)

  test("cura 20% de vida maxima mas daño mágico / 4"):
    knight.hpPoints = 50
    assert(white.doAction(curacion, knight))
    assert(knight.hpPoints == 75)

  test("curación no supera vida maxima"):
    knight.hpPoints = 95
    assert(white.doAction(curacion, knight))
    assert(knight.hpPoints == 100)

  test("lanzar curacion cuesta 15 mana"):
    white.doAction(curacion, knight)
    assert(white.manaPoints == 35.0)

  test("mago blanco puede curarse a si mismo"):
    white.hpPoints = 30
    assert(white.doAction(curacion, white))
    assert(white.hpPoints == 47)

  test("se puede curar a un enemigo"):
    val enemigo = new Enemigo("goblin", 100, 10, 30, 20)
    assert(!white.doAction(curacion, enemigo))
    assert(white.manaPoints == 50.0)

  test("mago negro no puede lanzar curacion"):
    val negro = new MagoNegro("Vivi", 60, 4, 40, 50)
    assert(!negro.doAction(curacion, knight))