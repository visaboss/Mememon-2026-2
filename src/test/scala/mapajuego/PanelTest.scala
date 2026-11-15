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