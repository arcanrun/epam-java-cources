package com.epam.university.java.core.task013;

import java.util.ArrayList;
import java.util.Collection;

public class Task013Impl implements Task013 {
    @Override
    public Figure invokeActions(Figure figure, Collection<FigureAction> actions) {
        if (figure == null
                || actions == null
                || actions.size() == 0
                || (actions.size() != ((FigureImpl) figure).getVertexCount())) {
            throw new IllegalArgumentException();
        }
        for (FigureAction fa : actions) {
            fa.run(figure);
        }
        return figure;
    }

    @Override
    public boolean isConvexPolygon(Figure figure) {
        if (figure == null) {
            throw new IllegalArgumentException();
        }

        return ((FigureImpl) figure).checkConvexity();

    }


}
