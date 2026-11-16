package cl.uchile.dcc
package acciones
import mapajuego.Panel
import unidades.{Caballero, Enemigo, MagoBlanco, MagoNegro}
import utilizables.{Daga, Varita}
import excepciones.{insufficienteManaException,missingWeaponException, InvalidTargetException, InvalidActionException}


class TruenoTest extends munit.FunSuite:
  var trueno: Trueno = null
  var negro: MagoNegro = null
  var enemigo: Enemigo = null

  override def beforeEach(context: BeforeEach): Unit =
    trueno = new Trueno("Trueno")
    negro = new MagoNegro("Vivi", 60, 4, 40, 50)
    negro.slotArma = Some(new Varita("Rayo", 10, 1, negro, 30))
    enemigo = new Enemigo("Goblin", 100, 10, 30, 20)

  test("Trueno built with default cost"):
    val accion = new Trueno("A")
    assertEquals(accion.nombre, "A")
    assertEquals(accion.costoMana, 20)

  test("Trueno reduce la vida del objetivo en el daño magico, sin considerar defensa"):
    assert(negro.doAction(trueno, enemigo))
    assert(enemigo.hpPoints == 70)

  test("Lanzar trueno cuesta 20 de mana"):
    negro.doAction(trueno, enemigo)
    assert(negro.manaPoints == 30.0)



  test("Con el mana justo si se puede lanzar"):
    negro.manaPoints = 20
    assert(negro.doAction(trueno, enemigo))
    assert(negro.manaPoints == 0.0)

  test("Un mago con un arma no magica no puede lanzar Trueno"):
    val otro = new MagoNegro("Kuja", 60, 4, 40, 50)
    otro.slotArma = Some(new Daga("Filo", 30, 2, otro))
    intercept[missingWeaponException](otro.doAction(trueno, enemigo))
    assert(enemigo.hpPoints == 100)
    assert(otro.manaPoints == 50.0)

  test("Un mago sin arma no puede lanzar Trueno"):
    val otro = new MagoNegro("Kuja", 60, 4, 40, 50)
    intercept[missingWeaponException](otro.doAction(trueno, enemigo))
    assert(enemigo.hpPoints == 100)

  test("Un mago sin arma hace 0 de daño magico"):
    val otro = new MagoNegro("Kuja", 60, 4, 40, 50)
    assert(otro.danioMagico == 0)

  test("La fuerza magica aumenta el daño en 50porcientoy se pierde al lanzar"):
    negro.recibirFuerzaMagica()
    assert(negro.doAction(trueno, enemigo))
    assert(enemigo.hpPoints == 55)
    assert(!negro.tieneFuerzaMagica)

  test("Un mago blanco no puede lanzar trueno"):
    val blanco = new MagoBlanco("Rosa", 60, 4, 45, 50)
    intercept[InvalidActionException]{
      blanco.doAction(trueno, enemigo)
    }

  test("Un personaje común no puede lanzar Trueno"):
    val caballero = new Caballero("Arthur", 100, 15, 70)
    intercept[InvalidActionException]{
      caballero.doAction(trueno, enemigo)
    }

  test("un enemigo no puede lanzar hechizos"):
    val caballero = new Caballero("Arthur", 100, 15, 70)
    intercept[InvalidActionException]{
      enemigo.doAction(trueno, caballero)
    }

  test("trueno no se puede lanzar sobre un panel y no gasta mana"):
    intercept[InvalidTargetException]{
      negro.doAction(trueno, new Panel(1, 1))
    }
    assert(negro.manaPoints == 50.0)
