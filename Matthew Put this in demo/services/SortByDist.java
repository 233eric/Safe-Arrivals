package services;
import java.util.ArrayList;

public class SortByDist {
	
	private static Intersection[] aux;
	private static Intersection dest;
	/**
	 * bottom-up merge sort using Intersection
	 * @param x - the input array containing jobs that need to be sorted.
	 * @param n - the size of the input array
	 */
	public static void sortMerge (ArrayList<Intersection> x, Intersection des){
		dest = des;
		int n = x.size();
		aux = new Intersection[n];
		for (int len = 1; len < n; len *= 2)
			for (int lo = 0; lo < n-len; lo += len+len)
				merge(x, lo, lo+len-1, Math.min(lo+len+len-1, n-1));
	}
	
	/**
	 * bottom-up merge sort using Intersection
	 * @param x - the input array containing jobs that need to be sorted.
	 * @param lo - the lower bound on the array indices to be merged
	 * @param mid - the middle of the array to be merged
	 * @param hi - the upper bound on the array indices to be merged
	 */
	private static void merge(ArrayList<Intersection> x, int lo, int mid, int hi){
		int i = lo, j = mid+1;
		
		for (int k = lo; k<= hi; k++)
			aux[k] = x.get(k);
		
		for (int k = lo; k <= hi; k++)
			if (i > mid)
				x.set(k, aux[j++]);
			else if (j > hi)
				x.set(k, aux[i++]);
			else if (less(aux[j], aux[i]))
				x.set(k, aux[j++]);
			else
				x.set(k, aux[i++]);
		
	}
	
	/**
	 * compares the element at index i and returns true if x[i] is smaller than x[j]
	 * @param x - the input array containing jobs that need to be sorted.
	 * @param i - the index of one of the element to compare to
	 * @param j - the index of one of the element use as a comparator
	 * @return true if Intersection v's execution time is shorter than Intersection w, else false
	 */
	private static boolean less(Intersection v, Intersection w){
		return v.compareBy(w,dest) < 0;
	}

}
