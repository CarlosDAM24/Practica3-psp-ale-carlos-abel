package model;

public class CuentaAhorro extends CuentaSA{

	private Double interesAnual;
	private Double limiteRetiros;
	
	public CuentaAhorro(String titular, double debeCuenta, double haberCuenta, Double interesAnual, Double limiteRetiros) {
		super(titular, debeCuenta, haberCuenta);
		
		interesAnual = 150.24;
		limiteRetiros = 3000.00;
		
	}
	
	public Double getInteresAnual() {
		return interesAnual;
	}

	public Double getLimiteRetiros() {
		return limiteRetiros;
	}

	
	
}
