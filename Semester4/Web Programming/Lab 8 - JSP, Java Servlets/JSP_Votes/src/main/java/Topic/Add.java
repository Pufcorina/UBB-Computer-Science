package Topic;

import Model.BibliographyEntry;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddServlet")
public class Add extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String author1 = req.getParameter( "author1" );
        String title1 = req.getParameter( "title1" );
        String nbOfPages1 = req.getParameter( "nbOfPages1" );
        String year1 = req.getParameter( "year1" );
        String cost1 = req.getParameter( "cost1" );

        String author2 = req.getParameter( "author2" );
        String title2 = req.getParameter( "title2" );
        String nbOfPages2 = req.getParameter( "nbOfPages2" );
        String year2 = req.getParameter( "year2" );
        String cost2 = req.getParameter( "cost2" );

        String author3 = req.getParameter( "author3" );
        String title3 = req.getParameter( "title3" );
        String nbOfPages3 = req.getParameter( "nbOfPages3" );
        String year3 = req.getParameter( "year3" );
        String cost3 = req.getParameter( "cost3" );

        List<BibliographyEntry> bibliographyEntries = new ArrayList<>(  );
        if (!author1.equals( "" ) && !title1.equals( "" ) && !nbOfPages1.equals( "" ) && !year1.equals( "" ) && !cost1.equals( "" )){
            bibliographyEntries.add( new BibliographyEntry( 2, author1, title1, Integer.parseInt( nbOfPages1), Integer.parseInt( year1), Integer.parseInt( cost1) ) );
        }
        if (!author2.equals( "" ) && !title2.equals( "" ) && !nbOfPages2.equals( "" ) && !year2.equals( "" ) && !cost2.equals( "" )){
            bibliographyEntries.add( new BibliographyEntry( 2, author2, title2, Integer.parseInt( nbOfPages2), Integer.parseInt( year2), Integer.parseInt( cost2) ) );
        }
        if (!author3.equals( "" ) && !title3.equals( "" ) && !nbOfPages3.equals( "" ) && !year3.equals( "" ) && !cost3.equals( "" )){
            bibliographyEntries.add( new BibliographyEntry( 2, author3, title3, Integer.parseInt( nbOfPages3), Integer.parseInt( year3), Integer.parseInt( cost3) ) );
        }

        BibliographyManager.add(bibliographyEntries);
        // Redirect to index
        resp.sendRedirect("/index.jsp");
    }
}
