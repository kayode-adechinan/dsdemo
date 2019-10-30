package datastructure;

// Java program to illustrate creating an array
// of integers, puts some values in the array
// and print each value to standard output

class MyArray {
    public static void main(String[] args) {
        // declares an array of integers
        int[] arr;

        // allocating memory for 5 integers
        arr = new int[5];

        // initialize the first elements of the array
        arr[0] = 10;

        // initialize the second elements of the array
        arr[1] = 20;

        // so on ...
        arr[2] = 30;
        arr[3] = 40;
        arr[4] = 50;

        // accessing the elements of the specified array

        for(int i = 0; i < arr.length; i++){
            System.out.println("Element at index " + i + " : " + arr[i]);
        }
    }
}



class Array1D {

    void traversal(int[] A) {
        for (int i = 0; i < A.length; i++) {
            System.out.println(A[i]);
        }
    }

    public static void main(String[] args) {
        int[] A = {9, 2, 6, 8};
        new Array1D().traversal(A);
    }

}

class Array2D {
    void traversal(int[][] A) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                System.out.println(A[i][j]);
            }
        }
    }

    public static void main(String[] args) {
        int[][] A = {{9, 2, 6, 8}, {5, 7, 1, 3}};
        new Array2D().traversal(A);
    }
}