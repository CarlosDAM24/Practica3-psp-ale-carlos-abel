package model;

public class CuentaAhorro extends CuentaSA{

	private Double interesAnual;
	private Double limiteRetiros;
	
	public CuentaAhorro(String titular, double debeCuenta, double haberCuenta) {
		super(titular, debeCuenta, haberCuenta);
		
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
		return "CuentaAhorro [interesAnual=" + interesAnual + ", limiteRetiros=" + limiteRetiros
				+ ", getTitularCuenta()=" + getTitularCuenta() + ", getDebeCuenta()=" + getDebeCuenta()
				+ ", getHaberCuenta()=" + getHaberCuenta() + ", getSaldo()=" + getSaldo() + "]";
	}

}
