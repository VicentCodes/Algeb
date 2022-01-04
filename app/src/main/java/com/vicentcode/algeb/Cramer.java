package com.vicentcode.algeb;



public class Cramer {

    public static double valorX(double[][] m) {
        return detX(m) / detM(m);
    }

    public static double valorY(double[][] m) {
        return detY(m) / detM(m);
    }

    public static double valorZ(double[][] m) {
        return detZ(m) / detM(m);
    }

    public static double detM(double[][] m) {

        double[][] mS = new double[3][5];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {

                if (j < 3)
                    mS[i][j] = m[i][j];

                if (j == 3 || j == 4)
                    mS[i][j] = m[i][j - 3];
            }
        }
        double del = (mS[0][0] * mS[1][1] * mS[2][2] + mS[0][1] * mS[1][2] * mS[2][0] + mS[0][2] * mS[1][0] * mS[2][1])
                - (mS[0][2] * mS[1][1] * mS[2][0] + mS[0][0] * mS[1][2] * mS[2][1] + mS[0][1] * mS[1][0] * mS[2][2]);

        return del;
    }

    private static double detX(double[][] m) {

        double[][] mX = new double[3][3];

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {

                if (j == 0)
                    mX[i][j] = m[i][3];

                else
                    mX[i][j] = m[i][j];
            }
        return detM(mX);
    }

    private static double detY(double[][] m) {

        double[][] mY = new double[3][3];

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {

                if (j == 1)
                    mY[i][j] = m[i][3];

                else
                    mY[i][j] = m[i][j];

            }
        return detM(mY);
    }

    private static double detZ(double[][] m) {

        double[][] mZ = new double[3][3];

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {

                if (j == 2)
                    mZ[i][j] = m[i][3];

                else
                    mZ[i][j] = m[i][j];

            }
        return detM(mZ);
    }
}
