package anhtuan.com.musicstreaming.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import anhtuan.com.musicstreaming.utils.TimestampConverter;

@Database(entities = {Comment.class}, version = 1, exportSchema = true)
@TypeConverters({TimestampConverter.class})
public abstract class AppDB extends RoomDatabase {
  abstract public CommentDao commentDao();
}
