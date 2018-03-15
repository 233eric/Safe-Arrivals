package sort;

/**
 * @author Victor
 * @version Mars.2 Release (4.5.2)
 */
public class Merge {
	
	private static Comparable[] aux;
	/**
	 * bottom-up merge sort using Comparable
	 * @param x - the input array containing jobs that need to be sorted.
	 * @param n - the size of the input array
	 */
	public static void sortMerge (Comparable[] x, int n){
		aux = new Comparable[n];
		for (int len = 1; len < n; len *= 2)
			for (int lo = 0; lo < n-len; lo += len+len)
				merge(x, lo, lo+len-1, Math.min(lo+len+len-1, n-1));
	}
	
	/**
	 * bottom-up merge sort using Comparable
	 * @param x - the input array containing jobs that need to be sorted.
	 * @param lo - the lower bound on the array indices to be merged
	 * @param mid - the middle of the array to be merged
	 * @param hi - the upper bound on the array indices to be merged
	 */
	private static void merge(Comparable[] x, int lo, int mid, int hi){
		int i = lo, j = mid+1;
		
		for (int k = lo; k<= hi; k++)
			aux[k] = x[k];
		
		for (int k = lo; k <= hi; k++)
			if (i > mid)
				x[k] = aux[j++];
			else if (j > hi)
				x[k] = aux[i++];
			else if (less(aux[j], aux[i]))
				x[k] = aux[j++];
			else
				x[k] = aux[i++];
		
	}
	
	/**
	 * compares the element at index i and returns true if x[i] is smaller than x[j]
	 * @param x - the input array containing jobs that need to be sorted.
	 * @param i - the index of one of the element to compare to
	 * @param j - the index of one of the element use as a comparator
	 * @return true if Job v's execution time is shorter than Job w, else false
	 */
	private static boolean less(Comparable v, Comparable w){
		return v.compareTo(w) < 0;
	}

}
