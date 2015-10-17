package magneto.lukmarr.pl.magneto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import magneto.lukmarr.pl.magneto.lib.IconCallback;
import magneto.lukmarr.pl.magneto.lib.Magnet;

public class MainActivity extends Activity {
    public static final String TAG=MainActivity.class.getSimpleName();
        private static Magnet mMagnet;
    private static Handler handler = new Handler(Looper.getMainLooper());
    private static ImageView iconView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iconView = new ImageView(MainActivity.this);
                iconView.setImageResource(R.drawable.tick);
                mMagnet = new Magnet.Builder(MainActivity.this)
                        .setIconView(iconView) // required
                        .withResource(0,R.drawable.tick)
                        .withResource(1,R.drawable.abc_btn_radio_to_on_mtrl_015)
                        .withResource(2,R.drawable.abc_btn_radio_material)

                        .setRemoveIconResId(R.drawable.trash)
                        .setIconCallback(new IconCallback() {
                            @Override
                            public void onFlingAway() {
                                Log.d(TAG, "onFlingAway ");
                            }

                            @Override
                            public void onMove(float x, float y) {
//                                Log.d(TAG, "onMove " + x + ", " + y);
                            }

                            @Override
                            public void onIconClick(View icon, float iconXPose, float iconYPose) {
                                //// TODO: 2015-10-14 new view for clickable notification
                                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                mMagnet.destroy();
                            }

                            @Override
                            public void onIconDestroyed() {
                            }
                        }).setRemoveIconShadow(R.drawable.bottom_shadow)
                        .setShouldFlingAway(true)
                        .setShouldStickToWall(true)
                        .setRemoveIconShouldBeResponsive(true)
                        .build();

                mMagnet.show();
            }
        }, 500);
            }
        });
    }
}
