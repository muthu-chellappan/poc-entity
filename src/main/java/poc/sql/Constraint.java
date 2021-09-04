package poc.sql;

public class Constraint {

    private final String name;
    private final String type;
    private final String content;

    public Constraint(final String name, final String type, final String content) {
        this.name = name;
        this.type = type;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

}
