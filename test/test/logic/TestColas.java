package test.logic;

import java.util.ArrayList;

import junit.framework.TestCase;
import model.data_structures.MaxColaCP;
import model.data_structures.MaxHeapCP;
import model.logic.TravelTime; 
/**
 *@Test
 */
public class TestColas extends TestCase 
{
	
   ArrayList<TravelTime> casosPrueba = new ArrayList<TravelTime>();
   
   TravelTime travel1 = new TravelTime(0000, 0124, 11, 20, 3.9,69.5, 2.3);
   
   TravelTime travel2 = new TravelTime(0001, 0123, 15, 30, 3.8,61.5, 2.2);
   
   TravelTime travel3 = new TravelTime(0002, 0125, 8, 10, 3.7,6.5, 3.7);
   
   TravelTime travel4 = new TravelTime(0104, 0133, 7, 5, 3.6,6.51, 2.8);
   
   TravelTime travel5 = new TravelTime(800, 0223, 4, 5, 3.4,65, 2.95);
   
   @SuppressWarnings("rawtypes")
   MaxColaCP testCola = new MaxColaCP();
   
   @SuppressWarnings("rawtypes")
   MaxHeapCP testHeap = new  MaxHeapCP(); 
   
   testCola.agregar(travel1);
   testCola.agregar(travel2);
   testCola.agregar(travel3);
   testCola.agregar(travel4);
   testCola.agregar(travel5);
   
   testHeap.agregar(travel1);
   testHeap.agregar(travel2);
   testHeap.agregar(travel3);
   testHeap.agregar(travel4);
   testHeap.agregar(travel5);
   
   
   
}