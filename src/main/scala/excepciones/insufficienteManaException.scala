package cl.uchile.dcc
package excepciones

/**Lanza si no hay mana suficiente
 *
 * @param message por que accion fallo
 * @param cause
 *
 * */

class insufficienteManaException(message:String, cause: Throwable=null) extends InvalidActionException(message, cause)