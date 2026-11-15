package utilizables
import acciones.AbstractTarget
import unidades.Personaje


abstract class Arma(override val nombre: String, val attackPoints: Int, val weight: Double, prop: Personaje) extends AbstractTarget,Utilizable:
  private var _owner: Personaje = prop
  def owner:Personaje= _owner

  override def equals(obj: Any): Boolean =
    if getClass == obj.getClass then
      val other = obj.asInstanceOf[Arma]
      nombre == other.nombre &&
        attackPoints == other.attackPoints &&
        weight == other.weight &&
        owner == other.owner
    else false

  def ataqueMagico: Int = 0


  def equipablePorCaballero: Boolean = false

  
  def equipablePorArquero: Boolean = false

 
  def equipablePorLadron: Boolean = false

 
  def equipablePorMagoNegro: Boolean = false

 
  def equipablePorMagoBlanco: Boolean = false

 
  override def equiparseEn(personaje: Personaje): Boolean =
    if personaje.puedeEquipar(this) then
      personaje.slotArma = Some(this)
      _owner = personaje
      true
    else false

