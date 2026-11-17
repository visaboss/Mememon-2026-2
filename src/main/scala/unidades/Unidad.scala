package cl.uchile.dcc
package unidades
import acciones.{Source, Target}
import mapajuego.Panel

import cl.uchile.dcc.observer.Subject
//T esta tiene el mismo-ish nombre que el nombre de carpeta, por lo que de aqui sale
//T todo el resto,
//T dealmente como de aqui va a salir todo lo demas, es muy,general para ser ocupado  entonces tiene que ser trait
//mas adelante, en otros files dentro de esta misma carpeta, cuando nos empecemos a ir mas por las ramas,
//y bajesmos de clases abstractas, podemos empezar a implementar clases


//notar que no es necesario que argumento de package igual a el del file pero es convencion


//notar que unidad igual a nombre de file, pero esto es solo convencion
trait Unidad extends Source, Target, Subject[Unidad]:
  val nombre:String
  var hpPoints: Int
  var defensePoints: Int
  var weight:Double
  var panelActual: Option[Panel]
  def barraAccion : Double

  /**
   * @return true si puntos de vida no positivos
   * */
  def estaDerrotada: Boolean

  def recibirDanioMagico(danio: Int): Unit
