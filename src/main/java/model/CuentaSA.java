package model;

import java.time.LocalDate;
import java.time.Period;

import controller.ESaldoNoValido;
import controller.ICalculoFechas;

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

public class CuentaSA implements ICalculoFechas {
   private String titularCuenta;
   private double    debeCuenta;
   private double    haberCuenta;
   private LocalDate fechaApertura;

   
      /** Constructor basico          *
        *  - Requiere necesariamente  *
        *    un titular               */
   public CuentaSA(String titular, double debeCuenta, double haberCuenta) {
      titularCuenta = titular;
      this.debeCuenta = debeCuenta;
      this.haberCuenta = haberCuenta;
      this.fechaApertura = LocalDate.now();
   }

   
   public CuentaSA(String titular, int imposicionInicial)  {
      titularCuenta = titular;
      debeCuenta    = 0;
      haberCuenta   = imposicionInicial;
   }

   @Override
	public boolean hanPasadoMeses(LocalDate fechaApertura, int meses) {
		
		LocalDate actual = LocalDate.now();
		Period periodo = Period.between(fechaApertura, actual);
	
		return periodo.toTotalMonths() >= meses;
	}

	@Override
	public boolean hanPasadoAnios(LocalDate fechaApertura, int anios) {
		
		LocalDate actual = LocalDate.now();
		Period periodo = Period.between(fechaApertura, actual);
		
		return false;
	}
	
	
	public LocalDate getFechaApertura() {
		return fechaApertura;
		
	}
	
	
	public void setFechaApertura(LocalDate fechaApertura) {
		this.fechaApertura = fechaApertura;
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
