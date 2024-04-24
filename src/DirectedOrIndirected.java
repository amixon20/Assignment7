public class DirectedOrIndirected {

    public static boolean isDirected(int[][] matrix) {
        int n = matrix.length;

        if (n != matrix[0].length) {
            return false;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (i == j && matrix[i][j] != 0) {
                    return false;
                }

                if (matrix[i][j] != 0 && matrix[j][i] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
       int[][] matrix = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        if (isDirected(matrix)) {
            System.out.println("The matrix represents a directed graph.");
        } else {
            System.out.println("The matrix does not represent a directed graph.");
        }
    }
}

