package main.java;

import java.util.List;

public class Analyzer {
    public static class Node {
        public int cost = 0;
        public boolean fromDiagonal = false;
        public boolean fromVertical = false;
        public boolean fromHorizontal = false;

        public Node(final int cost) {
            this.cost = cost;
        }
    }

    public AnalogyInfo computeAnalogy(List<String> source, List<String> target) {
        final Node[][] matrix = new Node[source.size() + 1][target.size() + 1];
        for (int i = 0; i <= source.size(); i++) {
            for (int j = 0; j <= target.size(); j++) {
                matrix[i][j] = new Node(0);
            }
        }

        for (int i = 1; i <= source.size(); i++) {
            matrix[i][0].cost = matrix[i - 1][0].cost + 1;
            matrix[i][0].fromHorizontal = true;
            matrix[i][0].fromVertical = false;
            matrix[i][0].fromDiagonal = false;
        }

        for (int j = 1; j <= target.size(); j++) {
            matrix[0][j].cost = matrix[0][j - 1].cost + 1;
            matrix[0][j].fromHorizontal = false;
            matrix[0][j].fromVertical = true;
            matrix[0][j].fromDiagonal = false;
        }

        for (int i = 1; i <= source.size(); i++) {
            for (int j = 1; j <= target.size(); j++) {
                boolean eq = source.get(i-1).equals(target.get(j-1));
                final int diagonal = matrix[i - 1][j - 1].cost + (eq ? 0 : 100);
                final int horizontal = matrix[i - 1][j].cost + 1;
                final int vertical = matrix[i][j - 1].cost + 1;
                final int min = Math.min(diagonal, Math.min(vertical, horizontal));

                final Node node = matrix[i][j];
                node.fromHorizontal = false;
                node.fromVertical = false;
                node.fromDiagonal = false;
                node.cost = min;
                if (min == horizontal) {
                    node.fromHorizontal = true;
                }
                if (min == vertical) {
                    node.fromVertical = true;
                }
                if (min == diagonal) {
                    node.fromDiagonal = true;
                }
            }
        }

        return FindAnalogies.find(matrix, source.size(), target.size());
    }
}
