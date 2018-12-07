package domain;

class MatrixOperation {
    static int[][] divideMatrixes(int[][] A, double[][] B) {
        int[][] result = new int[8][8];
        double aux;
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                aux = A[i][j] / B[i][j];
                if (aux < 0)
                    result[i][j] = (int) Math.ceil(aux);
                else
                    result[i][j] = (int) Math.floor(aux);
            }

        return result;
    }

    static int[][] multiplyMatrixes(int[][] A, double[][] B) {
        int[][] result = new int[8][8];

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                result[i][j] = (int) (A[i][j] * B[i][j]);

        return result;
    }


}
