package poc.sql;

import java.util.Collections;
import java.util.List;

public enum MacaTable {

    BUSINESS_TYPE("business_type",
            new String[] { "name#VARCHAR(511)#false" },
            null,
            null),
    INDUSTRY_TYPE("industry_type",
            new String[] { "name#VARCHAR(511)#false" },
            null,
            null),
    USER("user",
            new String[] { "email#VARCHAR(511)#false", "password#VARCHAR(1023)#false" },
            new String[] { "email#UNIQUE#email" },
            null),
    USER_PROFILE("user_profile",
            new String[] { "userId#INT#false", "firstName#VARCHAR(511)#false", "lastName#VARCHAR(511)#false", "country#VARCHAR(511)" },
            null,
            new String[] { "userId#user#id" }),
    ACCOUNT("account",
            new String[] { "accountData#JSON", "isActive#boolean#false#false" },
            null,
            null),
    ACCOUNT_PROFILE("account_profile",
            new String[] { "accountId#INT#false", "businessName#VARCHAR(511)#false", "addressLine1#VARCHAR(1023)#false", "addressLine2#VARCHAR(1023)", 
                    "city#VARCHAR(511)#false", "state#VARCHAR(511)#false" , "zipCode#VARCHAR(511)#false", "country#VARCHAR(511)#false", "businessTypeId#INT",
                    "industryId#INT", "website#VARCHAR(1023)", "bankDetails#JSON"},
            null,
            new String[] { "accountId#account#id", "businessTypeId#business_type#id", "industryId#industry_type#id"}),
    ACCOUNT_USER("account_user",
            new String[] { "userId#INT#false", "accountId#INT#false", "userRole#VARCHAR(100)#false", "accountRole#VARCHAR(100)#false", "isActive#boolean#false#false" },
            null,
            new String[] { "userId#user#id", "accountId#account#id"}),
    EVENT("event",
            new String[] { "eventType#VARCHAR(255)#false", "eventSource#VARCHAR(255)", "eventData#JSON" },
            null,
            null),
    PRODUCT("product",
            new String[] { "name#VARCHAR(255)#false", "label#VARCHAR(255)", "description#VARCHAR(1023)",
                    "accountId#INT#false" },
            null,
            new String[] { "accountId#account#id" }),
    OFFER("offer",
            new String[] { "name#VARCHAR(255)#false", "label#VARCHAR(255)", "description#VARCHAR(1023)",
                    "productId#INT#false", "isLive#BOOLEAN" },
            null,
            new String[] { "productId#product#id" }),
    PRICE_COMPONENT("price_component",
            new String[] { "name#VARCHAR(255)#false", "unitPrice#NUMERIC(15,6)#false", "currency#VARCHAR(3)#false",
                    "offerId#INT#false", "pricingModel#VARCHAR(255)", "metered#BOOLEAN#false#true", "billingFrequency#VARCHAR(255)", "maxPrice#NUMERIC(15,6)" },
            null,
            new String[] { "offerId#offer#id" }),
    PRICE_COMPONENT_TIER("price_component_tier",
            new String[] { "basePrice#NUMERIC(15,6)#false", "unitPrice#NUMERIC(15,6)#false", "tierMin#INT",
                    "tierMax#INT", "componentId#INT#false", "basePriceType#VARCHAR(255)", "unitPriceType#VARCHAR(255)" },
            null,
            new String[] { "componentId#price_component#id" }),
    FUNNEL("funnel",
            new String[] { "name#VARCHAR(255)#false", "offerId#INT#false", "description#VARCHAR(1023)" },
            null,
            new String[] { "offerId#offer#id" }),
    FUNNEL_CRITERIA("funnel_criteria",
            new String[] { "funnelId#INT#false", "key#VARCHAR(255)#false", "value#JSON" },
            null,
            new String[] { "funnelId#funnel#id" }),
    QUOTE("quote",
            new String[] { "offerId#INT#false", "status#VARCHAR(255)", "revisionCount#INT" },
            null,
            new String[] {"offerId#offer#id"}),
    QUOTE_REVISION("quote_revision",
            new String[] { "quoteId#INT#false" },
            null,
            new String[] { "quoteId#quote#id" }),
    CUSTOMER("customer",
            new String[] { "accountId#INT#false" },
            null,
            new String[] { "accountId#account#id" }),
    SUBSCRIPTION("subscription",
            new String[] { "quoteId#INT#false", "billingFrequency#INT", "customerId#INT#false",
                    "startDate#TIMESTAMP", "periodStart#TIMESTAMP", "periodEnd#TIMESTAMP" },
            null,
            new String[] { "quoteId#quote#id", "customerId#customer#id" }),
    INVOICE("invoice",
            new String[] { "customerId#INT#false", "collectionMethod#VARCHAR(31)#false",
                    "subscriptionId#INT#false" },
            null,
            new String[] { "customerId#customer#id", "subscriptionId#subscription#id" }),
    INVOICE_LINE_ITEM("invoice_line_item",
            new String[] { "invoiceId#INT#false" },
            null,
            new String[] { "invoiceId#invoice#id" }),
    TRANSACTION("transaction",
            new String[] { "category#VARCHAR(31)#false", "amount#NUMERIC(15,6)#false", "currency#VARCHAR(3)#false" },
            null,
            null),
    PAYMENT_METHOD("payment_method",
            new String[] { "type#VARCHAR(31)#false", "accountId#INT#false", "data#JSON" },
            null,
            new String[] { "accountId#account#id" });

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
