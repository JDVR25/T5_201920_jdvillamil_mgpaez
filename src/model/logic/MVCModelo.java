package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import com.opencsv.CSVReader;
import model.data_structures.IEstructura;
import model.data_structures.ListaSencillamenteEncadenada;
import model.data_structures.Nodo;
import model.data_structures.TablaHashLinearProbing;
import model.data_structures.TablaHashSeparateChaining;

/**
 * Definicion del modelo del mundo
 *
 */
public class MVCModelo {
	/**
	 * Atributos del modelo del mundo
	 */
	private ListaSencillamenteEncadenada<TravelTime> dias;

	private TablaHashSeparateChaining<String, TravelTime> separateChaining;
	
	private TablaHashLinearProbing<String, TravelTime> linearProbing;
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public MVCModelo()
	{
		dias = new ListaSencillamenteEncadenada<TravelTime>();
	}

	public void cargarDatos()
	{
		CSVReader reader = null;
		try 
		{
			for(int i = 1; i < 5; i++)
			{
				reader = new CSVReader(new FileReader("./data/bogota-cadastral-2018-"+ i + "-WeeklyAggregate.csv"));
				for(String[] param : reader)
				{
					try
					{
						TravelTime nuevo = new TravelTime(i, Integer.parseInt(param[0]), Integer.parseInt(param[1]), 
								Integer.parseInt(param[2]), Double.parseDouble(param[3]), Double.parseDouble(param[4]),
								Double.parseDouble(param[5]), Double.parseDouble(param[6]));
						dias.addLast(nuevo);
					}
					catch(NumberFormatException e)
					{

					}
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
		return dias.size();
	}

	public TravelTime darPrimerViaje()
	{
		return dias.getFirst();
	}

	public TravelTime darUltimoViaje()
	{
		return dias.getLast();
	}

	public ListaSencillamenteEncadenada<TravelTime> consultarViajesSegunHora(int hour)
	{
		ListaSencillamenteEncadenada<TravelTime> respuesta = new ListaSencillamenteEncadenada<TravelTime>();

		for(TravelTime temp: dias)
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
		TravelTime[] tiempos = (TravelTime[]) dias.toArray();
		int n = dias.size() - 1;
		int indice = 0;
		if(!dias.isEmpty())
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
	
	public void crearTablaLinearProbing()
	{
		
	}
	
	public void crearTablaSeparateChaining()
	{
		
	}

}

