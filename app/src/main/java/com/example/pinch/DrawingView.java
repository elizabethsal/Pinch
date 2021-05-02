package com.example.pinch;



import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DrawingView extends View {



    public int BRUSH_SIZE = 10;
    public static final int COLOR_PEN = Color.BLUE;
    public static final int DEFAULT_BG_COLOR = Color.WHITE;
    private static final float TOUCH_TOLERANCE = 4;

    private float mX, mY;
    private Path mPath;
    private Paint mPaint;
    private int currentColor;
    private final ArrayList<Line> paths = new ArrayList<>();
    private final ArrayList<Circle> circles = new ArrayList<>();
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Paint mBitmapPaint = new Paint(Paint.DITHER_FLAG);
    private Paint circlePaint;
    private Paint backgroundPaint;
    private Circle currentCircle;
    private float cx;
    private float cy;
    private float radius;


    public DrawingView(Context context) {
        super(context);
    }

    public DrawingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(COLOR_PEN);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setXfermode(null);
        mPaint.setAlpha(0xff);
    }

    public void init(DisplayMetrics metrics){
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;

        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

        currentColor = COLOR_PEN;
    }

    public void line(){
        currentColor = COLOR_PEN;
    }

  /*  public  void circle() {
        for (Circle circle : circles) {
            cx = Math.min(circle.getStartPoint().x, circle.getEndPoint().x);
            cy = Math.max(circle.getStartPoint().y, circle.getEndPoint().y);

            if (cx > cy) {
                radius = cy / 4;
            } else {
                radius = cx / 4;

            }
        }
    }
*/
    public void clear(){
        paths.clear();
     //   circles.clear();
        line();
        //circle();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        for (Line fp : paths) {
            mPaint.setColor(fp.getColor());
            mPaint.setStrokeWidth(fp.getStrokeWidth());
            mPaint.setMaskFilter(null);
            mCanvas.drawColor(DEFAULT_BG_COLOR);
            mCanvas.drawPath(fp.getPath(), mPaint);

        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
     //   canvas.restore();

        /*circlePaint = new Paint();
        backgroundPaint = new Paint();

       Resources resources = getResources();
        int circleColor = resources.getColor(R.color.black);
        int backgroundColor = resources.getColor(R.color.white);

        circlePaint.setColor(circleColor);
        backgroundPaint.setColor(backgroundColor);

        canvas.drawPaint(backgroundPaint); //заполняем фон кругами

           canvas.drawCircle(cx, cy, radius, circlePaint);
   */
    }
    }


    private void touchStart(float x, float y){
        mPath = new Path();
        Line fp = new Line(currentColor, BRUSH_SIZE, mPath);
        paths.add(fp);

        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void touchMove(float x, float y){
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);

        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE){
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    private void touchUp(){
        mPath.lineTo(mX, mY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
       /* PointF current = new PointF(event.getX(), event.getY());
        int activePointerId = event.getPointerId(0);

        int pointerIndex = event.findPointerIndex(activePointerId);
        int maskedAction = event.getActionMasked();
        float xPos = event.getX(pointerIndex);
        float yPos = event.getY(pointerIndex);
*/

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN :
                touchStart(x, y);
                invalidate();
            case MotionEvent.ACTION_POINTER_DOWN:
               /* currentCircle = new Circle(current);
                current.x = event.getX(pointerIndex);
                current.y = event.getY(pointerIndex);
                circles.add(currentCircle);
                return true;*/
            case MotionEvent.ACTION_MOVE :
                touchMove(x, y);
           /*     if(currentCircle != null){
                    currentCircle.setEndPoint(current);
                }*/
                invalidate();
                break;
            case MotionEvent.ACTION_UP :
                touchUp();
                invalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
                //currentCircle = null;

        }
        invalidate();
        return true;
    }

}

