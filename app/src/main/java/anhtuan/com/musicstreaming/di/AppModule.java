package anhtuan.com.musicstreaming.di;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;
import anhtuan.com.musicstreaming.db.AppDB;
import anhtuan.com.musicstreaming.network.AppService;
import anhtuan.com.musicstreaming.utils.Constant;
import anhtuan.com.musicstreaming.utils.FailSafeDoubleJsonDeserializer;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
class AppModule {
  @Singleton
  @Provides
  AppDB provideDb(Application app) {
    return Room.databaseBuilder(app, AppDB.class, "app.db")
        .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
        .fallbackToDestructiveMigration()
        .build();
  }

  static final Migration MIGRATION_1_2 = new Migration(1, 2) {
    @Override
    public void migrate(@NonNull SupportSQLiteDatabase database) {
      database.execSQL("CREATE TABLE IF NOT EXISTS `Quiz` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `courseID` INTEGER, `fromSection` INTEGER, `toSection` INTEGER, `fromSectionOriginID` INTEGER NOT NULL, `toSectionOriginID` INTEGER NOT NULL, `count_total_answered` INTEGER NOT NULL, `count_correct_answered` INTEGER NOT NULL, `type` INTEGER NOT NULL, `is_enable_certificate` INTEGER NOT NULL, `is_enable_take_a_test` INTEGER NOT NULL, `icon` BLOB)");
      database.execSQL("CREATE TABLE IF NOT EXISTS `Highligh` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `content` TEXT, `highlightedText` TEXT, `normal` TEXT, `date` INTEGER, `readingID` INTEGER, `courseID` INTEGER, FOREIGN KEY(`readingID`) REFERENCES `Reading`(`reading_id`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
      database.execSQL("ALTER TABLE Section ADD COLUMN `fromSection` INTEGER");
      database.execSQL("ALTER TABLE Section ADD COLUMN `toSection` INTEGER");
      database.execSQL("ALTER TABLE Section ADD COLUMN `count_total_answered` INTEGER NOT NULL DEFAULT (0)");
      database.execSQL("ALTER TABLE Section ADD COLUMN `count_correct_answerd` INTEGER NOT NULL DEFAULT (0)");
    }
  };

  static final Migration MIGRATION_2_3 = new Migration(2, 3) {
    @Override
    public void migrate(@NonNull SupportSQLiteDatabase database) {
      database.execSQL("CREATE TABLE IF NOT EXISTS `QuestionExam` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `question` TEXT, `o1` TEXT, `o2` TEXT, `o3` TEXT, `o4` TEXT, `explanation` TEXT, `status` INTEGER NOT NULL, `answerUser` TEXT, `answer` INTEGER, `examID` INTEGER, `topicID` INTEGER, `imageID` TEXT, `primaryCode` INTEGER NOT NULL)");
      /*
      int is_pass = 1;

  int status = 0; // -1 la review ket qua
  // 1 la continue;
  // 0 la chua lam gi, can init

  int instant_feed_back = 0;
  int stopWhenFailed;
  Long allow_mistake;
       */
      database.execSQL("ALTER TABLE EXamDB ADD COLUMN `is_pass` INTEGER NOT NULL DEFAULT 1");
      database.execSQL("ALTER TABLE EXamDB ADD COLUMN `status` INTEGER NOT NULL DEFAULT 0");
      database.execSQL("ALTER TABLE EXamDB ADD COLUMN `instant_feed_back` INTEGER NOT NULL DEFAULT 0");
      database.execSQL("ALTER TABLE EXamDB ADD COLUMN `stopWhenFailed` INTEGER NOT NULL DEFAULT 0");
      database.execSQL("ALTER TABLE EXamDB ADD COLUMN `allow_mistake` INTEGER");
      database.execSQL("ALTER TABLE ExamDB ADD COLUMN `time` INTEGER");
      database.execSQL("ALTER TABLE ExamDB ADD COLUMN `timeUsing` INTEGER");
      database.execSQL("ALTER TABLE QuestionTest ADD COLUMN `topicID` INTEGER");
      /*
      Long time;

  Long timeUsing;
       */

      database.execSQL("CREATE TABLE IF NOT EXISTS `QuestionForTestSeperated` (`primaryCode` INTEGER NOT NULL, `question` TEXT, `o1` TEXT, `o2` TEXT, `o3` TEXT, `o4` TEXT, `explanation` TEXT, `imageID` TEXT, `status` INTEGER NOT NULL, `answer` INTEGER, `topicID` INTEGER, PRIMARY KEY(`primaryCode`))");
    }
  };

  @Singleton
  @Provides
  AppService provideAppService() {
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
        .readTimeout(20, TimeUnit.SECONDS)
        .connectTimeout(20, TimeUnit.SECONDS)
        .build();

    return new Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
            .registerTypeAdapter(Double.class, new FailSafeDoubleJsonDeserializer())
            .create()))
        .client(okHttpClient)
        .build()
        .create(AppService.class);
  }
}
