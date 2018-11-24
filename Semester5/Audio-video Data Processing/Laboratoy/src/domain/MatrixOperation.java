package domain;

class MatrixOperation {
    static double[][] divideMatrixes(double[][] A, double[][] B) {
        double[][] result = new double[8][8];
        double aux;
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                aux = A[i][j] / B[i][j];
                if (aux < 0)
                    result[i][j] = Math.ceil(aux);
                else
                    result[i][j] = Math.floor(aux);
            }

        return result;
    }

    static double[][] multiplyMatrixes(double[][] A, double[][] B) {
        double[][] result = new double[8][8];

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                result[i][j] = A[i][j] * B[i][j];

        return result;
    }


}
