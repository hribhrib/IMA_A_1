package e.hribhrib.ima_a_1;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnTouchListener {

    ImageView imageViewUp, imageViewDown;
    TextView tvUp, tvDown;

    Bitmap bitmapUp, bitmapDown;
    Canvas canvasUp, canvasDown;
    Paint paint;

    Display currentDisplay;
    float dw;
    float dh;

    Bitmap saveUp;
    Bitmap saveDown;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentDisplay = getWindowManager().getDefaultDisplay();
        dw = currentDisplay.getWidth();
        dh = currentDisplay.getHeight() / 2;

        imageViewUp = (ImageView) this.findViewById(R.id.imageViewUp);
        imageViewDown = (ImageView) this.findViewById(R.id.imageViewDown);

        tvUp = (TextView) this.findViewById(R.id.textViewUp);
        tvDown = (TextView) this.findViewById(R.id.textViewDown);

        tvUp.setWidth(currentDisplay.getWidth()/3);
        tvDown.setWidth(currentDisplay.getWidth()/3);

        initBitmapAndCanvas();

        initPaint();

        imageViewUp.setOnTouchListener(this);
        imageViewDown.setOnTouchListener(this);
    }

    private void initBitmapAndCanvas() {


        bitmapUp = Bitmap.createBitmap((int) dw, (int) dh,
                Bitmap.Config.ARGB_8888);
        bitmapDown = Bitmap.createBitmap((int) dw, (int) dh,
                Bitmap.Config.ARGB_8888);
        canvasUp = new Canvas(bitmapUp);
        canvasDown = new Canvas(bitmapDown);

        imageViewUp.setImageBitmap(bitmapUp);
        imageViewDown.setImageBitmap(bitmapDown);
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(12);
    }

    public boolean onTouch(View v, MotionEvent event) {
        System.out.println("touch");
        System.out.print("x:" + event.getX());
        System.out.print("y:" + event.getY());
        System.out.println();

        int pointerCount = event.getPointerCount();

        System.out.println(pointerCount);

        try {

            for (int i = 0; i < pointerCount; i++) {


                int x = (int) event.getX(i);
                int y = (int) event.getY(i);
                int id = event.getPointerId(i);
                int actionIndex = event.getActionIndex();

                switch (id % 4) {
                    case 0:
                        paint.setColor(Color.GREEN);
                        break;
                    case 1:
                        paint.setColor(Color.BLUE);
                        break;
                    case 2:
                        paint.setColor(Color.RED);
                        break;
                    case 3:
                        paint.setColor(Color.BLACK);
                        break;
                }


                if (v.getId() == imageViewUp.getId()) {
                    tvUp.setText(x + "/" + y);
                    canvasUp.drawPoint(x, y, paint);
                    imageViewUp.invalidate();
                } else {
                    tvDown.setText(x + "/" + y);
                    canvasDown.drawPoint(x, y, paint);
                    imageViewDown.invalidate();
                }


                System.out.println("Action: " + " Index: " + actionIndex + " ID: " + id + " X: " + x + " Y: " + y);
            }
        } catch (Exception e) {

        }



        return true;
    }


    public void clearDisplay(android.view.View v) {
        initBitmapAndCanvas();
        tvUp.setText("");
        tvDown.setText("");
    }


    //Use onSaveInstanceState(Bundle) and onRestoreInstanceState

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        saveUp = bitmapUp.copy(bitmapUp.getConfig(),true);
        saveDown = bitmapDown.copy(bitmapDown.getConfig(),true);

        super.onSaveInstanceState(savedInstanceState);
    }

//onRestoreInstanceState

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        bitmapUp = saveUp.copy(saveUp.getConfig(),true);
        bitmapDown = saveDown.copy(saveDown.getConfig(),true);
    }
}