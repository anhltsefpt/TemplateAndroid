package anhtuan.com.musicstreaming.utils;

import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.text.Html;
import android.text.Spanned;

public class Utils {
  public static Spanned getHTMLText(String question) {
    try {
      if (VERSION.SDK_INT >= VERSION_CODES.N) {
        return Html.fromHtml(question, Html.FROM_HTML_MODE_LEGACY);
      } else {
        return Html.fromHtml(question);
      }
    }catch (Exception e) {
      return Html.fromHtml("");
    }
  }
}
