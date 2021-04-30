package suslova.yekaterina.syn_android07;

import android.graphics.Bitmap;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Follower {

    private String login;
    private String userURL;
    private static Bitmap icon;
    private String iconName;
    private String repository;




    public void setLogin(String login) {this.login = login;}
    public void setUserURL(String userURL) {this.userURL = userURL;}
    public void setIcon(Bitmap icon) {this.icon = icon;}
    public void setIconName(String iconName) {this.iconName = iconName;}
    public void setRepository(String repository) {this.repository = repository;}



    public String getLogin() {return login;}
    public String getUserURL() {return userURL;}
    public static Bitmap getIcon() {return icon;}
    public String getIconName() {return iconName;}
    public String getRepository() {return repository;}
}
