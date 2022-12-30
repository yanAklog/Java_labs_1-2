import java.util.Arrays;
import java.util.Random;

public class ImmutableMatrix
{
    private int r;
    private int c;
    private double[][] array;


    public ImmutableMatrix()
    {
        r = 0;
        c = 0;
        array = new double[0][0];
    }

    public ImmutableMatrix(int r, int c)
    {
        this.r = r;
        this.c = c;
        array = new double[r][c];
    }

    public ImmutableMatrix(ImmutableMatrix matrix)
    {
        r = matrix.r;
        c = matrix.c;
        array = new double[r][c];

        for(int i=0; i<r; i++){
            array[i] = Arrays.copyOf(matrix.array[i], matrix.array[i].length);
        }

    }

    public ImmutableMatrix(Matrix matrix)
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



    public ImmutableMatrix setRandomMatrix()
    {
        var n_matrix = new ImmutableMatrix(r, c);

        Random v;
        v = new Random();

        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                n_matrix.array[i][j] = Math.round(v.nextDouble(12) * 100.0)/100.0;
            }
        }

        return n_matrix;
    }

    public ImmutableMatrix setElement(int r, int c, double el)
    {
        var n_matrix = new ImmutableMatrix(this.r, this.c);

        for (int i=0; i<this.r; i++){
            n_matrix.array[i] = Arrays.copyOf(array[i], array[i].length);
        }

        try
        {
            n_matrix.array[r][c] = el;
            return n_matrix;
        }
        catch (Exception e)
        {
            throw new IndexOutOfBoundsException("Index is out of bounds!");
        }
    }

    public ImmutableMatrix setMatrix(double[][] arr)
    {
        var n_matrix = new ImmutableMatrix(r, c);

        if (r == arr.length && c == arr[0].length)
        {
            for (int i = 0; i<r; i++){
                n_matrix.array[i] = Arrays.copyOf(arr[i], arr[i].length);
            }
            return n_matrix;
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
        double[] column;
        column = new double[r];

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
        System.out.println();
    }


    @Override public boolean equals(Object otherObject)
    {
        if (this == otherObject) return true;

        //if(otherObject == null) return false;

        if(getClass() != otherObject.getClass()) return false;

        ImmutableMatrix other = (ImmutableMatrix) otherObject;

        return Arrays.deepEquals(array, other.array);
    }

    @Override public int hashCode()
    {
        return Arrays.deepHashCode(array) + 1000;
    }





    public static ImmutableMatrix getVec(double[] vec)
    {
        var vec_matrix = new ImmutableMatrix(vec.length, vec.length);

        for(int i=0; i<vec.length; i++) vec_matrix.array[i][i] = vec[i];

        return vec_matrix;
    }





    public ImmutableMatrix getCofactor(int excl_r, int excl_c)
    {
        ImmutableMatrix cofactor = new ImmutableMatrix(r-1, c-1);

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


    public ImmutableMatrix getInverse()
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
                ImmutableMatrix inverse = new ImmutableMatrix(r, c);

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
