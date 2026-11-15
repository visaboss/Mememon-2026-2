package cl.uchile.dcc
package acciones
import utilizables.{Fortaleza, Curacion as PocionCuracion, Espada, FuerzaMagica, Mana}

import unidades.{Caballero, MagoNegro, Enemigo}

class ConsumirPocionTest extends munit.FunSuite:
  var knight: Caballero = null
  var mage: MagoNegro = null

  override def beforeEach(context: BeforeEach): Unit =
    knight = new Caballero("artur", 100, 20, 70)
    mage = new MagoNegro("mg", 60, 4, 40, 50)

  test("Created with name and list given"):
    val curacion = new PocionCuracion("Curacion A")
    val fortaleza = new Fortaleza("Fortaleza A")
    val accion = new ConsumirPocion("consumir pocion", List(curacion, fortaleza))
    assertEquals(accion.nombre, "consumir pocion")
    assertEquals(accion.posibleObjects, List(curacion, fortaleza))

  test("curacion restaura 20% de vida maxima"):
    val pocion = new PocionCuracion("A")
    knight.agregarUtilizable(pocion)
    knight.hpPoints = 50
    assert(knight.doAction(knight.buscarAccion("Consumir pocion").get, pocion))
    assert(knight.hpPoints == 70)

  test("curacion no supera vida maxima"):
    val pocion = new PocionCuracion("A")
    knight.agregarUtilizable(pocion)
    knight.hpPoints = 90
    assert(knight.doAction(knight.buscarAccion("Consumir pocion").get, pocion))
    assert(knight.hpPoints == 100)

  test("Una poción consumida sale del inventario"):
    val pocion = new PocionCuracion("Elixir")
    knight.agregarUtilizable(pocion)
    knight.doAction(knight.buscarAccion("Consumir pocion").get, pocion)
    assert(knight.inventarioUtilizables.isEmpty)

  test("fortaleza aumenta defensa en 15%"):
    val pocion = new Fortaleza("A")
    knight.agregarUtilizable(pocion)
    assert(knight.doAction(knight.buscarAccion("Consumir pocion").get, pocion))
    assert(knight.defensePoints == 23)

  test("mana restaura 30% de mana maximo de mago"):
    val pocion = new Mana("A")
    mage.agregarUtilizable(pocion)
    mage.manaPoints = 10
    assert(mage.doAction(mage.buscarAccion("Consumir pocion").get, pocion))
    assert(mage.manaPoints == 25.0)

  test("mana no supera el mana maximo"):
    val pocion = new Mana("A")
    mage.agregarUtilizable(pocion)
    mage.manaPoints = 45
    assert(mage.doAction(mage.buscarAccion("Consumir pocion").get, pocion))
    assert(mage.manaPoints == 50.0)

  test("personaje comun no puede consumir mana y pocion sigue en inventario"):
    val pocion = new Mana("A")
    knight.agregarUtilizable(pocion)
    assert(!knight.doAction(knight.buscarAccion("Consumir pocion").get, pocion))
    assert(knight.inventarioUtilizables.contains(pocion))

  test("fuerza mágica activa bonus de daño en mago"):
    val pocion = new FuerzaMagica("A")
    mage.agregarUtilizable(pocion)
    assert(mage.doAction(mage.buscarAccion("Consumir pocion").get, pocion))
    assert(mage.tieneFuerzaMagica)

  test("personaje comun no puede consumir fuerza magica"):
    val pocion = new FuerzaMagica("A")
    knight.agregarUtilizable(pocion)
    assert(!knight.doAction(knight.buscarAccion("Consumir pocion").get, pocion))

  test("no puede consumir pocion que no está en posibles objetos"):
    val pocion = new PocionCuracion("A")
    knight.hpPoints = 50
    assert(!knight.doAction(new ConsumirPocion("Consumir pocion", List()), pocion))
    assert(knight.hpPoints == 50)

  test("no puede consumir arma"):
    val espada = new Espada("A", 50, 5, knight)
    knight.agregarUtilizable(espada)
    assert(!knight.doAction(knight.buscarAccion("Consumir pocion").get, espada))

  test("enemigo no puede consumir pociones"):
    val enemigo = new Enemigo("A", 100, 10, 30, 20)
    val pocion = new PocionCuracion("B")
    assert(!enemigo.doAction(new ConsumirPocion("Consumir pocion", List(pocion)), pocion))
