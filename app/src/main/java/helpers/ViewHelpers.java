package helpers;

import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.cpasnoor.humberfinalcalculator.R;

public class ViewHelpers {
    private ViewHelpers() {

    }

    @NonNull
    public static String getTxtViewString(TextView v) {
        String txt = v.getText().toString();
        return txt.equals("0") ? "" : txt;
    }

    @NonNull
    public static String getBtnViewString(Button v) {
        return v.getText().toString();
    }

    public static void animateView(View v, Boolean shouldAnimate, Context ctx) {
        if (shouldAnimate) {
            v.startAnimation(AnimationUtils.loadAnimation(ctx, R.anim.blink));
        }
    }

    public static String concat(String prevValue, String currValue) {
        return prevValue.concat(currValue);
    }
}
