package cl.uchile.dcc
package acciones

import unidades.{MagoBlanco, Enemigo, Caballero, MagoNegro}
import utilizables.{Baston}
import excepciones.{InvalidActionException, InvalidTargetException}
class PurificacionTest extends munit.FunSuite:
  var purificacion: Purificacion = null
  var white: MagoBlanco = null
  var enemy: Enemigo = null

  override def beforeEach(context: BeforeEach): Unit =
    purificacion = new Purificacion("purificacion")
    white = new MagoBlanco("A", 60, 4, 45, 50)
    white.slotArma = Some(new Baston("B", 5, 4, white, 20))
    enemy = new Enemigo("C", 100, 10, 30, 20)

  test("Purificacion built with default cost"):
    val accion = new Purificacion("A")
    assertEquals(accion.nombre, "A")
    assertEquals(accion.costoMana, 25)

  test("reduce 15% de vida actual de enemigo mas daño magico / 2"):
    assert(white.doAction(purificacion, enemy))
    assert(enemy.hpPoints == 75)

  test("usa vida actual,no inicial"):
    enemy.hpPoints = 40
    assert(white.doAction(purificacion, enemy))
    assert(enemy.hpPoints == 24)

  test("lanzar purificacion cuesta 25 mana"):
    white.doAction(purificacion, enemy)
    assert(white.manaPoints == 25.0)

  test("no se puede purificar a un personaje"):
    val caballero = new Caballero("Arthur", 100, 15, 70)
    intercept[InvalidTargetException]{
      white.doAction(purificacion, caballero)
    }
    assert(caballero.hpPoints == 100)

  test("mago negro no puede lanzar prificacion"):
    val negro = new MagoNegro("Vivi", 60, 4, 40, 50)
    intercept[InvalidActionException]{
      negro.doAction(purificacion, enemy)
    }