package model;

public class CuentaCorriente extends CuentaSA {

	private Double comisionMantenimiento;
	private Double comisionMensual;
	
	public CuentaCorriente(String titular) {
		super(titular);
		
		comisionMantenimiento = 20.5;
		comisionMensual = 250.0;
		
	}

	public CuentaCorriente(String titular, Double comisionMantenimiento, Double comisionMensual) {
		super(titular);
		this.comisionMantenimiento = comisionMantenimiento;
		this.comisionMensual = comisionMensual;
	}

	public Double getComisionMantenimiento() {
		return comisionMantenimiento;
	}

	public void setComisionMantenimiento(Double comisionMantenimiento) {
		this.comisionMantenimiento = comisionMantenimiento;
	}

	public Double getComisionMensual() {
		return comisionMensual;
	}

	public void setComisionMensual(Double comisionMensual) {
		this.comisionMensual = comisionMensual;
	}

	@Override
	public String toString() {
		return "CuentaCorriente [comisionMantenimiento=" + comisionMantenimiento + ", comisionMensual="
				+ comisionMensual + "]";
	}

}
