package anhtuan.com.musicstreaming.binding;

import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import anhtuan.com.musicstreaming.utils.Utils;
import com.bumptech.glide.Glide;
import javax.inject.Inject;

public class FragmentBindingAdapter {

  final Fragment fragment;

  @Inject
  public FragmentBindingAdapter(Fragment fragment) {
    this.fragment =fragment;
  }

  @BindingAdapter("imageUrl")
  public void bindImage(ImageView imageView, String url) {
    Glide.with(fragment).load(url)
        .into(imageView);
  }

  @BindingAdapter(("localImage"))
  public void loadLocalImage(ImageView imageView, byte[] bytes) {
    Glide.with(fragment)
        .load(bytes)
        .into(imageView);
  }

  @BindingAdapter("backgroundColor")
  public void bgColor(View view, String color) {
    if (!TextUtils.isEmpty(color)) {
      view.setBackgroundColor(Color.parseColor(color));
    }
  }

  @BindingAdapter("setBgColor")
  public void setBgCardview(CardView cardview, String color) {
    cardview.setCardBackgroundColor(Color.parseColor(color));
  }

  @BindingAdapter("setTextColor")
  public void setTextColor(TextView textView, String color) {
    textView.setTextColor(Color.parseColor(color));
  }

  @BindingAdapter("setTextSize")
  public void setTextSize(TextView tv, Integer textSize) {
    try {
      tv.setTextSize(textSize);
    } catch (Exception e) {

    }
  }

  @BindingAdapter("textbookColor")
  public void setTextbookColor(TextView tv, String color) {
    try {
      tv.setTextColor(Color.parseColor(color));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @BindingAdapter("setWeight")
  public void setWeight(LinearLayout ln, float weight) {
    try {
      LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
          0,
          LayoutParams.MATCH_PARENT,
          weight
      );
      ln.setLayoutParams(param);
    } catch (Exception e) {

    }
  }

  @BindingAdapter("setHtmlText")
  public void setHtmlText(TextView textView, String text) {
    try {
      textView.setText(Utils.getHTMLText(text));
    } catch (Exception e) {

    }
  }

  @BindingAdapter("maxValue")
  public void setSeekingBarMax(SeekBar seekingBar, Long maxValue) {
    try {
      seekingBar.setMax(maxValue.intValue());
    } catch (Exception e) {
      seekingBar.setMax(100);
    }
  }

  @BindingAdapter("progressDrawable")
  public void setProgressDrawable(ProgressBar progressBar, Drawable drawable) {
    try {
      progressBar.setProgressDrawable(drawable);
    } catch (Exception e) {

    }
  }
}
