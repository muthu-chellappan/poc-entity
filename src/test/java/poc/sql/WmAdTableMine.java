package poc.sql;

public enum WmAdTableMine {

//    DEVICE_MAKE("device_make", null, null, null);

    CONTINENT("continent",
            new String[] { "name#VARCHAR(100)#false" },
            new String[] { "name#UNIQUE#name" },
            null),
    COUNTRY("country",
            new String[] { "name#VARCHAR(100)#false", "code#VARCHAR(3)#false", "continent_id#INT#false" },
            new String[] { "name#UNIQUE#name", "code#UNIQUE#code" },
            new String[] { "continent_id#continent#id" }),
    STATE("state",
            new String[] { "name#VARCHAR(100)#false", "code#VARCHAR(3)", "country_id#INT#false" },
            new String[] { "code#UNIQUE#code" },
            new String[] { "country_id#country#id" }),
    REGION("region",
            new String[] { "name#VARCHAR(100)#false", "code#VARCHAR(3)", "country_id#INT#false" },
            new String[] { "code#UNIQUE#code" },
            new String[] { "country_id#country#id" }),
    CITY("city",
            new String[] { "name#VARCHAR(100)#false", "code#VARCHAR(3)", "country_id#INT#false" },
            new String[] { "code#UNIQUE#code" },
            new String[] { "country_id#country#id" }),
    POSTAL_CODE("postal_code",
            new String[] { "code#VARCHAR(100)#false", "description#VARCHAR(100)", "country_id#INT#false" },
            new String[] { "code#UNIQUE#code" },
            new String[] { "country_id#country#id" }),
    CURRENCY("currency",
            new String[] { "name#VARCHAR(100)#false", "code#VARCHAR(3)#false", "country_id#INT#false" },
            null,
            new String[] { "country_id#country#id" }),
    TIME_ZONE("time_zone",
            new String[] { "name#VARCHAR(100)#false", "code#VARCHAR(3)#false", "utc_offset#INT#false" },
            new String[] { "name#UNIQUE#name", "code#UNIQUE#code" },
            null),
    LOCALE("locale",
            new String[] { "code#VARCHAR(5)#false", "description#VARCHAR(100)", "decimal_notation#CHAR(1)",
                    "kilo_separator#CHAR(1)" },
            new String[] { "code#UNIQUE#code" },
            null),
    IAB_CATEGORY("iab_category",
            new String[] { "name#VARCHAR(100)#false", "code#VARCHAR(10)#false", "description#VARCHAR(500)" },
            new String[] { "code#UNIQUE#code" },
            null),
    IAB_SUB_CATEGORY("iad_sub_category",
            new String[] { "name#VARCHAR(100)#false", "code#VARCHAR(10)#false", "description#VARCHAR(500)",
                    "iab_category_id#INT#false" },
            new String[] { "code#UNIQUE#code" },
            new String[] { "iab_category_id#iab_category#id" }),
    DEVICE_MAKE("device_make",
            new String[] { "make#VARCHAR(100)#false", "brand#VARCHAR(100)" },
            new String[] { "make_brand#UNIQUE#make,brand" },
            null),
    DEVICE_MODEL("device_model",
            new String[] { "name#VARCHAR(100)#false", "model#VARCHAR(100)#false", "description#VARCHAR(500)",
                    "device_make_id#INT#false" },
            new String[] { "model_device_id#UNIQUE#model,device_make_id" },
            new String[] { "device_make_id#device_make#id" }),
    BROWSER("browser",
            new String[] { "name#VARCHAR(100)#false", "description#VARCHAR(500)" },
            new String[] { "name#UNIQUE#name" },
            null),
    BROWSER_VERSION("browser_version",
            new String[] { "name#VARCHAR(100)", "version#VARCHAR(100)#false", "description#VARCHAR(500)",
                    "browser_id#INT#false" },
            new String[] { "version_browser_id#UNIQUE#version,browser_id" },
            new String[] { "browser_id#browser#id" }),
    DELIVERY_POINT("devliery_point",
            new String[] { "name#VARCHAR(100)#false", "description#VARCHAR(500)" },
            null,
            null),
    OS("os",
            new String[] { "name#VARCHAR(100)#false", "description#VARCHAR(500)" },
            new String[] { "name#UNIQUE#name" },
            null),
    OS_VERSION("os_version",
            new String[] { "name#VARCHAR(100)", "version#VARCHAR(100)#false", "description#VARCHAR(500)",
                    "os_id#INT#false" },
            new String[] { "version_od_id#UNIQUE#version,os_id" },
            new String[] { "os_id#os#id" }),
    AD_UNIT("ad_unit",
            new String[] { "ad_format#VARCHAR(100)#false", "ad_type#VARCHAR(100)#false" },
            new String[] { "ad_format_ad_type#UNIQUE#ad_format,ad_type" },
            null),
    PACING_TYPE("pacing_type",
            new String[] { "name#VARCHAR(100)#false", "type#VARCHAR(100)#false" },
            new String[] { "type#UNIQUE#type" },
            null),
    BUYING_TYPE("buying_type",
            new String[] { "name#VARCHAR(100)#false", "type#VARCHAR(100)#false" },
            new String[] { "type#UNIQUE#type" },
            null),
    OPTIMIZATION_GOAL_TYPE("optimization_goal_type",
            new String[] { "name#VARCHAR(100)#false", "type#VARCHAR(100)#false" },
            new String[] { "type#UNIQUE#type" },
            null),
    FREQ_CAP_INTERVAL_TYPE("freq_cap_interval_type",
            new String[] { "name#VARCHAR(100)#false", "type#VARCHAR(100)#false" },
            new String[] { "type#UNIQUE#type" },
            null),
    COST_METHOD("cost_method",
            new String[] { "code#VARCHAR(10)#false", "description#VARCHAR(500)" },
            new String[] { "code#UNIQUE#code" },
            null),
    BUDGET_TYPE("budget_type",
            new String[] { "name#VARCHAR(100)#false", "description#VARCHAR(500)" },
            null,
            null),
    ACCOUNT_TYPE("account_type",
            new String[] { "name#VARCHAR(100)#false", "description#VARCHAR(500)" },
            null,
            null),
    ACCOUNT("account",
            new String[] { "name#VARCHAR(100)#false", "description#VARCHAR(500)", "account_type_id#INT#false" },
            null,
            new String[] { "account_type_id#account_type#id" }),
    ADVERTISER("advertiser",
            new String[] { "name#VARCHAR(100)#false", "description#VARCHAR(500)", "account_id#INT#false" },
            null,
            new String[] { "account_id#account#id" }),
    CREATIVE("creative",
            new String[] { "name#VARCHAR(100)#false", "description#VARCHAR(500)", "format#VARCHAR(10)", "duration#INT" },
            null,
            null),
    DEAL_PRICING_TYPE("deal_pricing_type",
            new String[] { "name#VARCHAR(100)#false" },
            null,
            null),
    DEAL("deal",
            new String[] { "name#VARCHAR(100)#false", "code#VARCHAR(100)", "description#VARCHAR(500)",
                    "deal_pricing_type_id#INT#false" },
            new String[] { "name#UNIQUE#name" },
            new String[] { "deal_pricing_type_id#deal_pricing_type#id" }),
    BUDGET("budget",
            new String[] { "name#VARCHAR(100)#false", "description#VARCHAR(500)", "value#INT", "start_date#DATE",
                    "end_date#DATE", "currency_id#INT#false", "deal_pricing_type_id#INT#false",
                    "pacing_type_id#INT#false", "cost_method_id#INT#false", "optimization_goal_type_id#INT#false", 
                    "buying_type_id#INT#false", "freq_cap_interval_type_id#INT#false" },
            null,
            new String[] { "deal_pricing_type_id#deal_pricing_type#id", "currency_id#currency#id",
                    "pacing_type_id#pacing_type#id", "optimization_goal_type_id#optimization_goal_type#id",
                    "buying_type_id#buying_type#id", "freq_cap_interval_type_id#freq_cap_interval_type#id",
                    "cost_method_id#cost_method#id" }),
    ADVERTISEMENT("advertisement",
            new String[] { "name#VARCHAR(100)#false", "description#VARCHAR(500)", "creative_id#INT#false" },
            null,
            new String[] { "creative_id#creative#id" }),
    ADVERTISER_ADVERTISEMENT("advertiser_advertisement",
            new String[] { "advertiser_id#INT#false", "advertisement_id#INT#false" },
            null,
            new String[] { "advertiser_id#advertiser#id", "advertisement_id#advertisement#id" }),
    ADVERTISER_BLACKLIST("advertiser_blacklist",
            new String[] { "reason#VARCHAR(500)", "advertiser_id#INT#false" },
            null,
            new String[] { "advertiser_id#advertiser#id" }),
    TARGET("target",
            new String[] { "name#VARCHAR(100)#false", "description#VARCHAR(500)", "type#VARCHAR(100)#false",
                    "type_id#INT#false", "advertiser_id#INT#false", "ad_unit_id#INT#false" },
            new String[] { "type_type_id_advertiser_ad_unit#UNIQUE#type,type_id,advertiser_id,ad_unit_id" },
            new String[] { "advertiser_id#advertiser#id", "ad_unit_id#ad_unit#id" }),
    CAMPAIGN("campaign", // TODO check why advertiser id?
            new String[] { "name#VARCHAR(100)#false", "description#VARCHAR(500)", "type#VARCHAR(5)", "value#INT",
                    "start_date#DATE", "end_date#DATE", "time_zone_id#INT#false", "advertiser_id#INT#false", "budget_id#INT#false" }, // added timezone
            null,
            new String[] { "advertiser_id#advertiser#id", "budget_id#budget#id", "time_zone_id#time_zone#id" }),
    CAMPAIGN_TARGET("campaign_target",
            new String[] { "type#VARCHAR(100)#false", "campaign_id#INT#false", "target_id#INT#false" },
            null,
            new String[] { "campaign_id#campaign#id", "target_id#target#id" }),
            
            
    USER("user", 
            new String[] { "fname#VARCHAR(100)#false", "mname#VARCHAT(100)", "lname#VARCHAR(100)#false", "email#VARCHAR(200)#false", 
                    "join_date#DATE", "last_login_date#DATE"}, 
            new String[] { "email#UNIQUE#email" },
            null),
    ROLE("role", 
            new String[] { "name#VARCHAR(100)#false", "description#VARCHAR(500)"}, 
            new String[] { "name#UNIQUE#name" },
            null),
    USER_ROLE("user_role", 
            new String[] { "user_id#INT#false", "role_id#INT#false"}, 
            null,
            new String[] { "user_id#user#id", "role_id#role#id"}),

    PREFERENCE("preference", 
            new String[] { "name#VARCHAR(100)#false", "description#VARCHAR(500)"}, 
            new String[] { "name#UNIQUE#name" },
            null),
    USER_PREFERENCE("user_preference", 
            new String[] { "user_id#INT#false", "preference_id#INT#false"}, 
            null,
            new String[] { "user_id#user#id", "preference_id#preference#id"}),

    PRIVILEGE("privilege", 
            new String[] { "name#VARCHAR(100)#false", "description#VARCHAR(500)"}, 
            new String[] { "name#UNIQUE#name" },
            null),
    ROLE_PRIVILEGE("role_privilege", 
            new String[] { "role_id#INT#false", "privilege_id#INT#false"}, 
            null,
            new String[] { "role_id#role#id", "privilege_id#privilege#id"}),

    FEATURE("feature", 
            new String[] { "name#VARCHAR(100)#false", "description#VARCHAR(500)"}, 
            new String[] { "name#UNIQUE#name" },
            null),
    FEATURE_PRIVILEGE("feature_privilege", 
            new String[] { "feature_id#INT#false", "privilege_id#INT#false"}, 
            null,
            new String[] { "feature_id#feature#id", "privilege_id#privilege#id"}),
    
    ACCOUNT_FEATURE("account_feature", 
            new String[] { "account_id#INT#false", "feature_id#INT#false"}, 
            null,
            new String[] { "account_id#account#id", "feature_id#feature#id"}),

    PRODUCT("product", 
            new String[] { "name#VARCHAR(100)#false", "description#VARCHAR(500)", "launch_date#DATE#false"}, 
            new String[] { "name#UNIQUE#name" },
            null),
    ACCOUNT_PRODUCT("account_product", 
            new String[] { "account_id#INT#false", "product_id#INT#false"}, 
            null,
            new String[] { "account_id#account#id", "product_id#product#id"}),
    ;

    private final String name;
    private final String[] cols;
    private final String[] cons;
    private final String[] keys;

    private WmAdTableMine(final String name, final String[] cols, final String[] cons, final String[] keys) {
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
        return table;
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

}
