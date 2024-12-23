package model;

import java.time.LocalDate;
import java.time.Period;

import controller.ICalculoFechas;

public class CuentaAhorro extends CuentaSA implements ICalculoFechas{

	private Double interesAnual;
	private Double limiteRetiros;
	
	
	public CuentaAhorro(String titular, double debeCuenta, double haberCuenta, LocalDate fechaApertura) {
		super(titular, debeCuenta, haberCuenta, fechaApertura);
		
		this.interesAnual = 150.24;
		this.limiteRetiros = 3000.00;	
	}
	
	public Double getInteresAnual() {
		return interesAnual;
	}

	public Double getLimiteRetiros() {
		return limiteRetiros;
	}


	@Override
	public String toString() {
		return "CuentaAhorro [interesAnual=" + interesAnual + ", limiteRetiros=" + limiteRetiros + ", getTitularCuenta()=" + getTitularCuenta() + ", getDebeCuenta()=" + getDebeCuenta()
				+ ", getHaberCuenta()=" + getHaberCuenta() + ", getSaldo()=" + getSaldo() + "]";
	}
}
