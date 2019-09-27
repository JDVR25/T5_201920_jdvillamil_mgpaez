package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import com.opencsv.CSVReader;
import model.data_structures.IEstructura;
import model.data_structures.ListaSencillamenteEncadenada;
import model.data_structures.MaxColaCP;
import model.data_structures.MaxHeapCP;
import model.data_structures.Nodo;

/**
 * Definicion del modelo del mundo
 *
 */
public class MVCModelo {
	/**
	 * Atributos del modelo del mundo
	 */
	private ListaSencillamenteEncadenada<TravelTime> horas;

	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public MVCModelo()
	{
		horas = new ListaSencillamenteEncadenada<TravelTime>();
	}

	public void cargarDatos()
	{
		CSVReader reader = null;
		try 
		{
			reader = new CSVReader(new FileReader("./data/bogota-cadastral-2018-1-All-HourlyAggregate.csv"));
			for(String[] param : reader)
			{
				try
				{
					TravelTime nuevo = new TravelTime(Integer.parseInt(param[0]), Integer.parseInt(param[1]), 
							Integer.parseInt(param[2]), Double.parseDouble(param[3]), Double.parseDouble(param[4]),
							Double.parseDouble(param[5]), Double.parseDouble(param[6]));
					horas.addLast(nuevo);
				}
				catch(NumberFormatException e)
				{

				}
			}

			reader = new CSVReader(new FileReader("./data/bogota-cadastral-2018-2-All-HourlyAggregate.csv"));
			for(String[] param : reader)
			{
				try
				{
					TravelTime nuevo = new TravelTime(Integer.parseInt(param[0]), Integer.parseInt(param[1]), 
							Integer.parseInt(param[2]), Double.parseDouble(param[3]), Double.parseDouble(param[4]),
							Double.parseDouble(param[5]), Double.parseDouble(param[6]));
					horas.addLast(nuevo);
				}
				catch(NumberFormatException e)
				{

				}
			}

		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (reader != null)
			{
				try
				{
					reader.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}

		}
	}

	public int darNumViajes()
	{
		return horas.size();
	}

	public TravelTime darPrimerViaje()
	{
		return horas.getFirst();
	}

	public TravelTime darUltimoViaje()
	{
		return horas.getLast();
	}

	public ListaSencillamenteEncadenada<TravelTime> consultarViajesSegunHora(int hour)
	{
		ListaSencillamenteEncadenada<TravelTime> respuesta = new ListaSencillamenteEncadenada<TravelTime>();

		for(TravelTime temp: horas)
		{
			if(temp.darHoraOMesODia() == hour && temp.darIDOrigen() == 4 && temp.darIdDestino() == 5)
			{
				respuesta.addLast(temp);
			}
		}

		return respuesta;
	}

	public ListaSencillamenteEncadenada<TravelTime> generarMuestra(int tamano)
	{
		ListaSencillamenteEncadenada<TravelTime> lista = new ListaSencillamenteEncadenada<TravelTime>();
		TravelTime[] tiempos = (TravelTime[]) horas.toArray();
		int n = horas.size() - 1;
		int indice = 0;
		if(!horas.isEmpty())
		{
			for(int i = 0; i < tamano; i++)
			{
				indice = (int) (Math.random()*n);
				TravelTime tiempo = tiempos[indice];
				boolean yaEsta = false;
				Iterator<TravelTime> it = lista.iterator();
				while(it.hasNext() && !yaEsta)
				{
					TravelTime temp = it.next(); 
					if(temp.compareTo(tiempo) == 0)
					{
						yaEsta = true;
					}
				}
				if(!yaEsta)
				{
					lista.addLast(tiempo);
				}
			}
		}
		return lista;
	}

	public MaxColaCP<TravelTime> crearMaxColaCP (int hInicial, int hFinal)
	{
		MaxColaCP<TravelTime> respuesta = new MaxColaCP<TravelTime>();
		for(TravelTime temp: horas)
		{
			if(temp.darHoraOMesODia() >= hInicial && temp.darHoraOMesODia() <= hFinal)
			{
				respuesta.agregar(temp);
			}
		}
		return respuesta;
	}

	public MaxHeapCP<TravelTime> crearMaxHeapCP (int hInicial, int hFinal)
	{
		MaxHeapCP<TravelTime> respuesta = new MaxHeapCP<TravelTime>();
		for(TravelTime temp: horas)
		{
			if(temp.darHoraOMesODia() >= hInicial && temp.darHoraOMesODia() <= hFinal)
			{
				respuesta.agregar(temp);
			}
		}
		return respuesta;
	}
}

