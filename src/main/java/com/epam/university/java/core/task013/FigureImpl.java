package com.epam.university.java.core.task013;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FigureImpl implements Figure {
    private List<Vertex> vertexList;
    private int vertexCount;

    public FigureImpl(int vertexCount) {
        this.vertexCount = vertexCount;
        vertexList = new ArrayList<>(vertexCount);
    }

    @Override
    public void addVertex(Vertex vertex) {
        vertexList.add(vertex);
    }

    @Override
    public Collection<Vertex> getVertexes() {
        return vertexList;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    /**
     * JavaDoc.
     */

    public boolean checkConvexity() {
        sortVertices();
        if (vertexList.size() < 3) {
            return false;
        }
        Vertex p;
        Vertex v;
        Vertex u;
        int res = 0;
        for (int i = 0; i < vertexList.size(); i++) {
            p = vertexList.get(i);
            Vertex tmp = vertexList.get((i + 1) % vertexList.size());
            v = new VertexImpl();
            v.setX(tmp.getX() - p.getX());
            v.setY(tmp.getY() - p.getY());
            u = vertexList.get((i + 2) % vertexList.size());

            if (i == 0) {
                res = u.getX() * v.getY() - u.getY()
                        * v.getX() + v.getX() * p.getY() - v.getY() * p.getX();
            } else {
                int newres = u.getX() * v.getY() - u.getY()
                        * v.getX() + v.getX() * p.getY() - v.getY() * p.getX();
                if ((newres > 0 && res < 0) || (newres < 0 && res > 0)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * JavaDoc.
     *
     * @param a1 a1.
     * @param a2 a2.
     * @param b  b.
     * @return double
     */
    public double metric(Vertex a1, Vertex a2, Vertex b) {
        double d = (b.getX() - a1.getX()) * (a2.getY() - a1.getY())
                - (b.getY() - a1.getY()) * (a2.getX() - a1.getX());
        return d;

    }

    /**
     * JavaDoc.
     */

    public void sortVertices() {
        for (int i = 0; i < vertexList.size(); i++) {
            if (vertexList.get(0).getX() > vertexList.get(i).getX()) {
                Vertex tmp = vertexList.get(0);
                vertexList.set(0, vertexList.get(i));
                vertexList.set(i, tmp);
            }
        }
        for (int i = 1; i < vertexList.size(); i++) {
            int j = i;
            while (j > 1 && (metric(vertexList.get(0),
                    vertexList.get(j - 1),
                    vertexList.get(j)) < 0)) {
                Vertex tmp = vertexList.get(j);
                vertexList.set(j, vertexList.get(j - 1));
                vertexList.set(j - 1, tmp);
                j--;
            }
        }


    }

}
