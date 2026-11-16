package cl.uchile.dcc
package acciones

import unidades.{MagoNegro, Enemigo, MagoBlanco}
import utilizables.{Varita}
import mapajuego.Panel
import excepciones.{InvalidTargetException, InvalidActionException, insufficienteManaException}

class MeteoritoTest extends munit.FunSuite:
  var meteorito: Meteorito = null
  var black: MagoNegro = null
  var panel: Panel = null
  var enemyOne: Enemigo = null
  var enemyTwo: Enemigo = null

  override def beforeEach(context: BeforeEach): Unit =
    meteorito = new Meteorito("A")
    black = new MagoNegro("B", 60, 4, 40, 60)
    black.slotArma = Some(new Varita("C", 10, 1, black, 30))
    panel = new Panel(2, 2)
    enemyOne = new Enemigo("D", 100, 10, 30, 20)
    enemyTwo = new Enemigo("E", 80, 5, 40, 25)
    panel.addUnit(enemyOne)
    panel.addUnit(enemyTwo)

  test("Meteorito built with default mana cost"):
    val accion = new Meteorito("A")
    assertEquals(accion.nombre, "A")
    assertEquals(accion.costoMana, 50)

  test("meteorito daña todas las unidades de panel objetivo"):
    assert(black.doAction(meteorito, panel))
    assert(enemyOne.hpPoints == 70)
    assert(enemyTwo.hpPoints == 50)

  test("lanzar meteorito cuesta 50 mana"):
    black.doAction(meteorito, panel)
    assert(black.manaPoints == 10.0)

  test("meteorito sobre panel vacio tiene exito"):
    assert(black.doAction(meteorito, new Panel(5, 5)))

  test("meteorito no puede lanzar sobre unidad"):
    intercept[InvalidTargetException]{
      black.doAction(meteorito, enemyOne)
    }
    assert(enemyOne.hpPoints == 100)

  test("sin mana suficiente no se lanza meteorito"):
    black.manaPoints = 40
    intercept[insufficienteManaException]{
      black.doAction(meteorito, panel)
    }
    assert(enemyOne.hpPoints == 100)

  test("mago blanco no puede lanzar meteorito"):
    val blanco = new MagoBlanco("A", 60, 4, 45, 60)
    intercept[InvalidActionException]{
      blanco.doAction(meteorito, panel)
    }


  test("meteorito no daña a las unidades derrotadas del panel"):
    enemyOne.hpPoints = 0
    assert(black.doAction(meteorito, panel))
    assert(enemyOne.hpPoints == 0)
    assert(enemyTwo.hpPoints == 50)
