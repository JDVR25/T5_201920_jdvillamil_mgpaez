package model.data_structures;

import java.util.Iterator;

public interface IHashTable<K, V>
{
	void put(K key, V value);
	
	V get(K key);
	
	V delete(K key);
	
	Iterator<K> keys();
	
	void putInSet(K key, V value);
	
	Iterator<V> getSet(K key);
	
	Iterator<V> deleteSet(K key);
}
