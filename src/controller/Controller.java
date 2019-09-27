package controller;

import java.util.Scanner;

import model.data_structures.ListaSencillamenteEncadenada;
import model.data_structures.MaxColaCP;
import model.data_structures.MaxHeapCP;
import model.data_structures.Nodo;
import model.logic.MVCModelo;
import model.logic.TravelTime;
import view.MVCView;

public class Controller {

	/* Instancia del Modelo*/
	private MVCModelo modelo;

	/* Instancia de la Vista*/
	private MVCView view;


	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new MVCView();
		modelo = new MVCModelo();
	}

	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
			case 1:
				System.out.println("--------- \nSe cargaran los datos: ");
				modelo = new MVCModelo(); 
				modelo.cargarDatos();
				System.out.println("Datos cargados");
				System.out.println("Numero de viajes cargados: " + modelo.darNumViajes() );
				break;

			case 2:
				System.out.println("--------- \nSe realizara una prueba con 200000 datos");
				try
				{
					int n = Integer.parseInt(dato);
					ListaSencillamenteEncadenada<TravelTime> muestra = modelo.generarMuestra(200000);
					
					MaxHeapCP<TravelTime> heap = new MaxHeapCP<TravelTime>();
					double tiempo = 0;
					long tInicial = System.currentTimeMillis();
					for(TravelTime temp: muestra)
					{
						heap.agregar(temp);
					}
					long tFinal = System.currentTimeMillis();
					tiempo = tFinal - tInicial;
					System.out.println("El tiempo de agregar para el MaxHeapCP fue de " + tiempo);
					
					MaxColaCP<TravelTime> cola = new MaxColaCP<TravelTime>();
					tiempo = 0;
					tInicial = System.currentTimeMillis();
					for(TravelTime temp: muestra)
					{
						cola.agregar(temp);
					}
					tFinal = System.currentTimeMillis();
					tiempo = tFinal - tInicial;
					System.out.println("El tiempo de agregar para el MaxColaCP fue de " + tiempo);
					
					tiempo = 0;
					tInicial = System.currentTimeMillis();
					while(!heap.esVacia())
					{
						heap.sacarMax();
					}
					tFinal = System.currentTimeMillis();
					tiempo = tFinal - tInicial;
					System.out.println("El tiempo de sacarMax para el MaxHeapCP fue de " + tiempo);
					
					tiempo = 0;
					tInicial = System.currentTimeMillis();
					while(!cola.esVacia())
					{
						cola.sacarMax();
					}
					tFinal = System.currentTimeMillis();
					tiempo = tFinal - tInicial;
					System.out.println("El tiempo de sacarMax para el MaxColaCP fue de " + tiempo);
				}
				catch(NumberFormatException e)
				{
					System.out.println("Debe ingresar los datos como numeros enteros");
				}
				break;

			case 3:
				System.out.println("--------- \nIngrese la cantidad de tiempos que desea consultar");
				dato = lector.next();
				try
				{
					int n = Integer.parseInt(dato);
					
					System.out.println("--------- \nIngrese la hora inicial");
					dato = lector.next();
					int hInicial = Integer.parseInt(dato);
					
					System.out.println("--------- \nIngrese la hora final");
					dato = lector.next();
					int hFinal = Integer.parseInt(dato);
					
					System.out.println("--------- \nRespuesta usando heap");
					double tiempo = 0;
					long tInicial = System.currentTimeMillis();
					MaxHeapCP<TravelTime> heap = modelo.crearMaxHeapCP(hInicial, hFinal);
					while(!heap.esVacia())
					{
						TravelTime temp = heap.sacarMax();
						System.out.println(temp.darTiempoViaje());
					}
					long tFinal = System.currentTimeMillis();
					tiempo = tFinal - tInicial;
					System.out.println("El tiempo total para la operacion usando MaxHeapCp fue " + tiempo);
					
					System.out.println("--------- \nRespuesta usando cola");
					tiempo = 0;
					tInicial = System.currentTimeMillis();
					MaxColaCP<TravelTime> cola = modelo.crearMaxColaCP(hInicial, hFinal);
					while(!cola.esVacia())
					{
						TravelTime temp = cola.sacarMax();
						System.out.println(temp.darTiempoViaje());
					}
					tFinal = System.currentTimeMillis();
					tiempo = tFinal - tInicial;
					System.out.println("El tiempo total para la operacion usando MaxColaCp fue " + tiempo);
				}
				catch(NumberFormatException e)
				{
					System.out.println("Debe ingresar los datos como numeros enteros");
				}
				break;

			case 4: 
				System.out.println("--------- \n Hasta pronto !! \n---------"); 
				lector.close();
				fin = true;
				break; 

			default: 
				System.out.println("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}

	}	
}
