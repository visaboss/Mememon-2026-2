package cl.uchile.dcc
package observer

import acciones.Atacar
import jugadores.{Jugador, Player}
import unidades.{Caballero, Enemigo, Unidad}
import utilizables.Espada


class ObservadorDeUnidades extends Observer[Unidad]:
  var notificaciones: Int = 0
  var ultima: Option[Unidad] = None

  override def update(subject: Subject[Unidad], response: Unidad): Unit =
    notificaciones += 1
    ultima = Some(response)


class ObservadorDeJugadores extends Observer[Player]:
  var notificaciones: Int = 0
  var ultimo: Option[Player] = None

  override def update(subject: Subject[Player], response: Player): Unit =
    notificaciones += 1
    ultimo = Some(response)

class ObserverTest extends munit.FunSuite:
  var knight: Caballero = null
  var goblin: Enemigo = null
  var orco: Enemigo = null
  var attack: Atacar = null
  var player: Jugador = null
  var obsUnidad: ObservadorDeUnidades = null
  var obsPlayer: ObservadorDeJugadores = null

  override def beforeEach(context: BeforeEach): Unit =
    knight = new Caballero("Arthur", 100, 15, 70)
    knight.slotArma = Some(new Espada("Excalibur", 50, 5, knight))
    goblin = new Enemigo("Goblin", 30, 10, 30, 20)   // 50 - 10 = 40 de dano: cae de un golpe
    orco = new Enemigo("Orco", 80, 10, 30, 20)       // cae en dos golpes
    attack = new Atacar("Atacar")
    player = new Jugador("Enemigos")
    obsUnidad = new ObservadorDeUnidades()
    obsPlayer = new ObservadorDeJugadores()

  test("unidad avisa a observador cuando derrotada"):
    goblin.attach(obsUnidad)
    knight.doAction(attack, goblin)
    assert(obsUnidad.notificaciones == 1)
    assert(obsUnidad.ultima == Some(goblin))

  test("unidad que sobrevive no avisa"):
    orco.attach(obsUnidad)
    knight.doAction(attack, orco)
    assert(orco.hpPoints == 40)
    assert(obsUnidad.notificaciones == 0)

  test("unidad deattach no recibe aviso"):
    goblin.attach(obsUnidad)
    goblin.detach(obsUnidad)
    knight.doAction(attack, goblin)
    assert(obsUnidad.notificaciones == 0)

  test("attach dos veces el mismo observador no duplica aviso"):
    goblin.attach(obsUnidad)
    goblin.attach(obsUnidad)
    knight.doAction(attack, goblin)
    assert(obsUnidad.notificaciones == 1)

  test("unidad derrotada avisa una vez"):
    goblin.attach(obsUnidad)
    knight.doAction(attack, goblin)
    goblin.recibirDanioMagico(10)
    assert(goblin.hpPoints == 0)
    assert(obsUnidad.notificaciones == 1)

  test("jugador tiene nombre, y por defecto se llama Jugador"):
    assert(player.nombre == "Enemigos")
    assert(new Jugador().nombre == "Jugador")

  test("jugador no avisa si tiene unidades vivas"):
    player.agregarUnidad(goblin)
    player.agregarUnidad(orco)
    player.attach(obsPlayer)
    knight.doAction(attack, goblin)
    assert(!player.derrotado)
    assert(obsPlayer.notificaciones == 0)

  test("jugador avisa cuando cae ultima unidad"):
    player.agregarUnidad(goblin)
    player.agregarUnidad(orco)
    player.attach(obsPlayer)
    knight.doAction(attack, goblin)
    knight.doAction(attack, orco)
    knight.doAction(attack, orco)
    assert(player.derrotado)
    assert(obsPlayer.notificaciones == 1)
    assert(obsPlayer.ultimo == Some(player))

  test("jugador deattached no recibe aviso"):
    player.agregarUnidad(goblin)
    player.attach(obsPlayer)
    player.detach(obsPlayer)
    knight.doAction(attack, goblin)
    assert(obsPlayer.notificaciones == 0)

  test("attach dos veces el mismo observador a jugador no duplica el aviso"):
    player.agregarUnidad(goblin)
    player.attach(obsPlayer)
    player.attach(obsPlayer)
    knight.doAction(attack, goblin)
    assert(obsPlayer.notificaciones == 1)