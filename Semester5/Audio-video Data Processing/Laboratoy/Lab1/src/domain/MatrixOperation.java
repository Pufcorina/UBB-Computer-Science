package domain;

class MatrixOperation {
    static double[][] divideMatrixes(double[][] A, double[][] B) {
        double[][] result = new double[8][8];

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                result[i][j] = A[i][j] / B[i][j];

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
