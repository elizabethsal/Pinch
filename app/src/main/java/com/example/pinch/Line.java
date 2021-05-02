package com.example.pinch;

import android.graphics.Path;

public class Line {


        private int color;
        private int strokeWidth;
        private Path path;

        public Line (int color, int strokeWidth, Path path) {
            this.color = color;
            this.strokeWidth = strokeWidth;
            this.path = path;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public int getStrokeWidth() {
            return strokeWidth;
        }

        public void setStrokeWidth(int strokeWidth) {
            this.strokeWidth = strokeWidth;
        }

        public Path getPath() {
            return path;
        }

        public void setPath(Path path) {
            this.path = path;
        }
    }