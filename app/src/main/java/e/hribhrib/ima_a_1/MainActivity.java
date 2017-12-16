package e.hribhrib.ima_a_1;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity implements View.OnTouchListener {

    ImageView imageViewUp;
    ImageView imageViewDown;

    Bitmap bitmapUp, bitmapDown;
    Canvas canvasUp, canvasDown;
    Paint paint;
    float x = 0, y = 0, upx = 0, upy = 0;

    private int mActivePointerId;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewUp = (ImageView) this.findViewById(R.id.imageViewUp);
        imageViewDown = (ImageView) this.findViewById(R.id.imageViewDown);

        Display currentDisplay = getWindowManager().getDefaultDisplay();
        float dw = currentDisplay.getWidth();
        float dh = currentDisplay.getHeight()/2;

        bitmapUp = Bitmap.createBitmap((int) dw, (int) dh,
                Bitmap.Config.ARGB_8888);
        bitmapDown = Bitmap.createBitmap((int) dw, (int) dh,
                Bitmap.Config.ARGB_8888);
        canvasUp = new Canvas(bitmapUp);
        canvasDown = new Canvas(bitmapDown);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(12);
        imageViewUp.setImageBitmap(bitmapUp);
        imageViewDown.setImageBitmap(bitmapDown);

        imageViewUp.setOnTouchListener(this);
        imageViewDown.setOnTouchListener(this);
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
                int action = event.getActionMasked();
                int actionIndex = event.getActionIndex();

                x = (int) event.getX(id);
                y = (int) event.getY(id);

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


                if(v.getId() == imageViewUp.getId()){
                    canvasUp.drawPoint(x, y, paint);
                    imageViewUp.invalidate();
                } else {
                    canvasDown.drawPoint(x, y, paint);
                    imageViewDown.invalidate();
                }



                System.out.println("Action: " + " Index: " + actionIndex + " ID: " + id + " X: " + x + " Y: " + y);
            }
        }catch (Exception e){

        }

        return true;
    }
}