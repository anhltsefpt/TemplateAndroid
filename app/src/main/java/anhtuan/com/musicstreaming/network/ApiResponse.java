package anhtuan.com.musicstreaming.network;

import android.support.annotation.Nullable;
import java.io.IOException;
import retrofit2.Response;

public class ApiResponse<T> {
  public static final int SUCCESS_CODE = 200;
  public static final int SUCCESS_CODE_MAX = 300;
  public final int code;
  @Nullable
  public final T body;
  @Nullable
  public final String errorMessage;

  public ApiResponse(Throwable error) {
    code = 500;
    body = null;
    errorMessage = error.getMessage();
  }

  public ApiResponse(Response<T> response) {
    if (response == null) {
      code = 0;
      body = null;
      errorMessage = "Somethings are wrong";
    } else {
      code = response.code();
      if (response.isSuccessful()) {
        body = response.body();
        errorMessage = null;
      } else {
        String message = null;
        if (response.errorBody() != null) {
          try {
            message = response.errorBody().string();
          } catch (IOException ignored) {
          }
        }
        if (message == null || message.trim().length() == 0) {
          message = response.message();
        }
        errorMessage = message;
        body = null;
      }
    }
  }

  public ApiResponse(int code, T response, String message) {
    this.code = code;
    this.body = response;
    this.errorMessage = message;
  }

  public boolean isSuccessful() {
    return code >= SUCCESS_CODE && code < SUCCESS_CODE_MAX;
  }
}


