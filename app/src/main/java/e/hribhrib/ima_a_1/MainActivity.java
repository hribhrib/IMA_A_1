package e.hribhrib.ima_a_1;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentDisplay = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        currentDisplay.getSize(size);
        dw = size.x;
        dh = size.y/2;


        imageViewUp = this.findViewById(R.id.imageViewUp);
        imageViewDown = this.findViewById(R.id.imageViewDown);

        tvUp = this.findViewById(R.id.textViewUp);
        tvDown = this.findViewById(R.id.textViewDown);

        tvUp.setWidth(size.y / 3);
        tvDown.setWidth(size.y / 3);

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
        int pointerCount = event.getPointerCount();

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
            }
        } catch (Exception e) {}
        return true;
    }

    public void clearDisplay(android.view.View v) {
        initBitmapAndCanvas();
        tvUp.setText("");
        tvDown.setText("");
    }
}