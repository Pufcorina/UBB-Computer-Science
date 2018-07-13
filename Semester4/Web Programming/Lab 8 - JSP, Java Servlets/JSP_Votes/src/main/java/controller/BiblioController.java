package controller;

import DB.DBManager;
import Model.BibliographyEntry;
import Topic.BibliographyManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class BiblioController extends HttpServlet {
    public BiblioController() {super();}
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {

            if (action.equals("getByTitleOrAuthor")){
                response.setContentType("application/json");

                String input = request.getParameter("input");

                List<BibliographyEntry> entries = BibliographyManager.getAllBiblioBySubstring(input);

                JSONArray jsonArray = null;
                try {
                    jsonArray = convertEntriesToJsonArray(entries);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                PrintWriter out = new PrintWriter(response.getOutputStream());
                out.println( jsonArray != null ? jsonArray.toString() : null );
                out.flush();
            }


            if (action.equals("getByTitleOrAuthorPaged")){
                response.setContentType("application/json");

                String input = request.getParameter("input");
                int page = Integer.valueOf(request.getParameter("page"));

                List<BibliographyEntry> entries = BibliographyManager.getAllBiblioBySubstring(input);
                List<List<BibliographyEntry>> pages = splitIntoPages(entries);
                System.out.println(pages);
                try {
                    entries = pages.get(page);
                }catch (Exception e) {
                    entries = new ArrayList<>();
                }

                JSONArray jsonArray = null;
                try {
                    jsonArray = convertEntriesToJsonArray(entries);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                PrintWriter out = new PrintWriter(response.getOutputStream());
                out.println( jsonArray != null ? jsonArray.toString() : null );
                out.flush();
            }



        }
    }




    private JSONArray convertEntriesToJsonArray(List<BibliographyEntry> entries) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (BibliographyEntry e : entries) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ID", e.getId());
            jsonObject.put("IDCategory", e.getIdCategory());
            jsonObject.put("Author", e.getAuthor());
            jsonObject.put("Title", e.getTitle());
            jsonObject.put("NumberOfPages", e.getNumberOfPages());
            jsonObject.put("Year", e.getYear());
            jsonObject.put("Cost", e.getCost());
            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }

    private List<BibliographyEntry> sortEntieis(List<BibliographyEntry> entries){
        for (int i = 0; i < entries.size() - 1; i++){
            for (int j = i + 1; j < entries.size(); j++){
                if(entries.get(i).getIdCategory() > entries.get(j).getIdCategory()){
                    BibliographyEntry aux = entries.get(i);
                    entries.set(i, entries.get(j));
                    entries.set(j, aux);
                }
            }
        }
        return entries;
    }
    private List<List<BibliographyEntry>> splitIntoPages(List<BibliographyEntry> entries) {
        List<List<BibliographyEntry>> pages = new ArrayList<>();
        entries = sortEntieis( entries );

        List<BibliographyEntry> page = new ArrayList<>();
        for (int i = 0; i < entries.size() - 1; i++) {
            if ((page.size() < 5) && (page.isEmpty() || entries.get( i - 1 ).getIdCategory().equals( entries.get( i ).getIdCategory() ))) {
                page.add( entries.get( i ) );
            } else {
                pages.add( page );
                page = new ArrayList<>();
                page.add( entries.get( i ) );
            }
        }

        pages.add( page );
        return pages;
    }
}
