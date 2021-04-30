package suslova.yekaterina.syn_android07;

import android.graphics.Bitmap;

import org.json.JSONArray;
import org.json.JSONObject;

// СОЗДАТЕЛЬ ПОГОДЫ
public class FollowerBuilder {

    public static String getData() {
        String data = "https://api.github.com/users/proffix4/followers";
        return HttpClient.getHTMLData(data);
    }
    private static Bitmap getFollowerImage(String code) {
        return HttpClient.getHTMLImage(code + ".jpg");
    }

    private static Follower dataParsing(String json, String nameF) {
        Follower follower = new Follower();
        try {
            JSONArray _arr = new JSONArray(json);
            for (int i = 0; i< _arr.length(); i++){
                JSONObject _obj = _arr.getJSONObject(i);
                if (_obj.getString("login").equals(nameF)){
                    follower.setLogin(_obj.getString("login"));
                    follower.setUserURL(_obj.getString("html_url"));
                    follower.setRepository(_obj.getString("repos_url"));
                    follower.setIconName(_obj.getString("avatar_url"));
                    follower.setIcon(getFollowerImage(follower.getIconName()));
                }
            }

        } catch (Exception ignore) {
        }
        return follower;
    }

    public static Follower buildStars (String nameF) {
        Follower followers = dataParsing(getData(), nameF);
        return followers;
    }
}