package Model;

public class BibliographyEntry {
    private Integer id;
    private Integer idCategory;
    private String author;
    private String title;
    private Integer numberOfPages;
    private Integer year;
    private Integer cost;

    public BibliographyEntry(Integer id, Integer idCategory, String author, String title, Integer numberOfPages, Integer year, Integer cost) {
        this.id = id;
        this.idCategory = idCategory;
        this.author = author;
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.year = year;
        this.cost = cost;
    }

    public BibliographyEntry(Integer idCategory, String author, String title, Integer numberOfPages, Integer year, Integer cost) {
        this.idCategory = idCategory;
        this.author = author;
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.year = year;
        this.cost = cost;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getCost() {
        return cost;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
