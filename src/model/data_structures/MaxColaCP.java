package model.data_structures;

public class MaxColaCP<T extends Comparable<T>>
{
	private T[] elements;

	private int size;
	
	private int length ; 

	public MaxColaCP()
	{
		length = 10000;
		size = 0;
		elements = (T[]) new Object[length];
	}
	
	public int darNumElementos()
	{
		return size; 
	}
	
	public void agregar(T elemento)
	{
		if (size < (elements.length / 2) )
		{
			length = length * 2; 
		}
		boolean added = false;
		for (int i = 0; i < elements.length && !added ; i++)
		{
			if (elements[i].compareTo(elemento) >= 0 && elements[i + 1] == null)
			{
				elements[i + 1] = elemento; 
				size++; 
				added = true;
			}
			else if (elements[i].compareTo(elemento) >= 0 && elements[i + 1].compareTo(elemento) <=0 )
			{
				 T[] elementsTemp = (T[]) new Object[length];
				 int a = 0;
				 for (int j = i + 1; j < elements.length; j++)
				 {
					 elementsTemp[a] = elements[j];
				 }
				 
				elements[i + 1] = elemento; 
				size++;
				a = 0;
				for (int b = i + 2; b < elements.length; b++)
				{
					elements[b] =  elementsTemp[a];
				} 
				added = true;
			}
		}
	}
	
	public T sacarMax ()
	{
		T[] elementsTemp = (T[]) new Object[length];
		T max = elements[0];
		 int a = 0;
		 for (int j = 1; j < elements.length; j++)
		 {
			 elementsTemp[a] = elements[j];
		 }
		 
		 elements = elementsTemp;
		 
		 
		 return max;
	}
	
	public T darMax() 
	{
		return elements[0];
	}
	
	public boolean esVacia () 
	{
		if (size != 0)
		{
			return false;
		}
		else
		{
			return true; 
		}
	}
}
