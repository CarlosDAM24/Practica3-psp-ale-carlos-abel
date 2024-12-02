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

   
   
   public String getTitularCuenta() {
	return titularCuenta;
}


	public void setTitularCuenta(String titularCuenta) {
		this.titularCuenta = titularCuenta;
	}
   
      public double getDebeCuenta() {
		return debeCuenta;
	}


	public void setDebeCuenta(double debeCuenta) {
		this.debeCuenta = debeCuenta;
	}


	public double getHaberCuenta() {
		return haberCuenta;
	}


	public void setHaberCuenta(double haberCuenta) {
		this.haberCuenta = haberCuenta;
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
			+ ", getSaldo()=" + getSaldo() + "]";
}

}


/******** Fin de CuentaSA.java ***************/
