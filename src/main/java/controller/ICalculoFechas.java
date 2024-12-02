package controller;

import java.time.LocalDate;

public interface ICalculoFechas {

	// Verifica si han pasado al menos un cierto numero de meses desde la apertura
	boolean hanPasadoMeses(LocalDate fechaApertura, int meses);
	
	// Verifica si han pasado al menos un cierto numero de años desde la apertura
	boolean hanPasadoAnios(LocalDate fechaApertura, int anios);
	
}
