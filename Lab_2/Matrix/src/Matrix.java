import java.util.Arrays;
import java.util.Random;

public class Matrix
{
    private int r;
    private int c;
    private double[][] array;


    public Matrix()
    {
        r = 0;
        c = 0;
        array = new double[0][0];
    }

    public Matrix(int r, int c)
    {
        this.r = r;
        this.c = c;
        array = new double[r][c];
    }

    public Matrix(Matrix matrix)
    {
        r = matrix.r;
        c = matrix.c;
        array = new double[r][c];

        for(int i=0; i<r; i++){
            array[i] = Arrays.copyOf(matrix.array[i], matrix.array[i].length);
        }

    }

    public Matrix(ImmutableMatrix matrix)
    {
        r = matrix.getDim()[0];
        c = matrix.getDim()[1];
        array = new double[r][c];

        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                array[i][j] = matrix.getElement(i, j);
            }
        }

    }


    public void setRandomMatrix()
    {
        Random v = new Random();

        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                array[i][j] = Math.round(v.nextDouble(12) * 100.0)/100.0;
            }
        }

    }

    public void setElement(int r, int c, double el)
    {
        try
        {
            array[r][c] = el;
        }
        catch (Exception e)
        {
            throw new IndexOutOfBoundsException("Index is out of bounds!");
        }
    }

    public void setMatrix(double[][] arr)
    {
        if (r == arr.length && c == arr[0].length)
        {
            for (int i = 0; i<r; i++){
                array[i] = Arrays.copyOf(arr[i], arr[i].length);
            }
        }

        else
        {
            throw new IndexOutOfBoundsException("Index is out of bounds!");
        }
    }


    public double getElement(int r, int c)
    {
        try
        {
            return array[r][c];
        }
        catch (Exception e)
        {
            throw new IndexOutOfBoundsException("Index is out of bounds!");
        }
    }

    public String getRow(int r)
    {
        try
        {
            return Arrays.toString(array[r]);
        }
        catch (Exception e)
        {
            throw new IndexOutOfBoundsException("Index is out of bounds!");
        }
    }

    public String getColumn(int c)
    {
        double[] column = new double[r];

        try
        {
            for(int i=0; i<r; i++){
                column[i] = array[i][c];
            }

            return Arrays.toString(column);
        }
        catch (Exception e)
        {
            throw new IndexOutOfBoundsException("Index is out of bounds!");
        }
    }


    public int[] getDim()
    {
//        return Arrays.toString(new int[]{r, c});
        return new int[]{r, c};
    }


    public void print()
    {
        for (int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                System.out.print(getElement(i, j) + " ");
            }
            System.out.println();
        }
        System.out.println("\n");
    }


    @Override public boolean equals(Object otherObject)
    {
        if (this == otherObject) return true;

        //if(otherObject == null) return false;

        if(getClass() != otherObject.getClass()) return false;

        Matrix other = (Matrix) otherObject;

        return Arrays.deepEquals(array, other.array);
    }

    @Override public int hashCode()
    {
        return Arrays.deepHashCode(array);
    }





    public static Matrix getVec(double[] vec)
    {
        var vec_matrix = new Matrix(vec.length, vec.length);

        for(int i=0; i<vec.length; i++) vec_matrix.array[i][i] = vec[i];

        return vec_matrix;
    }





    public Matrix getCofactor(int excl_r, int excl_c)
    {
        Matrix cofactor = new Matrix(r-1, c-1);

        for(int row = 0, row_cof = 0; row < r; row++){
            if (row != excl_r){
                for(int col = 0, col_cof = 0; col < c; col++){
                    if (col != excl_c){
//                        System.out.print(row_cof);
                        cofactor.array[row_cof][col_cof] = array[row][col];

                        col_cof ++;
                    }
                }

                row_cof ++;
            }
        }

        return cofactor;
    }


    public double determinant()
    {
        if (r == 1) return array[0][0];

        else if (r == 2) {
            return (array[0][0] * array[1][1] - array[0][1] * array[1][0]);
        }

        else{
            double det = 0.0;

            for(int row = 0; row < r; row++){

                det += (Math.pow(-1, row + 1 + 1) * array[row][0] * (getCofactor(row, 0).determinant()));

            }

            return det;
        }

    }


    public Matrix getInverse()
    {
        if (r != c)
        {
            System.out.println("No inverse matrix for non-square matrix.");
            return null;
        }

        else
        {
            double det = determinant();

            if (det == 0){
                System.out.println("Determinant = 0 - no inverse matrix.");
                return null;
            }

            else
            {
                Matrix inverse = new Matrix(r, c);

                for (int row = 0; row < r; row++){
                    for(int col = 0; col < c; col++){

                        inverse.array[col][row] = ((1.0 / det) * Math.pow(-1, row + col) * (getCofactor(row, col).determinant()));
                    }
                }

                return inverse;
            }
        }
    }







}
