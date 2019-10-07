package model.data_structures;

public class NodoST<K, V> extends Nodo<V> {

	private K llave;
	
	public NodoST(K key, V elemento) {
		super(elemento);
		
		llave = key;
	}

	public K darLlave()
	{
		return llave;
	}
	
	public void cambiarSiguienteST(NodoST<K, V> siguiente)
	{
		this.siguiente = siguiente;
	}
}
