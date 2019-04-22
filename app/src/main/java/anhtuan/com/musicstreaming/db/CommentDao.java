package anhtuan.com.musicstreaming.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

@Dao
public abstract class CommentDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public abstract void insert(Comment comment);
}
