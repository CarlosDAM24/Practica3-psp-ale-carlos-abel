package model;

public class CuentaCorriente extends CuentaSA {

	private Double comisionMantenimiento;
	private Double comisionMensual;
	
	
	public CuentaCorriente(String titular, double debeCuenta, double haberCuenta) {
		super(titular, debeCuenta, haberCuenta);
		
		this.comisionMantenimiento = 150.0;
		this.comisionMensual = 147.8;
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
				+ comisionMensual + ", getTitularCuenta()=" + getTitularCuenta() + ", getDebeCuenta()="
				+ getDebeCuenta() + ", getHaberCuenta()=" + getHaberCuenta() + ", getSaldo()=" + getSaldo() + "]";
	}
}
