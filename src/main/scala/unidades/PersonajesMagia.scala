package unidades

abstract class PersonajeMagico(nombre: String, HpPoints: Int, DefensePoints: Int, weight: Double, var manaPoints: Double) extends  Personaje(nombre, HpPoints, DefensePoints, weight):
  val maxManaPoints: Double = manaPoints
  private var fuerzaMagicaActiva: Boolean = false

  override def equals(obj: Any): Boolean =
    super.equals(obj) &&
      manaPoints == obj.asInstanceOf[PersonajeMagico].manaPoints

  def tieneFuerzaMagica: Boolean = fuerzaMagicaActiva

  def danioMagico: Int =
    val base = if slotArma.isDefined then slotArma.get.ataqueMagico else 0
    if fuerzaMagicaActiva then base * 3 / 2 else base

  def pagarHechizo(costo: Int): Unit =
    manaPoints = manaPoints - costo
    fuerzaMagicaActiva = false

  override def recibirPocionMana(): Boolean =
    manaPoints = manaPoints + maxManaPoints * 0.3
    if manaPoints > maxManaPoints then manaPoints = maxManaPoints
    true

  override def recibirFuerzaMagica(): Boolean =
    fuerzaMagicaActiva = true
    true