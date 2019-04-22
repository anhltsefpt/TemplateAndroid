package anhtuan.com.musicstreaming.network;

import android.arch.lifecycle.LiveData;
import java.util.List;
import org.w3c.dom.Comment;
import retrofit2.http.GET;

public interface AppService {
  @GET("/v1/comments")
  LiveData<List<Comment>> get();
}
