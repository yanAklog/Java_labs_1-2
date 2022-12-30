import java.util.Arrays;

public class MatrixTest
{
    public static void main(String[] args)
    {
        System.out.println("Hello");
//        Matrix A = new Matrix(5, 4);
//        A.setRandomMatrix();

//        Matrix B = new Matrix(A);

//        System.out.println(A.getElement(2, 2));
//        System.out.println(B.getElement(2, 2));

//        A.setElement(2, 2, -1515);

//        System.out.println(A.getElement(2, 2));
//        System.out.println(B.getElement(2, 2));
//
//        System.out.println(A.getRow(3));
//        System.out.println(B.getRow(1));

//        System.out.println(A.hashCode());
//        System.out.println(B.hashCode());
//
//        System.out.println(A.equals(B));
//        System.out.println(B.equals(A));
//        Matrix A = new Matrix();
//        A.getRow(0);







//        System.out.println(A.r);
//        System.out.println(B.r);
//
//        A.r = 100;
//
//        System.out.println(A.r);
//        System.out.println(B.r);
//
//        System.out.println(Arrays.toString(A.array[0]));
//        System.out.println(Arrays.toString(B.array[0]));
//
//        A.array[0][0] = 500;
//
//        System.out.println(Arrays.toString(A.array[0]));
//        System.out.println(Arrays.toString(B.array[0]));
//
//        System.out.println(A.getDim());
//        System.out.println(B.getDim());

//        double[][] a = {{1, 2, 3}, {4, 5, 6}, {7, 8 ,9}};
//        var A = new Matrix(4, 3);
//        A.setMatrix(a);
//        System.out.println(A.getRow(1));


//        var B = new ImmutableMatrix(3, 3);
//        System.out.println(B.getRow(2));
//        var C = B.setElement(2, 2, 5000);
//        System.out.println(C.getRow(2));
//        System.out.println(B.getRow(2));

//        var A = new Matrix(3, 3);
//        A.setRandomMatrix();
//        A.print();
//        var B = new ImmutableMatrix(A);
//        B.print();
//        A.setElement(1, 1, 10000);
//        A.print();
//        B.print();
//
//        double[][] ab = {{1, 2, 3}, {4, 5, 6}, {7, 8 ,9}};
//
//        var C = new Matrix(3, 3);
//        C.setMatrix(ab);
//
//        var D = new Matrix(3, 3);
//        D.setMatrix(ab);
//
//        C.print();
//        D.print();
//
//        ab[1][1] = 50000;
//
//        C.print();
//        D.print();

//        var A = new Matrix(3, 3);
//        A.setRandomMatrix();
//
//        var B = new ImmutableMatrix(A);
//
//        System.out.println(A.hashCode());
//        System.out.println(B.hashCode());


//        var A = new Matrix(3, 3);
//        double[][] ab = {{1, 7, -15, 18, 20}, {2, 3, 12, -17, 3}, {0, 9 ,11, 11, 7}, {-8, 0, 2, 11, 13}, {5, -15, 6, 9, 1}};
//
//        double[][] ba = {{3, 2, 0.5}, {5, 7, 4}, {0.4, 2, 1.1}};
//        A.setMatrix(ba);
//
//        A.print();
//
//        A.getInverse().print();

//        double[] ba = {-0.5, 1, 12, 11, 87, -0.99};
//        var A = Matrix.getVec(ba);
//        A.print();
//
//        A.setElement(3, 3, 99);
//        A.print();

//        var A = new ImmutableMatrix(3, 3);
//
//        var B = A.setMatrix(ba);
//
//        var C = B.getInverse();
//
//        B.print();
//        C.print();
//
//        B.setElement(2, 2, 1998);
//
//        B.print();
//        C.print();

//        double[] ba = {-0.5, 1, 12, 11, 87, -0.99};
//        var A = ImmutableMatrix.getVec(ba);
//        A.print();
//
//        ba[1] = 999;
//
//        A.print();
//
//        var B = new ImmutableMatrix(2, 2);
//        double[][] da = {{1, 2}, {3, 7}};
//        var C = B.setMatrix(da);
//        C.getInverse().print();

        var A = new Matrix(2, 3);
        A.setRandomMatrix();
        A.print();
        var B = new Matrix(A);
        B.print();
        B.setElement(0, 0, -100);
        B.print();
        A.print();

        var C = new ImmutableMatrix(B);

        C.print();

        C.setElement(0, 0, -200);

        C.print();

        var D = new Matrix(4, 4);
        double[][] da = {{1, 0, 0, 0}, {1, 1, 0, 0}, {0, 0, 1, 1}, {0, 0, 0, 1}};
        D.setMatrix(da);
        System.out.println(D.determinant());




    }
}
