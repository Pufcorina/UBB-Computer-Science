package Model;

import DB.DBManager;
import Topic.PhotoManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Photo {
    private Integer id;
    private String url;
    private Integer likes;
    private Integer userId;

    public Photo(Integer id, String url, Integer userId){
        this.id = id;
        this.url = url;
        this.likes = 0;
        this.userId = userId;
    }

    public Photo(Integer id, String url, Integer likes, Integer userId){
        this.id = id;
        this.url = url;
        this.likes = likes;
        this.userId = userId;
    }

    public Photo(){}

    public Integer getId() { return this.id; }

    public void setId(Integer id) { this.id = id; }

    public String getUrl() { return this.url; }

    public void setUrl(String url) { this.url = url; }

    public Integer getLikes() { return this.likes; }

    public void setLikes(Integer likes) { this.likes = likes; }

    public Integer getUserId() { return this.likes; }

    public void setUserId(Integer userId) { this.userId = userId; }

    public String getUserName() {
        try {
            String sql = "select userName from Users where id = " + this.userId.toString() + ";";
            PreparedStatement statement = DBManager.getConnection().prepareStatement( sql );
            ResultSet resultSet = statement.executeQuery( sql );
            resultSet.next();
            return resultSet.getString( "userName" );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
