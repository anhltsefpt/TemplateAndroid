package anhtuan.com.musicstreaming.utils;

import android.arch.persistence.room.TypeConverter;
import android.text.TextUtils;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimestampConverter implements JsonDeserializer<Date> {
  /**
   * Simple data format for local time stamp.
   */
  private static DateFormat sLocalDateFormat = new SimpleDateFormat("HHLmm:ss", Locale.ENGLISH);
  /**
   * Simple data format for Server time stamp.
   */
  private static DateFormat sServerDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
  /**
   * Simple data format for SQLite time stamp.
   */
  private static DateFormat sSQLDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

  /**
   * Time stamp parser.
   *
   * @param value The time stamp string value base on registered format
   * @return The timestamp on date format
   */
  @TypeConverter
  public static Date fromTimestamp(final String value) {
    if (!TextUtils.isEmpty(value)) {
      try {
        sServerDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sServerDateFormat.parse(value);
      } catch (ParseException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
        e.printStackTrace();
      }
    }

    return new Date(0);
  }

  /**
   * Time stamp parser.
   *
   * @param value The time stamp long value base on registered format
   * @return The timestamp on date format
   */
  @TypeConverter
  public static Date fromTimestamp(final long value) {
    return new Date(value);
  }

  /**
   * Time stamp parser.
   *
   * @param date The time stamp date value base on registered format
   * @return The timestamp on string format
   */
  public static String toTimestampWithLocalFormat(final Date date) {
    return date == null ? "" : sLocalDateFormat.format(date);
  }

  /**
   * Time stamp parser.
   *
   * @param date The time stamp date value base on registered format
   * @return The timestamp on string format
   */
  @TypeConverter
  public static String toTimestampWithServerFormat(final Date date) {
    return date == null ? "" : sServerDateFormat.format(date);
  }

  /**
   * Time stamp parser.
   *
   * @param date The time stamp date value base on registered format
   * @return The timestamp on string format
   */
  public static String toTimestampWithSQLFormat(final Date date) {
    return date == null ? "" : sSQLDateFormat.format(date);
  }

  @Override
  public Date deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) {
    String date = json.getAsString();

    try {
      sServerDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
      return sServerDateFormat.parse(date);
    } catch (ParseException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
      e.printStackTrace();
      try {
        long time = json.getAsLong();
        return new Date(time);
      } catch (Exception ex) {
        long time = System.currentTimeMillis();
        return new Date(time);
      }
    }
  }
}

