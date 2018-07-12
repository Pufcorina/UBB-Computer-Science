package Topic;

import DB.DBManager;
import Model.BibliographyEntry;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BibliographyManager {
    public static ArrayList<BibliographyEntry> getAllBiblioBySubstring(String text){
        List<BibliographyEntry> bibliographyEntries = new ArrayList<>(  );
        try {
            String sql = "select * from BibliographyEntry";
            PreparedStatement statement = DBManager.getConnection().prepareStatement( sql );
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                bibliographyEntries.add( new BibliographyEntry( resultSet.getInt( 1 ),
                        resultSet.getInt( 2 ),
                        resultSet.getString( 3 ),
                        resultSet.getString( 4 ),
                        resultSet.getInt(5),
                        resultSet.getInt(6),
                        resultSet.getInt(7))

                );
            }

            bibliographyEntries = bibliographyEntries.stream().filter( b -> b.getTitle().contains( text ) || b.getAuthor().contains( text ) ).collect(Collectors.toList());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (ArrayList<BibliographyEntry>) bibliographyEntries;
    }

    public static void add(List<BibliographyEntry> bibliographyEntries) {
        bibliographyEntries.forEach( b ->{
            try {
                String sql = "insert into BibliographyEntry(IDCategory, Author, Title, NumberOfPages, Year, Cost) values ('" + b.getIdCategory().toString() + "', '" + b.getAuthor() +"', '" + b.getTitle() +"', '" + b.getNumberOfPages().toString() +"', '" + b.getYear().toString() +"', '" + b.getCost().toString() + "');";
                PreparedStatement statement = DBManager.getConnection().prepareStatement( sql );
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
                });

        if (!bibliographyEntries.isEmpty())
        {
            Integer nr = 0;
            Integer catId = bibliographyEntries.get( 0 ).getIdCategory();
            try {
                String sql = "select * from Category where ID='" + catId.toString() + "';";
                PreparedStatement statement = DBManager.getConnection().prepareStatement( sql );
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){
                    nr = resultSet.getInt( 3 );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Integer nbEntries = bibliographyEntries.size() + nr;

            try {
                String sql = "update Category set NumberOfEntries = " + nbEntries.toString() + " where ID = " + catId.toString() + ";";
                PreparedStatement statement = DBManager.getConnection().prepareStatement( sql );
                statement.execute();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void deleteCategory(String categoryId) {
        try {
            String sql = "delete from BibliographyEntry where IDCategory='" + categoryId + "';";
            PreparedStatement statement = DBManager.getConnection().prepareStatement( sql );
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            String sql = "delete from Category where ID='" + categoryId+ "';";
            PreparedStatement statement = DBManager.getConnection().prepareStatement( sql );
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
