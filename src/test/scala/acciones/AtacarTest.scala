package cl.uchile.dcc
package acciones

import unidades.{Caballero, Enemigo, MagoNegro}
import utilizables.{Espada, Daga}
import mapajuego.Panel

class AtacarTest extends munit.FunSuite:
  var attack: Atacar = null
  var knight: Caballero = null
  var enemy: Enemigo = null

  override def beforeEach(context: BeforeEach): Unit = {
    attack = new Atacar("atacar")
    knight = new Caballero("artur", 100, 15, 70)
    enemy = new Enemigo("enemy", 100, 10, 30, 20)
  }

  test("created with correct name"):
    val accion = new Atacar("atacar")
    assertEquals(accion.nombre, "atacar")

  test("personaje con arma inflige ataque de arma menos defensa de objetivo"):
    knight.slotArma = Some(new Espada("Excalibur", 50, 5, knight))
    assert(knight.doAction(attack, enemy))
    assert(enemy.hpPoints == 60)

  test("personaje sin arma no puede atacar"):
    assert(!knight.doAction(attack, enemy))
    assert(enemy.hpPoints == 100)

  test("mago con arma puede atacar"):
    val mago = new MagoNegro("A", 60, 4, 40, 50)
    mago.slotArma = Some(new Daga("B", 30, 2, mago))
    assert(mago.doAction(attack, enemy))
    assert(enemy.hpPoints == 80)

  test("enemigo hace ataque menos defensa de objetivo"):
    assert(enemy.doAction(attack, knight))
    assert(knight.hpPoints == 95)

  test("defensa supera ataque, objetivo no pierde vida"):
    val rata = new Enemigo("Rata", 10, 1, 5, 3)
    assert(rata.doAction(attack, knight))
    assert(knight.hpPoints == 100)

  test("vida no baja de 0"):
    val dragon = new Enemigo("Dragon", 500, 50, 80, 500)
    assert(dragon.doAction(attack, knight))
    assert(knight.hpPoints == 0)

  test("no se puede atacar panel"):
    knight.slotArma = Some(new Espada("Excalibur", 50, 5, knight))
    assert(!knight.doAction(attack, new Panel(1, 1)))