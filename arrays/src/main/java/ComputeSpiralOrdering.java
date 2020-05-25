import java.util.*;

public class ComputeSpiralOrdering {

    /*
    6.17
    */

    /*
    * [ 1 2 3 4
    *   5 6 7 8
    *   4 3 2 1
    *   8 7 6 5 ]
    *
    *  keep constant space for 4 corners
    *  i = 0-indexed iteration, l = matrix len
    *  (0,0) (0,3) (3,3) (3,0)
    *  (1,1) (1,2) (2,2) (2,1)
    * */

    public static List<Integer> matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
        List<Integer> result = new ArrayList<>();
        int span = squareMatrix.size() / 2 + squareMatrix.size() % 2;

        for (int i = 0; i < span; i++) {
            Set<Position> corners = getCorners(squareMatrix, i);
            Set<Position> visited = new HashSet<>(); // visited corners
            result.addAll(printTop(squareMatrix, i, corners, visited));
            result.addAll(printRight(squareMatrix, i, corners, visited));
            result.addAll(printBottom(squareMatrix, i, corners, visited));
            result.addAll(printLeft(squareMatrix, i, corners, visited));
        }

        return result;
    }

    private static int getSpan(List<List<Integer>> matrix, int r) {
        return Math.max(0, matrix.size() - 2*r);
    }

    private static int getCol(int r, int span) {
        return r + span - 1;
    }

    private static Set<Position> getCorners(List<List<Integer>> matrix, int r) {
        int span = getSpan(matrix, r);
        int col = getCol(r, span);
        if (span <= 0) return new HashSet<>(Collections.singletonList(new Position(r, r)));

        Set<Position> corners = new HashSet<>();
        corners.add(new Position(r, r));        // top left
        corners.add(new Position(r, col));      // top right
        corners.add(new Position(col, col));    // bottom right
        corners.add(new Position(col, r));      // bottom left

        return corners;
    }

    private static List<Integer> printTop(List<List<Integer>> matrix, int r, Set<Position> corners, Set<Position> visitedCorners) {
        List<Integer> result = new ArrayList<>();
        int span = getSpan(matrix, r);
        if (span <= 0) return result;

        for (int i = r; i < span; i++) {
            Position p = new Position(r, i);
            if (visitedCorners.contains(p)) continue; // already visited
            if (corners.contains(p)) visitedCorners.add(p);
            result.add(matrix.get(p.x).get(p.y));
        }

        return result;
    }

    private static List<Integer> printRight(List<List<Integer>> matrix, int r, Set<Position> corners, Set<Position> visitedCorners) {
        List<Integer> result = new ArrayList<>();
        int span = getSpan(matrix, r);
        if (span <= 0) return result;
        int col = getCol(r, span);
        for (int i = r; i <= col; i++) {
            Position p = new Position(i, col);
            if (visitedCorners.contains(p)) continue; // already visited
            if (corners.contains(p)) visitedCorners.add(p);
            result.add(matrix.get(p.x).get(p.y));
        }

        return result;
    }

    private static List<Integer> printBottom(List<List<Integer>> matrix, int r, Set<Position> corners, Set<Position> visitedCorners) {
        List<Integer> result = new ArrayList<>();
        int span = getSpan(matrix, r);
        if (span <= 0) return result;
        int col = getCol(r, span);
        int row = matrix.size() - r - 1;
        for (int i = 0; i < span; i++) {
            Position p = new Position(row, col - i);
            if (visitedCorners.contains(p)) continue; // already visited
            if (corners.contains(p)) visitedCorners.add(p);
            result.add(matrix.get(p.x).get(p.y));
        }

        return result;
    }

    private static List<Integer> printLeft(List<List<Integer>> matrix, int r, Set<Position> corners, Set<Position> visitedCorners) {
        List<Integer> result = new ArrayList<>();
        int span = getSpan(matrix, r);
        if (span <= 0) return result;
        int row = matrix.size() - r - 1; // starting row, column is just r and doesn't change
        for (int i = 0; i < span; i++) {
            Position p = new Position(row - i, r);
            if (visitedCorners.contains(p)) continue; // already visited
            if (corners.contains(p)) visitedCorners.add(p);
            result.add(matrix.get(p.x).get(p.y));
        }

        return result;
    }


    private static class Position {
        public int x;
        public int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.x, this.y);
        }

        @Override
        public boolean equals(Object obj) {
            Position other = (Position)obj;
            return this.x == other.x && this.y == other.y;
        }
    }
}
