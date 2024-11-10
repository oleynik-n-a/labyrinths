package backend.academy.algorithms.generators;

import backend.academy.models.Cell;
import backend.academy.models.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Kruskal implements GeneratorAlgorithm {
    public void execute(Maze maze) {
        int height = maze.height();
        int width = maze.width();

        UnionFind uf = new UnionFind();

        for (int y = 1; y < height; y += 2) {
            for (int x = 1; x < width; x += 2) {
                maze.setSurface(y, x, Cell.PATHWAY);
                uf.makeSet(y, x);
            }
        }

        List<Wall> walls = new ArrayList<>();

        for (int y = 1; y < height; y += 2) {
            for (int x = 1; x < width; x += 2) {
                if (y + 2 < height) {
                    walls.add(new Wall(y + 1, x, y, x, y + 2, x));
                }
                if (x + 2 < width) {
                    walls.add(new Wall(y, x + 1, y, x, y, x + 2));
                }
            }
        }

        for (Wall wall : walls) {
            maze.setSurface(wall.y, wall.x, Cell.WALL);
        }

        Collections.shuffle(walls);

        for (Wall wall : walls) {
            String root1 = uf.find(wall.cell1Y, wall.cell1X);
            String root2 = uf.find(wall.cell2Y, wall.cell2X);

            if (!root1.equals(root2)) {
                uf.union(wall.cell1Y, wall.cell1X, wall.cell2Y, wall.cell2X);
                maze.setSurface(wall.y, wall.x, Cell.PATHWAY); // Убираем стену
            }
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (maze.getSurface(y, x) == null) {
                    maze.setSurface(y, x, Cell.WALL);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Kruskal";
    }

    private static final class Wall {
        int y;
        int x;
        int cell1Y;
        int cell1X;
        int cell2Y;
        int cell2X;

        Wall(int y, int x, int cell1Y, int cell1X, int cell2Y, int cell2X) {
            this.y = y;
            this.x = x;
            this.cell1Y = cell1Y;
            this.cell1X = cell1X;
            this.cell2Y = cell2Y;
            this.cell2X = cell2X;
        }
    }


    private static final class UnionFind {
        private final Map<String, String> parent = new HashMap<>();

        public void makeSet(int y, int x) {
            String cell = y + "," + x;
            parent.put(cell, cell);
        }

        public String find(int y, int x) {
            String cell = y + "," + x;
            if (!parent.containsKey(cell)) {
                parent.put(cell, cell);
            }
            String root = parent.get(cell);
            if (!root.equals(cell)) {
                String[] coords = root.split(",");
                int rootY = Integer.parseInt(coords[0]);
                int rootX = Integer.parseInt(coords[1]);
                root = find(rootY, rootX);
                parent.put(cell, root); // Сжатие пути
            }
            return root;
        }

        public void union(int y1, int x1, int y2, int x2) {
            String root1 = find(y1, x1);
            String root2 = find(y2, x2);
            if (!root1.equals(root2)) {
                parent.put(root1, root2);
            }
        }
    }
}
