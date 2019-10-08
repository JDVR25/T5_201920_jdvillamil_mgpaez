package model.data_structures;

import java.util.Iterator;

//Codigo basado en "https://github.com/kevin-wayne/algs4/blob/master/src/main/java/edu/princeton/cs/algs4/SeparateChainingHashST.java"
public class TablaHashSeparateChaining <K extends Comparable<K>, V>
{

	private static final int INIT_CAPACITY = 7;

	//Parejas de llaves
	private int n;

	//Tamaño
	private int m;

	private TableSimbolos<K, V>[] tablaSimbolos;

	public TablaHashSeparateChaining()
	{
		this(INIT_CAPACITY);
	} 

	/**
	 * Initializes an empty symbol table with {@code m} chains.
	 * @param m the initial number of chains
	 */
	public TablaHashSeparateChaining(int tam)
	{
		m = tam;
		tablaSimbolos = (TableSimbolos<K, V>[]) new TableSimbolos[m];
		for (int i = 0; i < m; i++)
			tablaSimbolos[i] = new TableSimbolos<K, V>();
	} 

	private void resize(int chains)
	{
		TablaHashSeparateChaining<K, V> temp = new TablaHashSeparateChaining<K, V>(chains);
		for (int i = 0; i < m; i++)
		{
			for (K key : tablaSimbolos[i].keys())
			{
				temp.put(key, tablaSimbolos[i].get(key));
			}
		}
		m  = temp.m;
		n  = temp.n;
		tablaSimbolos = temp.tablaSimbolos;
	}

	private int hash(K key)
	{
		return (key.hashCode() & 0x7fffffff) % m;
	} 

	public int size()
	{
		return n;
	} 

	public boolean isEmpty()
	{
		return size() == 0;
	}

	public boolean contains(K key)
	{
		boolean respuesta = false;
		if (key != null)
			respuesta = get(key) != null;
		return respuesta;
	} 

	public V get(K key)
	{
		V buscado = null;
		if (key != null)
		{
			int i = hash(key);
			buscado =  tablaSimbolos[i].get(key);
		}
		return buscado;
	} 

	public int siguientePrimo(int actual)
	{
		int respuesta = actual;
		boolean encontrado = false;
		if(actual == 2)
		{
			respuesta++;
			encontrado = true;
		}
		else if(actual < 2)
		{
			respuesta = 2;
			encontrado = true;
		}
		else
		{
			respuesta = respuesta + 2;
		}
		while(!encontrado)
		{
			boolean esPrimo = true;
			for(int i=2; i * i <=respuesta && esPrimo; i++)
			{
				if( respuesta % i == 0 )
				{
					esPrimo = false;
				}
			}
			if(esPrimo)
				encontrado = true;
			if(!encontrado)
				respuesta = respuesta + 2;
		}
		return respuesta;
	}

	/**
	 * Inserts the specified key-value pair into the symbol table, overwriting the old 
	 * value with the new value if the symbol table already contains the specified key.
	 * Deletes the specified key (and its associated value) from this symbol table
	 * if the specified value is {@code null}.
	 *
	 * @param  key the key
	 * @param  val the value
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public void put(K key, V val)
	{
		if (key != null)
		{
			if (val == null)
			{
				delete(key);
			}
			else
			{
				if(n >= 5*m)
					resize(siguientePrimo(m));
				int i = hash(key);
				if (!tablaSimbolos[i].contains(key))
					n++;
				tablaSimbolos[i].put(key, val);
			}
		}
	} 

	/**
	 * Removes the specified key and its associated value from this symbol table     
	 * (if the key is in this symbol table).    
	 *
	 * @param  key the key
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public void delete(K key) {
		if (key != null)
		{
			int i = hash(key);
			if (tablaSimbolos[i].contains(key))
				n--;
			tablaSimbolos[i].delete(key);
		}
	} 

	public Iterator<K> keys()
	{
		Iterator<K> respuesta = null;
		ListaSencillamenteEncadenada<K> lista = new ListaSencillamenteEncadenada<K>();
		for (int i = 0; i < m; i++) {
			for (K key : tablaSimbolos[i].keys())
				lista.addLast(key);
		}
		respuesta = lista.iterator();
		return respuesta;
	}
}
