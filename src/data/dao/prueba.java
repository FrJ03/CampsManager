package data.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import business.campamento.*;
import business.monitor.*;
import business.actividad.*;

public class prueba {

	public static void main(String[] args) {
		CampamentoDAO c = CampamentoDAO.getInstance();
		MonitorDAO a = MonitorDAO.getInstance();
		Monitor mon = new Monitor(1, "Pepe", "Mellado", false);
		LocalDate date = LocalDate.now();
		Campamento cam = new Campamento(1, date, date, Nivel.Adolescente, 3);
		cam.setResponsable(mon);
		cam.setResponsable(mon);
		a.create(mon);
		System.out.println(c.create(cam));
		System.out.println(c.readAll());
		c.delete(cam);
		a.delete(mon);
	}
}
