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

	public void cargarDatos(int i)
	{
		CSVReader reader = null;
		try 
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
	
	public TablaHashSeparateChaining<String, TravelTime> darSeparateChaining()
	{
		return separateChaining;
	}
	
	public TablaHashLinearProbing<String, TravelTime> darLinearProbing()
	{
		return linearProbing;
	}

	//TODO Pendiente, recuerda poner lo necesario para poder llenar la tabla de cargar datos
	public void crearTablaLinearProbing()
	{

	}

	public void crearTablaSeparateChaining()
	{
		separateChaining = new TablaHashSeparateChaining<String, TravelTime>();
		for(TravelTime temp: dias)
		{
			separateChaining.putInSet(temp.darTrimestre() + "-" + temp.darIDOrigen() + "-" + temp.darIdDestino(), temp);
		}
	}
	
	public Iterator<TravelTime> buscarTiemposDeViajeSeparateChaining(int trimestre, int zonaOrigen, int zonaDestino)
	{
		return separateChaining.getSet(trimestre + "-" + zonaOrigen + "-" + zonaDestino);
	}
	
	//TODO pendiente
	public Iterator<TravelTime> buscarTiemposDeViajeLinearProbing(int trimestre, int zonaOrigen, int zonaDestino)
	{
		return null;
	}
	
	public double[] pruebaSeparateChaining()
	{
		double[] respuesta = new double[3];
		int trimestre = (int) (Math.random()*4);
		int zonaOrigen = (int) (Math.random()*1500);
		int zonaDestino = (int) (Math.random()*1500);
		int noExis = 0;
		int siExist = 0;
		double min = 999999999;
		double acumulado = 0;
		double max = 0;
		while(noExis < 2000 && siExist < 8000)
		{
			//TODO terminar, recordar usar system.getTimeinmilis
			String llave = trimestre + "-" + zonaOrigen + "-" + zonaDestino;
			if(separateChaining.contains(llave))
			{
				
			}
			else
			{
				
			}
			trimestre = (int) (Math.random()*4);
			zonaOrigen = (int) (Math.random()*1500);
			zonaDestino = (int) (Math.random()*1500);
		}
		respuesta[0] = min;
		respuesta[1] = acumulado/10000;
		respuesta[2] = max;
		return respuesta;
	}
}

