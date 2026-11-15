package cl.uchile.dcc
package mapajuego
import unidades.Caballero

class PanelTest extends munit.FunSuite:
  var panel: Panel = null

  override def beforeEach(context: BeforeEach): Unit =
    panel = new Panel(2,3)

  test("panel created with given coordenates"):
    assert(panel.x==2)
    assert(panel.y==3)
  test("new panel, doesnt have units"):
    assert(panel.unitsInPanel.isEmpty)

  test("Un Panel puede contener unidades"): 
    val caballero = new Caballero("A", 100, 15, 70)
    panel.addUnit(caballero)
    assert(panel.unitsInPanel.head == caballero)

  test("Un Panel nuevo no tiene paneles aledaños"):
    assert(panel.neighboringPanel.isEmpty)

  test("Un Panel reconoce como aledaño a un panel agregado"):
    val vecino = new Panel(2, 4)
    panel.addNeighbor(vecino)
    assert(panel.neighboringPanel.contains(vecino))

  test("colocar unidad en otro panel saca de anterior"):
    val otro = new Panel(5, 5)
    val caballero = new Caballero("A", 100, 15, 70)
    panel.addUnit(caballero)
    otro.addUnit(caballero)
    assert(panel.unitsInPanel.isEmpty)
    assert(otro.unitsInPanel == List(caballero))

  test("unidad sacada de panel ya no en ningun panel"):
    val caballero = new Caballero("A", 100, 15, 70)
    panel.addUnit(caballero)
    panel.quitarUnidad(caballero)
    assert(caballero.panelActual == None)

  test("panel lejano no vecino"):
    panel.addNeighbor(new Panel(7, 9))
    assert(panel.neighboringPanel.isEmpty)

  test("panel no vecino de si mismo"):
    panel.addNeighbor(panel)
    assert(panel.neighboringPanel.isEmpty)

  test("colocar dos veces misma unidad no duplica"):
    val caballero = new Caballero("A", 100, 15, 70)
    panel.addUnit(caballero)
    panel.addUnit(caballero)
    assert(panel.unitsInPanel == List(caballero))

  test("puede sacar unidad de panel"):
    val caballero = new Caballero("A", 100, 15, 70)
    panel.addUnit(caballero)
    panel.quitarUnidad(caballero)
    assert(panel.unitsInPanel.isEmpty)
