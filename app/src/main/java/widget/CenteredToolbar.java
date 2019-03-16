package widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;
import anhtuan.com.musicstreaming.R;

public class CenteredToolbar extends Toolbar {

  private TextView txtTitle;
  public CenteredToolbar(Context context) {
    super(context);
  }

  public CenteredToolbar(Context context,
      @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public CenteredToolbar(Context context,
      @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  private void init() {
    createTextViewCenter();
  }

  public void createTextViewCenter() {
    if (txtTitle == null) {
      txtTitle = new TextView(getContext());
      txtTitle.setSingleLine();
      txtTitle.setEllipsize(TextUtils.TruncateAt.END);
      txtTitle.setGravity(Gravity.CENTER);
      txtTitle.setTextAppearance(getContext(), R.style.AppTheme_Menu_TextAppearance_Title);

      android.support.v7.widget.Toolbar.LayoutParams lp = new android.support.v7.widget.Toolbar.LayoutParams(
          android.support.v7.widget.Toolbar.LayoutParams.WRAP_CONTENT, android.support.v7.widget.Toolbar.LayoutParams.WRAP_CONTENT);
      lp.gravity = Gravity.CENTER;
      txtTitle.setLayoutParams(lp);
      addView(txtTitle);
      txtTitle.setVisibility(GONE);
    }
  }

  public void showTitle(String title) {

  }
}
