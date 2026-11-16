package cl.uchile.dcc
package unidades

import acciones.{Atacar, Curacion, Moverse, Purificacion, Trueno}
import excepciones.defeatedUnitException
import mapajuego.Panel
import utilizables.{Baston, Espada, Varita}

class UnidadDerrotadaTest extends munit.FunSuite:
  var caballero: Caballero = null
  var enemigo: Enemigo = null
  var negro: MagoNegro = null
  var blanco: MagoBlanco = null
  var atacar: Atacar = null

  override def beforeEach(context: BeforeEach): Unit =
    caballero = new Caballero("artur", 100, 15, 70)
    caballero.slotArma = Some(new Espada("Excalibur", 50, 5, caballero))
    enemigo = new Enemigo("Goblin", 100, 10, 30, 20)
    negro = new MagoNegro("A", 60, 4, 40, 50)
    negro.slotArma = Some(new Varita("Rayo", 10, 1, negro, 30))
    blanco = new MagoBlanco("Rosa", 60, 4, 45, 50)
    blanco.slotArma = Some(new Baston("Roble", 5, 4, blanco, 20))
    atacar = new Atacar("Atacar")

  test("Una unidad con vida no esta derrotada"):
    assert(!caballero.estaDerrotada)

  test("Una unidad con 0 de vida esta derrotada"):
    caballero.hpPoints = 0
    assert(caballero.estaDerrotada)

  test("Un personaje sin vida no puede actuar"):
    caballero.hpPoints = 0
    intercept[defeatedUnitException](caballero.doAction(atacar, enemigo))
    assert(enemigo.hpPoints == 100)

  test("Un enemigo sin vida no puede actuar, y el mensaje explica el motivo"):
    enemigo.hpPoints = 0
    val error = intercept[defeatedUnitException](enemigo.doAction(atacar, caballero))
    assert(error.getMessage == "Goblin no tiene puntos de vida")
    assert(caballero.hpPoints == 100)

  test("Un mago negro sin vida no puede lanzar hechizos"):
    negro.hpPoints = 0
    intercept[defeatedUnitException](negro.doAction(new Trueno("Trueno"), enemigo))
    assert(negro.manaPoints == 50.0)

  test("Un mago blanco sin vida no puede lanzar hechizos"):
    blanco.hpPoints = 0
    intercept[defeatedUnitException](blanco.doAction(new Curacion("Curacion"), caballero))
    assert(blanco.manaPoints == 50.0)

  test("Una unidad sin vida no puede moverse"):
    val origen = new Panel(1, 1)
    val destino = new Panel(1, 2)
    origen.addNeighbor(destino)
    origen.addUnit(enemigo)
    enemigo.hpPoints = 0
    intercept[defeatedUnitException](enemigo.doAction(new Moverse("Moverse"), destino))
    assert(origen.unitsInPanel.contains(enemigo))

  test("unidad derrotada no puede recibir ataque, y el mensaje explica el motivo"):
    enemigo.hpPoints = 0
    val error = intercept[defeatedUnitException]{
      caballero.doAction(atacar, enemigo)
    }

    assert(error.getMessage == "Goblin derrotado, no recibir acciones")

  test("unidad derrotada no puede recibir Trueno, y no se gasta mana"):
    enemigo.hpPoints = 0
    intercept[defeatedUnitException](negro.doAction(new Trueno("Trueno"), enemigo))
    assert(negro.manaPoints == 50.0)

  test("personaje derrotado no puede ser curado"):
    caballero.hpPoints = 0
    intercept[defeatedUnitException](blanco.doAction(new Curacion("Curacion"), caballero))
    assert(caballero.hpPoints == 0)
    assert(blanco.manaPoints == 50.0)

  test("enemigo derrotado no puede ser purificado"):
    enemigo.hpPoints = 0
    intercept[defeatedUnitException](blanco.doAction(new Purificacion("Purificacion"), enemigo))
    assert(blanco.manaPoints == 50.0)