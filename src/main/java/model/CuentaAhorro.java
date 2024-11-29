package model;

public class CuentaAhorro extends CuentaSA{

	private Double interesAnual;
	private Double limiteRetiros;
	
	public CuentaAhorro(String titular) {
		super(titular);
		
		interesAnual = 150.24;
		limiteRetiros = 3000.00;
		
	}

	public CuentaAhorro(String titular, Double interesAnual, Double limiteRetiros) {
		super(titular);
		this.interesAnual = interesAnual;
		this.limiteRetiros = limiteRetiros;
	}

	public Double getInteresAnual() {
		return interesAnual;
	}

	public Double getLimiteRetiros() {
		return limiteRetiros;
	}

	@Override
	public String toString() {
		return "CuentaAhorro [interesAnual=" + interesAnual + ", limiteRetiros=" + limiteRetiros + "]";
	}
	
}
