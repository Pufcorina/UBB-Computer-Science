package Topic;

import DB.DBManager;
import Model.Photo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PhotoManager {
    public static void addNewPhoto(String url, Integer userId){
        try {
            String sql = "insert into Photos(photoUrl, photoLikes, photoUserId) values ('" + url + "', 0, '" + userId.toString() + "');";
            PreparedStatement statement = DBManager.getConnection().prepareStatement( sql );
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Photo> getAllPhotos() {
        ArrayList<Photo> photos = new ArrayList<>(  );

        try {
            String sql = "select * from Photos";
            PreparedStatement statement = DBManager.getConnection().prepareStatement( sql );
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                photos.add( new Photo( resultSet.getInt( 1 ),
                                        resultSet.getString( 2 ),
                                        resultSet.getInt( 3 ),
                                        resultSet.getInt( 4 ))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return photos;
    }

    public static void likePhoto(Integer nrLikes, Integer photoId){
        try {
            String sql = "update Photos set photoLikes = " + nrLikes.toString() + " where photoId = " + photoId.toString() + ";";
            PreparedStatement statement = DBManager.getConnection().prepareStatement( sql );
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
