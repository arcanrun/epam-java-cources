package com.epam.university.java.core.task012;

import java.util.Arrays;
import java.util.Collection;
import java.util.Stack;

public class GraphImpl implements Graph {
    private class Vertex {
        int number;
        boolean isVisited;
    }

    private Vertex[] vertexList;
    private int[][] adjMatrix;
    private int size;
    private Stack<Integer> stack;

    /**
     * Constructor for Graph.
     *
     * @param vertexCount
     */

    public GraphImpl(int vertexCount) {
        stack = new Stack<>();
        vertexList = new Vertex[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            vertexList[i] = new Vertex();
        }
        adjMatrix = new int[vertexCount][vertexCount];
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix.length; j++) {
                adjMatrix[i][j] = 0;
            }
        }
    }

    private int getAdjUnvisitedVertex(int vertex) {
        for (int i = 0; i < adjMatrix.length; i++) {
            if (adjMatrix[vertex][i] == 1 && !vertexList[i].isVisited) {
                return i;
            }
        }
        return -1;
    }

    private void dfs(int from) {
        for (int i = 0; i < size; i++) {
            vertexList[i].isVisited = false;
        }
        vertexList[from].isVisited = true;
        stack.push(from);
        while (!stack.isEmpty()) {
            int v = getAdjUnvisitedVertex(stack.peek());
            if (v == -1) {
                stack.pop();
            } else {
                vertexList[v].isVisited = true;
                stack.push(v);
            }
        }

    }

    public boolean isPathExist(int from, int to) {
        dfs(from - 1);
        for (int i = 0; i < vertexList.length; i++) {
            Vertex vertex = vertexList[i];
            if ((i == (to - 1)) && vertex.isVisited) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void createEdge(int from, int to) {
        adjMatrix[from - 1][to - 1] = 1;
        adjMatrix[to - 1][from - 1] = 1;
    }

    @Override
    public boolean edgeExists(int from, int to) {
        return adjMatrix[from - 1][to - 1] == 1 || adjMatrix[to - 1][from - 1] == 1;
    }

    @Override
    public void removeEdge(int from, int to) {
        adjMatrix[from - 1][to - 1] = 0;
        adjMatrix[to - 1][from - 1] = 0;
    }

    @Override
    public Collection<Integer> getAdjacent(int from) {
        return null;
    }
}
