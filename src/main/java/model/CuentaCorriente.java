package model;

public class CuentaCorriente extends CuentaSA {

	private Double comisionMantenimiento;
	private String comisionMensual;
	
	public CuentaCorriente(String titular) {
		super(titular);
		
		comisionMantenimiento = 20.5;
		comisionMensual = "Comision por retirar dinero";
		
	}

	public CuentaCorriente(String titular, Double comisionMantenimiento, String comisionMensual) {
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

	public String getComisionMensual() {
		return comisionMensual;
	}

	public void setComisionMensual(String comisionMensual) {
		this.comisionMensual = comisionMensual;
	}

	@Override
	public String toString() {
		return "CuentaCorriente [comisionMantenimiento=" + comisionMantenimiento + ", comisionMensual="
				+ comisionMensual + "]";
	}

}
