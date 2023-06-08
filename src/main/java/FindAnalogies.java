package main.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class FindAnalogies {
    static AnalogyInfo find(Analyzer.Node[][] matrix, int sourceSize, int targetSize) {
        return find0(matrix, sourceSize, targetSize, new AnalogyInfo());
    }

    static AnalogyInfo find0(Analyzer.Node[][] matrix, int i, int j, AnalogyInfo info) {
        if (i == 0 && j == 0) {
            return info;
        }

        final Analyzer.Node n = matrix[i][j];
        if (n.fromVertical) {
            return find0(matrix, i, j - 1, info);
        } else if (n.fromHorizontal) {
            return find0(matrix, i - 1, j, info);
        } else if (n.fromDiagonal) {
            info.add(i - 1);
            return find0(matrix, i - 1, j - 1, info);
        }
        assert false;
        return null;
    }
}
