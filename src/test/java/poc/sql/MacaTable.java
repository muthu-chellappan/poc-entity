package poc.sql;

import java.util.Collections;
import java.util.List;

public enum MacaTable {

    USER("user",
            new String[] { "email#VARCHAR(511)#false", "password#VARCHAR(1023)#false" },
            new String[] { "email#UNIQUE#email" },
            null),
    PROFILE("profile",
            new String[] { "user_id#INT#false", "first_name#VARCHAR(511)#false", "last_name#VARCHAR(511)#false" },
            null,
            new String[] { "user_id#user#id" }),
    ACCOUNT("account",
            new String[] { "account_data#JSON" },
            null,
            null),
    ACCOUNT_USER("account_user",
            new String[] { "user_id#INT#false", "account_id#INT#false", "user_role#VARCHAR(31)#false" },
            null,
            new String[] { "user_id#user#id", "account_id#account#id" }),
    EVENT("event",
            new String[] { "event_type#VARCHAR(255)#false", "event_source#VARCHAR(255)", "event_data#JSON" },
            null,
            null),
    PRODUCT("product",
            new String[] { "name#VARCHAR(255)#false", "label#VARCHAR(255)", "description#VARCHAR(1023)",
                    "account_id#INT#false" },
            null,
            new String[] { "account_id#account#id" }),
    OFFER("offer",
            new String[] { "name#VARCHAR(255)#false", "label#VARCHAR(255)", "description#VARCHAR(1023)",
                    "product_id#INT#false", "is_live#BOOLEAN" },
            null,
            new String[] { "product_id#product#id" }),
    PRICE_COMPONENT("price_component",
            new String[] { "name#VARCHAR(255)#false", "unit_price#NUMERIC(15,6)#false", "currency#VARCHAR(3)#false",
                    "offer_id#INT#false" },
            null,
            new String[] { "offer_id#offer#id" }),
    PRICE_COMPONENT_TIER("price_component_tier",
            new String[] { "base_price#NUMERIC(15,6)#false", "unit_price#NUMERIC(15,6)#false", "tier_min#INT",
                    "tier_max#INT", "component_id#INT#false" },
            null,
            new String[] { "component_id#price_component#id" }),
    FUNNEL("funnel",
            new String[] { "name#VARCHAR(255)#false", "offer_id#INT#false", "description#VARCHAR(1023)" },
            null,
            new String[] { "offer_id#offer#id" }),
    FUNNEL_CRITERIA("funnel_criteria",
            new String[] { "funnel_id#INT#false", "key#VARCHAR(255)#false", "value#JSON" },
            null,
            new String[] { "funnel_id#funnel#id" }),
    QUOTE("quote",
            new String[] { "offer_id#INT#false", "status#VARCHAR(255)", "revision_count#INT" },
            null,
            new String[] {"offer_id#offer#id"}),
    QUOTE_REVISION("quote_revision",
            new String[] { "quote_id#INT#false" },
            null,
            new String[] { "quote_id#quote#id" }),
    CUSTOMER("customer",
            new String[] { "account_id#INT#false" },
            null,
            new String[] { "account_id#account#id" }),
    SUBSCRIPTION("subscription",
            new String[] { "quote_id#INT#false", "billing_frequency#INT", "customer_id#INT#false",
                    "start_date#TIMESTAMP", "period_start#TIMESTAMP", "period_end#TIMESTAMP" },
            null,
            new String[] { "quote_id#quote#id", "customer_id#customer#id" }),
    INVOICE("invoice",
            new String[] { "customer_id#INT#false", "collection_method#VARCHAR(31)#false",
                    "subscription_id#INT#false" },
            null,
            new String[] { "customer_id#customer#id", "subscription_id#subscription#id" }),
    INVOICE_LINE_ITEM("invoice_line_item",
            new String[] { "invoice_id#INT#false" },
            null,
            new String[] { "invoice_id#invoice#id" }),
    TRANSACTION("transaction",
            new String[] { "category#VARCHAR(31)#false", "amount#NUMERIC(15,6)#false", "currency#VARCHAR(3)#false" },
            null,
            null),
    PAYMENT_METHOD("payment_method",
            new String[] { "type#VARCHAR(31)#false", "account_id#INT#false", "data#JSON" },
            null,
            new String[] { "account_id#account#id" });

    private final String name;
    private final String[] cols;
    private final String[] cons;
    private final String[] keys;

    private MacaTable(final String name, final String[] cols, final String[] cons, final String[] keys) {
        this.name = name;
        this.cols = cols;
        this.cons = cons;
        this.keys = keys;
    }

    public Table get() {
        final Table table = new Table(name);
        addCols(table);
        addCons(table);
        addKeys(table);
        addQueries(table);
        return table;
    }

    public String getName() {
        return this.name;
    }

    public String[] getCols() {
        return this.cols;
    }

    public List<Query> getQueries() {
        return Collections.emptyList();
    }

    private void addCols(final Table table) {
        if (cols == null) {
            return;
        }
        for (final String col : cols) {
            final String[] tokens = col.split("#");
            final String type = tokens.length > 1 ? tokens[1] : null;
            final boolean nullable = tokens.length > 2 ? Boolean.valueOf(tokens[2].trim()) : Boolean.TRUE;
            final String value = tokens.length > 3 ? tokens[3] : null;
            final Column column = new Column(tokens[0], type, nullable, value);
            table.add(column);
        }
    }

    private void addCons(final Table table) {
        if (cons == null) {
            return;
        }
        for (final String con : cons) {
            final String[] tokens = con.split("#");
            final Constraint constraint = new Constraint(tokens[0], tokens[1], tokens[2]);
            table.add(constraint);
        }
    }

    private void addKeys(final Table table) {
        if (keys == null) {
            return;
        }
        for (final String key : keys) {
            final String[] tokens = key.split("#");
            final Key k = new Key(tokens[0], tokens[1], tokens[2]);
            table.add(k);
        }
    }

    private void addQueries(final Table table) {
        table.addQueries(getQueries());
    }

}
