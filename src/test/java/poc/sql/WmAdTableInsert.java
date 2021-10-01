package poc.sql;

public enum WmAdTableInsert {

    CAMPAIGN_TYPE(WmAdTable.CAMPAIGN_TYPE,
            new String[] { "Direct Sold", "Marketplace" }),//campaign_type_value
    BUYING_TYPE(WmAdTable.BUYING_TYPE,
            new String[] { "Sponsorship", "Premium", "Standard" },//name
            new String[] { "Sponsorship", "Premium", "Standard" }),//type
    COST_METHOD(WmAdTable.COST_METHOD,
            new String[] { "CPM", "CPC", "CPA" },//code
            new String[] { "Cost Per Milli", "Cost Per Click", "Cost Per Action" }),//description
    AD_ZONE_LOCATION(WmAdTable.AD_ZONE_LOCATION,
            new String[] { "Homepage", "Other"},//name
            new String[] { "Homepage", "Other" }),//description
//    AD_ZONE(WmAdTable.AD_ZONE,
//            new String[] { "deliveries-listing-cards", "homepage-card-row-deliveries", "homepage-card-row-dispensaries", "map-listing-cards", "homepage-carousel-1", "homepage-carousel-2", "homepage-carousel-3", "homepage-carousel-4", "homepage-promo-tile-big", "homepage-promo-tile-small", "oserp-banner-top", "products-banner-top", "sale-carousel", "serp-banner-top"},//name
//            new String[] { "deliveries-listing-cards", "homepage-card-row-deliveries", "homepage-card-row-dispensaries", "map-listing-cards", "homepage-carousel-1", "homepage-carousel-2", "homepage-carousel-3", "homepage-carousel-4", "homepage-promo-tile-big", "homepage-promo-tile-small", "oserp-banner-top", "products-banner-top", "sale-carousel", "serp-banner-top" }),//description
    DEVICE_TYPE(WmAdTable.DEVICE_TYPE,
            new String[] { "Mobile", "Desktop"},//name
            new String[] { "Mobile", "Desktop" }),//description
    BUDGET_DURATION(WmAdTable.BUDGET_DURATION,
            new String[] { "Daily", "Lifetime"}),//duration_value
    OS(WmAdTable.OS,
    		new String[] { "IOS", "Android"}, // name 
            new String[] { "IOS", "Android"}),// description
    BROWSER(WmAdTable.BROWSER,
    		new String[] { "Chrome", "IE", "Safari", "Firefox"}, // name 
            new String[] { "Chrome", "IE", "Safari", "Firefox"}),// description
    ROLE(WmAdTable.ROLE,
    		new String[] { "Admin", "Campaign Manager"}, // name 
            new String[] { "Admin", "Campaign Manager"}),// description
    CREATIVE_FORMAT(WmAdTable.CREATIVE_FORMAT,
    		new String[] { "Display", "Display", "Display", "Native", "Video"}, // type 
    		new String[] { "300", "728", "120", "0", "0"}, // width 
            new String[] { "250", "90", "60", "0", "0"}),// height
    DEAL_TYPE(WmAdTable.DEAL_TYPE,
            new String[] { "Private", "PMP"}),// type
    DAY_PART(WmAdTable.DAY_PART,
            new String[] { "Early Morning", "Morning"}),// name
    
    ;

    private final WmAdTable table;
    private final Object[][] colValues;

    private WmAdTableInsert(final WmAdTable table, final Object[]... colValues) {
        this.table = table;
        this.colValues = colValues;
    }

    public Table get() {
        final Table t = new Table(table.getName());
        final String[] cols = table.getCols();
        for (int i = 0; i < cols.length; i++) {
            final String col = cols[i];
            final Object[] values = colValues[i];
            final Column column = Column.get(col);
            t.add(column);
            for (final Object value : values) {
                t.addColumnValue(column, String.valueOf(value));
            }
        }
        return t;
    }

}
