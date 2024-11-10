package backend.academy.algorithms.solvers;

import backend.academy.algorithms.exceptions.NoWayFoundException;
import backend.academy.models.Cell;
import backend.academy.models.Maze;
import java.util.LinkedList;
import java.util.Queue;

public class BFS implements SolverAlgorithm {
    // Класс для представления позиции в лабиринте
    static class Point {
        public int y;
        public int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    @Override
    public void execute(Maze maze) throws NoWayFoundException {
        int height = maze.height();
        int width = maze.width();

        // Массив для отслеживания посещенных ячеек
        boolean[][] visited = new boolean[height][width];
        // Массив для хранения предыдущей позиции для каждой ячейки
        Point[][] prev = new Point[height][width]; // для восстановления пути

        // Поиск позиции START и FINISH
        int startY = -1;
        int startX = -1;
        int finishY = -1;
        int finishX = -1;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Cell cell = maze.getSurface(y, x);
                if (cell == Cell.START) {
                    startY = y;
                    startX = x;
                } else if (cell == Cell.FINISH) {
                    finishY = y;
                    finishX = x;
                }
            }
        }

        if (startY == -1 || startX == -1 || finishY == -1 || finishX == -1) {
            // START или FINISH не найдены
            throw new NoWayFoundException("That is incomplete maze");
        }

        // Инициализация очереди для BFS
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(startY, startX));
        visited[startY][startX] = true;

        boolean found = false;
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            int y = current.y;
            int x = current.x;

            // Проверка, достигли ли мы FINISH
            if (y == finishY && x == finishX) {
                found = true;
                break;
            }

            // Проверка соседних ячеек
            int[] dy = {-1, 1, 0, 0};
            int[] dx = {0, 0, -1, 1};

            for (int dir = 0; dir < 4; dir++) {
                int ny = y + dy[dir];
                int nx = x + dx[dir];

                // Проверка границ лабиринта
                if (ny >= 0 && ny < height && nx >= 0 && nx < width) {
                    Cell neighborCell = maze.getSurface(ny, nx);
                    if (neighborCell != Cell.WALL && !visited[ny][nx]) {
                        visited[ny][nx] = true;
                        prev[ny][nx] = current;
                        queue.add(new Point(ny, nx));
                    }
                }
            }
        }

        if (found) {
            // Восстановление пути от FINISH до START
            Point current = new Point(finishY, finishX);
            while (!(current.y == startY && current.x == startX)) {
                Cell cell = maze.getSurface(current.y, current.x);
                if (cell == Cell.PATHWAY) {
                    // Изменение ячейки на SOLUTION
                    maze.setSurface(current.y, current.x, Cell.SOLUTION);
                }
                current = prev[current.y][current.x];
            }
            return;
        }

        throw new NoWayFoundException("There is no such way");
    }

    public String toString() {
        return "BFS";
    }
}
