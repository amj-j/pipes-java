package sk.amjj.model.creators;

import java.util.ArrayList;
import java.util.Random;

import lombok.Getter;
import sk.amjj.model.data.Board;
import sk.amjj.model.data.Pipe;
import sk.amjj.model.enums.Side;
import sk.amjj.universalStructs.Coords;

public class RandCreator implements ICreator {
    Random rand = new Random();
    Board board;
    boolean[][] tiles;
    Side startSide;
    Side finishSide;
    Coords start;
    Coords finish;

    public Board createBoard(int rows, int cols) {
        this.board = new Board(rows, cols);
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
        ArrayList<Pipe> route = board.getRoute();
        route.get(0).addSide(startSide);
        for (int i = 1; i < board.getRoute().size(); i++) {
            route.get(i).addSide(startSide);
            for (Neighbour neighbour : Neighbour.values()) {
                Coords neighbourCoords = neighbour.getNeighbourPos(route.get(i).getPos());
                if (route.get(i).getPos().equals(neighbourCoords)) {
                    route.get(i).addSide(neighbour.getSide());
                    route.get(i-1).addSide(neighbour.getSide().opposite());
                }
            }
        }
        route.get(route.size()-1).addSide(finishSide);
    }

    private void randomRotatePipes() {
        for (Pipe pipe : board.getRoute()) {
            int reps = rand.nextInt(Side.values().length);
            for (int i = 0; i < reps; i++) {
                pipe.rotate(true);
            }
        }
    }

    @Getter
    private enum Neighbour {
        NORTH(0, -1, Side.UP),
        SOUTH(0, 1, Side.BOTTOM),
        EAST(1, 0, Side.RIGHT),
        WEST(-1, 0, Side.LEFT);

        private final int x;
        private final int y;
        private final Side side;

        Neighbour(int x, int y, Side side) {
            this.x = x;
            this.y = y;
            this.side = side;
        }

        public Coords getNeighbourPos(Coords baseCoords) {
            Coords neighbourCoords = new Coords(baseCoords);
            neighbourCoords.moveX(this.x);
            neighbourCoords.moveY(this.y);
            return neighbourCoords;
        }
    }
}
