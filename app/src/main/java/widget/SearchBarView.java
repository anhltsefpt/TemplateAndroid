package widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import anhtuan.com.musicstreaming.R;

public class SearchBarView extends FrameLayout implements View.OnFocusChangeListener, View.OnClickListener{
  private EditText searchEdit;
  private OnSearchBarListener listener;

  public SearchBarView(Context context) {
    this(context, null);
  }

  public SearchBarView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public SearchBarView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    inflate(getContext(), R.layout.view_search_bar, this);
    searchEdit = findViewById(R.id.search_editText);
    searchEdit.setOnFocusChangeListener(this);
    searchEdit.setOnClickListener(this);
    searchEdit.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
      }

      @Override
      public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
      }

      @Override
      public void afterTextChanged(Editable editable) {
        String keyword = null;
        if (editable != null && !TextUtils.isEmpty(editable.toString())) {
          keyword = editable.toString();
        }

        if (listener != null) {
          listener.onWordChange(keyword);
        }
      }
    });
  }

  @Override
  public void onFocusChange(View v, boolean hasFocus) {
    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
    if (imm == null) {
      return;
    }
    if (hasFocus) {
      imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
    } else {
      imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    if (listener != null) {
      listener.onSearchFocus(hasFocus);
    }
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.search_editText:
        showKeyboard(true);
        searchEdit.setCursorVisible(true);
        if (listener != null) {
          listener.onSearchFocus(true);
        }
        break;
      default:
        break;
    }
  }

  public void show(boolean focus) {
    if (focus) {
      setVisibility(VISIBLE);
    }
    if (listener != null) {
      if (focus) {
        listener.onShowSearchBar();
      }
    }
    if (focus) {
      searchEdit.requestFocus();
    }
    showKeyboard(focus);
  }

  public void hide() {
    setVisibility(GONE);
    if (listener != null) {
      listener.onHideSearchBar();
    }
    clearText();
  }

  public void clearText() {
    searchEdit.setText("");
    searchEdit.setCursorVisible(false);
  }

  public void showKeyboard(boolean show) {
    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
    if (imm == null) {
      return;
    }
    if (show) {
      imm.showSoftInput(searchEdit, InputMethodManager.SHOW_IMPLICIT);
    } else {
      imm.hideSoftInputFromWindow(searchEdit.getWindowToken(), 0);
    }
  }

  public void updateTextLanguage(String str) {
    if (searchEdit != null) {
      searchEdit.setHint(str);
    }
  }

  public void setFocus(Boolean focus) {
    searchEdit.setFocusable(focus);
  }

  public void setSearchBarListener(OnSearchBarListener listener) {
    this.listener = listener;
  }

  public interface OnSearchBarListener {
    void onWordChange(String keyword);

    void onShowSearchBar();

    void onHideSearchBar();

    boolean backedNecessary();

    void onSearchFocus(boolean hasFocus);
  }
}

