package model.data_structures;

//Codigo basado en "https://github.com/kevin-wayne/algs4/blob/master/src/main/java/edu/princeton/cs/algs4/SequentialSearchST.java"
public class TableSimbolos<K extends Comparable<K>, V>
{
	private int n;
	private NodoST<K, V> primero; 

	public TableSimbolos()
	{
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
		if(key != null)
		{
			respuesta = get(key) != null;
		}
		return respuesta;
	}

	public V get(K key)
	{
		V buscado = null;
		if (key != null)
		{
			NodoST<K, V> temp = primero;
			boolean encontrado = false;
			while(temp != null && !encontrado)
			{
				if (key.equals(temp.darLlave()))
				{
					buscado = temp.darElemento();
					encontrado = true;
				}
				temp = (NodoST<K, V>) temp.darSiguiente();
			}
		}

		return buscado;
	}

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
				NodoST<K, V> temp = primero;
				boolean encontrado = false;
				while(temp != null && !encontrado)
				{
					if (key.equals(temp.darLlave()))
					{
						temp.cambiarElemento(val);
						encontrado = true;
					}
					temp = (NodoST<K, V>) temp.darSiguiente();
				}
				if(!encontrado)
				{
					NodoST<K,V> nuevo = new NodoST<K,V>(key, val);
					nuevo.cambiarSiguienteST(primero);
					primero = nuevo;
					n++;
				}
			}
		}
	}

	public void delete(K key) {
		if (key != null) 
			primero = delete(primero, key);
	}

	private NodoST<K, V> delete(NodoST<K, V> actual, K key)
	{
		NodoST<K, V> respuesta = actual;
		if (actual != null)
		{
			if (key.equals(actual.darLlave()))
			{
				n--;
				respuesta = (NodoST<K, V>) actual.darSiguiente();
			}
			else
			{
				actual.cambiarSiguienteST(delete((NodoST<K, V>)actual.darSiguiente(), key));
			}
		}
		return respuesta;
	}

	public Iterable<K> keys()  {
		ListaSencillamenteEncadenada<K> lista = new ListaSencillamenteEncadenada<K>();
		NodoST<K, V> temp = primero;
		while(temp != null)
		{
			lista.addLast(temp.darLlave());
			temp = (NodoST<K, V>) temp.darSiguiente();
		}
		return lista;
	}

}
