package cl.uchile.dcc
package excepciones

/**
 *
 * @param message motivo de por que fallo
 * @param cause
 *
 * */

class missingWeaponException(message:String,cause:Throwable=null) extends InvalidActionException(message, cause)