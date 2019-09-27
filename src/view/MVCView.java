package view;

import model.logic.MVCModelo;

public class MVCView 
{
	    /**
	     * Metodo constructor
	     */
	    public MVCView()
	    {
	    	
	    }
	    
		public void printMenu()
		{
			System.out.println("1. Cargar los viajes de UBER");
			System.out.println("2. Prueba con 200000 datos");
			System.out.println("3. Consultar tiempos de viaje promedios mas largos en un rango de horas");
			System.out.println("4. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
		public void printModelo(MVCModelo modelo)
		{
			// TODO implementar
		}
		
}
