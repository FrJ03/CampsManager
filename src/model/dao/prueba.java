package model.dao;

import java.time.LocalDate;
import java.time.Period;

import view.beans.activity.*;
import view.beans.camp.*;
import view.beans.monitor.*;

public class prueba {

	public static void main(String[] args) {
		CampamentoDAO c = CampamentoDAO.getInstance();
		MonitorDAO a = MonitorDAO.getInstance();
		Monitor mon = new Monitor(1, "Pepe", "Mellado", false);
		LocalDate date =  LocalDate.parse("2025-11-15");

		Campamento cam = new Campamento(1, date, date, Nivel.Adolescente, 3);
		cam.setResponsable(mon);
		cam.setResponsable(mon);
		a.create(mon);
		System.out.println(c.create(cam));
		System.out.println(c.read(1));
		c.delete(cam);
		a.delete(mon);
		
		 LocalDate fecha1 = LocalDate.of(2023, 11, 8);
	       LocalDate fecha2 = LocalDate.of(2023, 12, 12);

        // Calcular la diferencia en días
        Period periodo = Period.between(fecha1, fecha2);
        int diferenciaEnDias = periodo.getDays();

        System.out.println("La diferencia en días es: " + diferenciaEnDias + " días.");
	}
}
