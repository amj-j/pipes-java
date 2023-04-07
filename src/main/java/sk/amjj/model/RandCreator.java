package sk.amjj.model;

import java.util.ArrayList;
import java.util.Random;

import sk.amjj.enums.Side;

public class RandCreator implements ICreator {
    Random rand = new Random();
    BoardInfo board;
    boolean[][] tiles;
    Side startSide;
    Side finishSide;
    Coords start;
    Coords finish;

    public BoardInfo createBoard(int rows, int cols) {
        this.board = new BoardInfo(rows, cols);
        tiles = new boolean[cols][rows];
        setStartAndFinish();
        setRoute();
        setPipesOnRoute();
        randomRotatePipes();
        return this.board;
    }

    public void setStartAndFinish() {
        startSide = Side.values()[rand.nextInt(Side.values().length)];
        finishSide = startSide.opposite();
        this.start = getRandBorderCoords(startSide);
        this.finish = getRandBorderCoords(finishSide);
    }

    private Coords getRandBorderCoords(Side side) {
        if (side == Side.UP) {
            return new Coords(rand.nextInt(board.getCols()), 0);
        }
        else if (side == Side.RIGHT) {
            return new Coords(board.getCols()-1, rand.nextInt(board.getRows()));
        }
        else if (side == Side.BOTTOM) {
            return new Coords(rand.nextInt(board.getCols()), board.getRows()-1);
        }
        else if (side == Side.LEFT) {
            return new Coords(0, rand.nextInt(board.getRows()));
        }
        else {
            return null;
        }
    }

    public void setRoute() {
        board.getRoute().add(new Pipe(start));
        randomizedDFS(start);
    }

    private boolean randomizedDFS(Coords tile) {
        tiles[tile.getX()][tile.getY()] = true;
        if (tile.equals(finish)) {
            return true;
        }
        Coords nextTile = getUnvisitedNeighbour(tile);
        while (nextTile != null) {
            board.getRoute().add(new Pipe(nextTile));
            boolean finished = randomizedDFS(nextTile);
            if (finished) {
                return true;
            }            
            nextTile = getUnvisitedNeighbour(tile);
        }
        board.getRoute().remove(board.getRoute().size()-1);
        return false;
    }

    private Coords getUnvisitedNeighbour(Coords tile) {
        ArrayList<Coords> unvisitedNeighbours = getUnvisitedNeighbours(tile);
        if (unvisitedNeighbours.isEmpty()) {
            return null;
        }
        int randIndex = rand.nextInt(unvisitedNeighbours.size());
        return unvisitedNeighbours.get(randIndex);
    }

    private ArrayList<Coords> getUnvisitedNeighbours(Coords tile) {
        ArrayList<Coords> list = new ArrayList<>();
        for (Neighbour neighbour : Neighbour.values()) {
            Coords neighbourTile = new Coords(tile.getX() + neighbour.getX(), tile.getY() + neighbour.getY());
            try {
                if (tiles[neighbourTile.getX()][neighbourTile.getY()] == false) {
                    list.add(neighbourTile);
                }
            }
            catch (IndexOutOfBoundsException e) { continue; }
        }
        return list;
    }

    private void setPipesOnRoute() {
        board.getRoute().get(0).addSide(startSide);
        for (int i = 0; i < board.getRoute().size()-1; i++) {
            //TODO
        }
        board.getRoute().get(board.getRoute().size()-1).addSide(finishSide);
    }

    private void randomRotatePipes() {

    }

    private enum Neighbour {
        NORTH(0, -1),
        SOUTH(0, 1),
        EAST(1, 0),
        WEST(-1, 0);

        private final int x;
        private final int y;

        Neighbour(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
