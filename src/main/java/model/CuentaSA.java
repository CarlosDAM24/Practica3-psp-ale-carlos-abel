package model;

import controller.ESaldoNoValido;

/**
 * Esta clase representa estructuras de tipo
 * cuenta bancaria.
 *
 * Cada objeto de esta clase contendra las
 * siguientes variables:
 * 	- Nombre del titular (tipo String)
 *	- Cantidad DEBE (tipo entero)
 *	- Cantidad HABER (tipo entero)
 */

public class CuentaSA {
   private String titularCuenta;
   private double    debeCuenta;
   private double    haberCuenta;

   
      /** Constructor basico          *
        *  - Requiere necesariamente  *
        *    un titular               */
   public CuentaSA(String titular, double debeCuenta, double haberCuenta) {
      titularCuenta = titular;
      debeCuenta    = 0;
      haberCuenta   = 0;
   }

   
   public CuentaSA(String titular, int imposicionInicial)  {
      titularCuenta = titular;
      debeCuenta    = 0;
      haberCuenta   = imposicionInicial;
   }

   
   public double getDebe () {
      return debeCuenta;
   }

   
   public double getHaber () {
      return haberCuenta;
   }

   
      /** Devuelve el saldo */
   public double getSaldo () {
      return haberCuenta - debeCuenta;
   }


      /* Metodo Imposicion */
   public boolean Imposicion(int cantidad) {
	haberCuenta += cantidad;
	return true;
   }

   
      /* Metodo Reintegro */
   public void Reintegro(int cantidad) throws ESaldoNoValido{
	if (getSaldo() >= cantidad) {
		debeCuenta += cantidad;
	} else
		throw new ESaldoNoValido("Error: saldo Negativo");
   }


@Override
public String toString() {
	return "CuentaSA [titularCuenta=" + titularCuenta + ", debeCuenta=" + debeCuenta + ", haberCuenta=" + haberCuenta
			+ "]";
}

}


/******** Fin de CuentaSA.java ***************/
