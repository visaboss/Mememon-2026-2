package cl.uchile.dcc
package acciones
import mapajuego.Panel
import unidades.{Caballero, Enemigo}


class MoverseTest extends munit.FunSuite:
  var move: Moverse = null
  var origin: Panel = null
  var neighbor: Panel = null
  var lejano: Panel = null
  var knight: Caballero = null
  var enemy: Enemigo = null

  override def beforeEach(context: BeforeEach): Unit =
    move = new Moverse("Moverse")
    origin = new Panel(1, 1)
    neighbor = new Panel(1, 2)
    lejano = new Panel(3, 3)
    origin.addNeighbor(neighbor)
    knight = new Caballero("artur", 100, 15, 70)
    enemy = new Enemigo("b", 100, 10, 30, 20)

  test("built with correct name"):
    val accion = new Moverse("A")
    assertEquals(accion.nombre, "A")

  test("personaje mueve a panel vecino"):
    origin.addUnit(knight)
    assert(knight.doAction(move, neighbor))
    assert(neighbor.unitsInPanel.contains(knight))
    assert(!origin.unitsInPanel.contains(knight))
    assert(knight.panelActual == Some(neighbor))

  test("enemigo puede moverse tambien"):
    origin.addUnit(enemy)
    assert(enemy.doAction(move, neighbor))
    assert(neighbor.unitsInPanel.contains(enemy))

  test("no se puede mover a panel no vecino"):
    origin.addUnit(knight)
    assert(!knight.doAction(move, lejano))
    assert(origin.unitsInPanel.contains(knight))
    assert(lejano.unitsInPanel.isEmpty)

  test("no se puede mover a mismo panel en que esta"):
    origin.addUnit(knight)
    assert(!knight.doAction(move, origin))

  test("unidad que no esta en ningún panel no se mueve"):
    assert(!knight.doAction(move, neighbor))
    assert(neighbor.unitsInPanel.isEmpty) 