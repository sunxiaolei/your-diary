package sunxl8.your_diary.constant;

import sunxl8.your_diary.R;

/**
 * Created by sunxl8 on 2017/2/10.
 */

public class Constant {

    public static final String SP_ACCOUNT = "SP_ACCOUNT";
    public static final String SP_ACCOUNT_NUMBER = "SP_ACCOUNT_NUMBER";
    public static final String SP_ACCOUNT_PASSWORD = "SP_ACCOUNT_PASSWORD";
    public static final String SP_ACCOUNT_NAME = "SP_ACCOUNT_NAME";

    public static final int ITEM_TYPE_CONTACT = 0;
    public static final int ITEM_TYPE_DIARY = 1;
    public static final int ITEM_TYPE_MEMO = 2;

    private static final int STATUS_MOOD_SMILE = 0;
    private static final int STATUS_MOOD_NORMAL = 1;
    private static final int STATUS_MOOD_SAD = 2;
    private static final int STATUS_MOOD_ANGER = 3;
    public static final int[] IC_MOOD = {R.drawable.ic_mood_smile, R.drawable.ic_mood_mormal, R.drawable.ic_mood_sad, R.drawable.ic_mood_anger};

    private static final int STATUS_WEATHER_SUNNY = 0;
    private static final int STATUS_WEATHER_OVERCAST = 1;
    private static final int STATUS_WEATHER_CLOUDY = 2;
    private static final int STATUS_WEATHER_RAINY = 3;
    private static final int STATUS_WEATHER_WINDY = 4;
    private static final int STATUS_WEATHER_THUNDER = 5;
    private static final int STATUS_WEATHER_SNOW = 6;
    private static final int STATUS_WEATHER_HAZE = 7;
    private static final int STATUS_WEATHER_FOGGY = 8;
    public static final int[] IC_WEATHER = {R.drawable.ic_weather, R.drawable.ic_weather, R.drawable.ic_weather, R.drawable.ic_weather,
            R.drawable.ic_weather, R.drawable.ic_weather, R.drawable.ic_weather, R.drawable.ic_weather, R.drawable.ic_weather};
}
