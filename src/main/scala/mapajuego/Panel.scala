package cl.uchile.dcc
package mapajuego
import scala.collection.mutable
import acciones.AbstractTarget
import unidades.Unidad
import excepciones.{InvalidMovementException}
// var ListUnitsPanel: mutable.ListBuffer[Unidad] = mutable.ListBuffer[]()



class Panel(val x: Int, val y: Int) extends  AbstractTarget:
  private val unidades: mutable.ListBuffer[Unidad] = mutable.ListBuffer()
  private val vecinos: mutable.ListBuffer[Panel] = mutable.ListBuffer()

  def unitsInPanel: List[Unidad] = unidades.toList
  def neighboringPanel: List[Panel] = vecinos.toList

  def esAdyacente(otro: Panel): Boolean =
    math.abs(x - otro.x) + math.abs(y - otro.y) == 1
  //def PanelesAledaños(): Panel =
    //blabla
    //se me ocurre, algun tipo algoritmo, que escanee en un delta de x e y, alrededor, y evaluea, si celdas
    //dentro de (dx, dy) contienen, a otro panel, si es asi, colocarlo en una lista, y delvolver esa lista
    //si al final, la lista esta vacia, entonces, devolver la lista vacia, y
  def addNeighbor(panel:Panel):Unit =
    if esAdyacente(panel) && !vecinos.contains(panel) then
      vecinos += panel
      panel.addNeighbor(this)

  def addUnit(unidad:Unidad): Unit =
    if unidad.panelActual.isDefined && unidad.panelActual.get != this then
      unidad.panelActual.get.quitarUnidad(unidad)
    if !unidades.contains(unidad) then unidades += unidad
    unidad.panelActual = Some(this)

  def quitarUnidad(unidad: Unidad): Unit = {
    unidades -= unidad
    if unidad.panelActual == Some(this) then unidad.panelActual = None
  }

  override def recibirMovimiento(unidad: Unidad): Boolean =
    if unidad.panelActual.isEmpty then
      throw new InvalidMovementException(s"${unidad.nombre} no esta en ningún panel")
    if !vecinos.contains(unidad.panelActual.get) then
      throw new InvalidMovementException(s"${unidad.nombre} solo puede moverse a un panel aledaño")
    unidad.panelActual.get.quitarUnidad(unidad)
    addUnit(unidad)
    true

  override def recibirMeteorito(danioMagico: Int): Boolean =
    for unidad <- unidades.toList do
      if !unidad.estaDerrotada then unidad.recibirDanioMagico(danioMagico)
    true
