package cl.uchile.dcc
package excepciones

/** Lannza cuando unidad sin puntos de vido, intenta actuar o si recibe una accion
 *
 * @param message motivo de por que accion fallo
 * @param cause
 *
 *
 * */

class defeatedUnitException(message:String,cause:Throwable=null) extends InvalidActionException(message, cause)