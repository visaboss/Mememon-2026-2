package cl.uchile.dcc
package acciones
import utilizables.{Espada,Daga,Arco,Varita,Baston, Fortaleza}
import unidades.{Caballero, Enemigo, MagoNegro, Arquero, Ladron, MagoBlanco}

class EquiparArmaTest extends munit.FunSuite:
  var knight: Caballero = null
  var sword: Espada = null
  var dagger: Daga = null
  var bow: Arco = null
  var varita: Varita = null
  var baston: Baston = null


  override def beforeEach(context: BeforeEach): Unit =
    knight = new Caballero("artur", 100, 15, 70)
    sword = new Espada("a", 50, 5, knight)
    dagger = new Daga("b", 30, 2, knight)
    bow = new Arco("c", 40, 3, knight)
    varita = new Varita("d", 10, 1, knight, 30)
    baston = new Baston("e", 15, 4, knight, 45)

  test("equipararma built with name and list"):
    val owner = new Caballero("arthur", 100, 15, 70)
    val sword = new Espada("A", 25, 3, owner)
    val dagger = new Daga("B", 15, 1, owner)
    val accion = new EquiparArma("C", List(sword, dagger))
    assertEquals(accion.nombre, "C")
    assertEquals(accion.posibleObjects, List(sword, dagger))

  test("personaje equipa un arma de su inventario"):
    knight.agregarUtilizable(sword)
    assert(knight.doAction(knight.buscarAccion("Equipar arma").get, sword))
    assert(knight.slotArma == Some(sword))

  test("equipar personaje pasa a ser dueño de arma"):
    val ab = new Caballero("A", 90, 12, 65)
    ab.agregarUtilizable(sword)
    assert(ab.doAction(ab.buscarAccion("Equipar arma").get, sword))
    assert(sword.owner == ab)

  test("no puede equipar un arma que no esta entre posibles objetos"):
    val equipar = new EquiparArma("Equipar arma", List())
    assert(!knight.doAction(equipar, sword))
    assert(knight.slotArma == None)

  test("personaje no puede equiparse arma que su clase no permite"):
    knight.agregarUtilizable(baston)
    assert(!knight.doAction(knight.buscarAccion("Equipar arma").get, baston))
    assert(knight.slotArma == None)

  test("no se puede equipar poción"):
    val pocion = new Fortaleza("A")
    knight.agregarUtilizable(pocion)
    assert(!knight.doAction(knight.buscarAccion("Equipar arma").get, pocion))

  test("enemigo no puede equiparse armas"):
    val enemigo = new Enemigo("goblin", 100, 10, 30, 20)
    assert(!enemigo.doAction(new EquiparArma("Equipar arma", List(sword)), sword))

  test("mago se equipa arma que su clase permite"):
    val mago = new MagoNegro("A", 60, 4, 40, 50)
    mago.agregarUtilizable(varita)
    assert(mago.doAction(mago.buscarAccion("Equipar arma").get, varita))
    assert(mago.slotArma == Some(varita))

  test("caballero puede equiparse espada, daga y arco"):
    assert(knight.puedeEquipar(sword))
    assert(knight.puedeEquipar(dagger))
    assert(knight.puedeEquipar(bow))
    assert(!knight.puedeEquipar(varita))
    assert(!knight.puedeEquipar(baston))

  test("arquero puede equiparse espada, arco y varita"):
    val arquero = new Arquero("Legolas", 80, 8, 60)
    assert(arquero.puedeEquipar(sword))
    assert(!arquero.puedeEquipar(dagger))
    assert(arquero.puedeEquipar(bow))
    assert(arquero.puedeEquipar(varita))
    assert(!arquero.puedeEquipar(baston))

  test("ladrin puede equiparse espada y daga"):
    val ladron = new Ladron("A", 70, 6, 55)
    assert(ladron.puedeEquipar(sword))
    assert(ladron.puedeEquipar(dagger))
    assert(!ladron.puedeEquipar(bow))
    assert(!ladron.puedeEquipar(varita))
    assert(!ladron.puedeEquipar(baston))

  test("mago negro puede equiparse daga, varita y baston"):
    val mago = new MagoNegro("A", 60, 4, 40, 50)
    assert(!mago.puedeEquipar(sword))
    assert(mago.puedeEquipar(dagger))
    assert(!mago.puedeEquipar(bow))
    assert(mago.puedeEquipar(varita))
    assert(mago.puedeEquipar(baston))

  test("mago blanco puede equiparse arco, varita y bastón"):
    val mago = new MagoBlanco("Rosa", 60, 4, 45, 50)
    assert(!mago.puedeEquipar(sword))
    assert(!mago.puedeEquipar(dagger))
    assert(mago.puedeEquipar(bow))
    assert(mago.puedeEquipar(varita))
    assert(mago.puedeEquipar(baston))