package model;

public class CuentaCorriente extends CuentaSA {

	private Double comisionMantenimiento;
	private Double comisionMensual;
	
	
	public CuentaCorriente(String titular, double debeCuenta, double haberCuenta, Double comisionMantenimiento, Double comisionMensual) {
		super(titular, debeCuenta, haberCuenta);
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

	
}
