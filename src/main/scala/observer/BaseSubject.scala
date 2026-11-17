package cl.uchile.dcc
package observer
import scala.collection.mutable

/** guarda los observadores sin duplicados y en orden de suscripcion, y reenvia cada notificacion.
 *
 * constructor se hereda. esta proteced
 *
 * @tparam T ,tipo de dato que se notifica
 */
class BaseSubject[T] protected extends Subject[T]:
  private val observers: mutable.Set[Observer[T]] =
    mutable.LinkedHashSet.empty[Observer[T]]

  override def attach(observer: Observer[T]): Unit =
    observers += observer

  override def detach(observer: Observer[T]): Unit =
    observers -= observer

  override def notifyObservers(notification: T): Unit =
    for observer <- observers do observer.update(this, notification)