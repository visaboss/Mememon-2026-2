package unidades

import utilizables.Espada
import unidades.Enemigo

class BarraAccionTest extends munit.FunSuite:
  var caballero: Caballero = null
  var enemigo :Enemigo = null
  
  override def beforeEach(context: BeforeEach): Unit =
    caballero = new Caballero("A", 100, 15, 70)
    enemigo = new Enemigo("B", 40, 10, 30, 20)
    
  test("personaje sin arma tiene barraccion igual a su peso"):
    assertEquals(caballero.barraAccion, 70.0)
    
  test("Personaje con arma suma mitad peso de arma"):
    caballero.slotArma = Some(new Espada("C", 25,8, caballero))
    assertEquals(caballero.barraAccion, 70.0 +0.5*8)
    
  test("Enemigo tiene barraAccion igual a su peso"):
    assertEquals(enemigo.barraAccion, 30.0)
  
  