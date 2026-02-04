package logic;

/**
 * Container for parsed data.
 * Objects of this class are returned by the parse method in the Parser class.
 */
public class AfterParse {

    private String keyword;
    private String description;
    private String date1; // For deadline: by, for event: from
    private String date2; // For event: to

    public AfterParse(String keyword, String description) {
        this.keyword = keyword;
        this.description = description;
    }

    public AfterParse(String keyword, String description, String date1) {
        this(keyword, description);
        this.date1 = date1;
    }

    public AfterParse(String keyword, String description, String date1, String date2) {
        this(keyword, description, date1);
        this.date2 = date2;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDate1() {
        return date1;
    }

    public String getDate2() {
        return date2;
    }

    public int getIndex() {
        return Integer.parseInt(description);
    }
}
