package com.example.pinch;

import android.graphics.Path;
import android.graphics.PointF;

public class Circle {

    private PointF startPoint;
    private PointF endPoint;
    private Path path;

    public Circle(PointF startPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.path = path;
    }

    public PointF getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(PointF startPoint) {
        this.startPoint = startPoint;
    }

    public PointF getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(PointF endPoint) {
        this.endPoint = endPoint;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }
}
