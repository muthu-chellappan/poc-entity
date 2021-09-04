package poc.sql;

public class Key {

    private final String col;
    private final String table;
    private final String foreign;

    public Key(final String col, final String table, final String foreign) {
        this.col = col;
        this.table = table;
        this.foreign = foreign;
    }

    public String getCol() {
        return col;
    }

    public String getTable() {
        return table;
    }

    public String getForeign() {
        return foreign;
    }

}
