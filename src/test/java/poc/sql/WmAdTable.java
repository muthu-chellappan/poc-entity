package poc.sql;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum WmAdTable {

    COUNTRY("country",
            new String[] { "name#VARCHAR(100)#false", "code#VARCHAR(3)#false"},
            new String[] { "name#UNIQUE#name", "code#UNIQUE#code" },
            null),
    STATE("state",
            new String[] { "name#VARCHAR(100)#false", "code#VARCHAR(3)", "country_id#INT#false" },
            null,
            new String[] { "country_id#country#id" }){
        public List<Query> getQueries() {
            final List<Query> queries = new ArrayList<>();
            queries.add(new Query("ActiveStatesByCountryIds",
                    "(where: {is_deleted: {_eq: false}, country_id: {_in: \" + countryIds + \"}})",
                    "countryIds"));
            return queries;
        }
    },
    CITY("city",
            new String[] { "name#VARCHAR(100)#false", "code#VARCHAR(3)", "state_id#INT#false" },
            null,
            new String[] { "state_id#state#id" }){
        public List<Query> getQueries() {
            final List<Query> queries = new ArrayList<>();
            queries.add(new Query("ActiveCitiesByStateIds",
                    "(where: {is_deleted: {_eq: false}, state_id: {_in: \" + stateIds + \"}})",
                    "stateIds"));
            return queries;
        }
    },
    POSTAL_CODE("postal_code",
            new String[] { "code#VARCHAR(100)#false", "description#VARCHAR(100)", "city_id#INT#false" },
            null,
            new String[] { "city_id#city#id" }){
        public List<Query> getQueries() {
            final List<Query> queries = new ArrayList<>();
            queries.add(new Query("ActivePostalsByCityIds",
                    "(where: {is_deleted: {_eq: false}, city_id: {_in: \" + cityIds + \"}})",
                    "cityIds"));
            return queries;
        }
    },
    CURRENCY("currency",
            new String[] { "name#VARCHAR(100)#false", "code#VARCHAR(3)#false", "country_id#INT#false" },
            null,
            new String[] { "country_id#country#id" }),
    TIME_ZONE("time_zone",
            new String[] { "name#VARCHAR(100)#false", "code#VARCHAR(3)#false", "utc_offset#INT#false" },
            new String[] { "name#UNIQUE#name", "code#UNIQUE#code" },
            null),
    IAB_CATEGORY("iab_category",
            new String[] { "name#VARCHAR(100)#false", "code#VARCHAR(10)#false", "description#VARCHAR(500)" },
            new String[] { "code#UNIQUE#code" },
            null),
    IAB_SUB_CATEGORY("iab_sub_category",
            new String[] { "name#VARCHAR(100)#false", "code#VARCHAR(10)#false", "description#VARCHAR(500)",
                    "iab_category_id#INT#false" },
            new String[] { "code#UNIQUE#code" },
            new String[] { "iab_category_id#iab_category#id" }),
    DEVICE_TYPE("device_type",
            new String[] { "name#VARCHAR(100)#false", "description#VARCHAR(100)" },
            new String[] { "name#UNIQUE#name" },
            null),
    DEVICE_MAKE("device_make",
            new String[] { "make#VARCHAR(100)#false", "brand#VARCHAR(100)", "device_type_id#INT#false" },
            new String[] { "make_brand#UNIQUE#make,brand" },
            new String[] { "device_type_id#device_type#id" }){
        public List<Query> getQueries() {
            final List<Query> queries = new ArrayList<>();
            queries.add(new Query("ActiveDeviceMakesByDeviceTypeIds",
                    "(where: {is_deleted: {_eq: false}, device_type_id: {_in: \" + deviceTypeIds + \"}})",
                    "deviceTypeIds"));
            return queries;
        }
    },
    DEVICE_MODEL("device_model",
            new String[] { "name#VARCHAR(100)#false", "model#VARCHAR(100)#false", "description#VARCHAR(500)",
                    "device_make_id#INT#false" },
            new String[] { "model_device_id#UNIQUE#model,device_make_id" },
            new String[] { "device_make_id#device_make#id" }){
        public List<Query> getQueries() {
            final List<Query> queries = new ArrayList<>();
            queries.add(new Query("ActiveDeviceModelsByDeviceMakeIds",
                    "(where: {is_deleted: {_eq: false}, device_make_id: {_in: \" + deviceMakeIds + \"}})",
                    "deviceMakeIds"));
            return queries;
        }
    },
    BROWSER("browser",
            new String[] { "name#VARCHAR(100)#false", "description#VARCHAR(500)" },
            new String[] { "name#UNIQUE#name" },
            null),
    OS("os",
            new String[] { "name#VARCHAR(100)#false", "description#VARCHAR(500)" },
            new String[] { "name#UNIQUE#name" },
            null),
    AD_ZONE("ad_zone",
            new String[] { "name#VARCHAR(100)#false", "description#VARCHAR(500)", "ad_count#INT#false#1" },
            new String[] { "name#UNIQUE#name" },
            null),
    AD_ZONE_LOCATION("ad_zone_location",
            new String[] { "name#VARCHAR(100)#false", "description#VARCHAR(500)" },
            new String[] { "name#UNIQUE#name" },
            null),
    KEYWORD_CATEGORY("keyword_category",
            new String[] { "name#VARCHAR(500)#false", "keyword#VARCHAR(500)#false", "is_published#BOOLEAN", "group_name#VARCHAR(500)", "mutually_exclusive#BOOLEAN" },
            new String[] { "keyword#UNIQUE#keyword" },
            null),
    KEYWORD_BRAND("keyword_brand",
            new String[] { "name#VARCHAR(500)#false", "keyword#VARCHAR(500)#false", "is_published#BOOLEAN", "is_premium#BOOLEAN" },
            new String[] { "keyword#UNIQUE#keyword" },
            null),
    KEYWORD_TAG("keyword_tag",
            new String[] { "name#VARCHAR(500)#false", "keyword#VARCHAR(500)#false", "is_published#BOOLEAN", "tag_group#VARCHAR(500)" },
            null,
            null),
    SALES_REGION("sales_region",
            new String[] { "name#VARCHAR(500)#false", "keyword#VARCHAR(500)#false"},
            new String[] { "keyword#UNIQUE#keyword" },
            null),
    BRAND_REGION("brand_region",
            new String[] { "brand_id#INT#false", "sales_region_id#INT#false", "brand_region_name#VARCHAR(500)#false", "sales_region_name#VARCHAR(500)#false"},
            null,
            new String[] { "brand_id#keyword_brand#id", "sales_region_id#sales_region#id"}),
    DAY_PART("day_part",
            new String[] { "name#VARCHAR(100)#false"},
            new String[] { "name#UNIQUE#name" },
            null),
    PACING_TYPE("pacing_type",
            new String[] { "name#VARCHAR(100)#false", "type#VARCHAR(100)#false" },
            new String[] { "type#UNIQUE#type" },
            null),
    BUYING_TYPE("buying_type",
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
    ADVERTISEMENT("advertisement",
            new String[] { "name#VARCHAR(100)#false", "description#VARCHAR(500)", "advertiser_id#INT#false", "choose_performing_creative#boolean#false" },
            null,
            new String[] { "advertiser_id#advertiser#id" }),
    CREATIVE_FORMAT("creative_format",
            new String[] { "type#VARCHAR(15)#false", "width#INT#false",  "height#INT#false"},
            new String[] { "type_width_height#UNIQUE#type,width,height" },
            null),
    CREATIVE("creative",
            new String[] { "name#VARCHAR(100)#false", "description#VARCHAR(500)", "creative_format_id#INT#false",
                    "advertisement_id#INT#false", "click_thru_url#VARCHAR(1000)#false", "wm_type#VARCHAR(100)",
                    "alt_text#VARCHAR(1000)", "disclosure#VARCHAR(1000)", "theme#varchar(100)" },
            null,
            new String[] { "creative_format_id#creative_format#id", "advertisement_id#advertisement#id" }){
        public List<Query> getQueries() {
            final List<Query> queries = new ArrayList<>();
            queries.add(new Query("ActiveCreativesByAdvertisementIds",
                    "(where: {is_deleted: {_eq: false}, advertisement_id: {_in: \" + adIds + \"}})",
                    "adIds"));
            return queries;
        }
    },
    ASSET("asset",
            new String[] { "name#VARCHAR(100)#false", "location_url#VARCHAR(4000)#false", "width#INT#false",
                    "height#INT#false", "duration#INT",
                    "creative_id#INT#false"},
            null,
            new String[] { "creative_id#creative#id"}){
        public List<Query> getQueries() {
            final List<Query> queries = new ArrayList<>();
            queries.add(new Query("ActiveAssetsByCreativeIds",
                    "(where: {is_deleted: {_eq: false}, creative_id: {_in: \" + creativeIds + \"}})",
                    "creativeIds"));
            return queries;
        }
    },
    DEAL_TYPE("deal_type",
            new String[] { "type#VARCHAR(15)#false" },
            new String[] { "type#UNIQUE#type" },
            null),
    DEAL("deal",
            new String[] { "name#VARCHAR(100)#false", "deal_type_id#INT#false", "start_date#DATE#false", "end_date#DATE#false" },
            new String[] { "name#UNIQUE#name" },
            new String[] { "deal_type_id#deal_type#id" }),
    BUDGET_DURATION("budget_duration",
            new String[] { "duration_value#VARCHAR(10)#false" },
            null,
            null),
    CAMPAIGN_BUDGET("campaign_budget",
            new String[] { "description#VARCHAR(500)", "campaign_budget_amount#INT", "start_date#DATE", "end_date#DATE",
                    "currency_id#INT#false", "budget_duration_id#INT#false", "pacing_type_id#INT#false",
                    "cost_method_id#INT#false", "buying_type_id#INT#false", "freq_cap_interval_type_id#INT#false",
                    "cost_price#NUMERIC(15,6)#false", "floor_price#NUMERIC(15,6)#false" },
            null,
            new String[] { "currency_id#currency#id", "budget_duration_id#budget_duration#id",
                    "pacing_type_id#pacing_type#id", "buying_type_id#buying_type#id",
                    "freq_cap_interval_type_id#freq_cap_interval_type#id", "cost_method_id#cost_method#id" }),
    FLIGHT_BUDGET("flight_budget",
            new String[] { "description#VARCHAR(500)", "flight_budget_amount#INT", "start_date#DATE", "end_date#DATE",
                    "budget_duration_id#INT#false", "pacing_type_id#INT#false", "cost_method_id#INT", 
                    "freq_cap_interval_type_id#INT#false", "cost_price#NUMERIC(15,6)#false", "floor_price#NUMERIC(15,6)#false" },
            null,
            new String[] { "budget_duration_id#budget_duration#id",
                    "pacing_type_id#pacing_type#id", "freq_cap_interval_type_id#freq_cap_interval_type#id",
                    "cost_method_id#cost_method#id" }),
    ADVERTISER_BLACKLIST("advertiser_blacklist",
            new String[] { "reason#VARCHAR(500)", "advertiser_id#INT#false" },
            null,
            new String[] { "advertiser_id#advertiser#id" }),
    TARGET("target",
            new String[] { "name#VARCHAR(100)#false", "description#VARCHAR(500)", "target_type#VARCHAR(100)#false",
                    "target_value_id#INT#false", "advertiser_id#INT#false"},
            new String[] {
                    "type_type_id_advertiser#UNIQUE#target_type,target_value_id,advertiser_id" },
            new String[] { "advertiser_id#advertiser#id" }){
        public List<Query> getQueries() {
            final List<Query> queries = new ArrayList<>();
            queries.add(new Query("ActiveTargetsByTypeAndIds",
                    "(where: {is_deleted: {_eq: false}, target_type: {_eq: \\\"\" + type\r\n"
                    + "                + \"\\\"}, target_value_id: {_in: \" + targetIds + \"}})",
                    "String:type", "targetIds"));
            return queries;
        }
    },
    CAMPAIGN_TYPE("campaign_type",
            new String[] { "campaign_type_value#VARCHAR(100)#false" },
            new String[] { "campaign_type_value#UNIQUE#campaign_type_value" },
            null),
    CAMPAIGN("campaign",
            new String[] { "name#VARCHAR(100)#false", "description#VARCHAR(500)", "start_date#DATE", "end_date#DATE",
                    "campaign_type_id#INT#false", "deal_id#INT", "time_zone_id#INT#false", "advertiser_id#INT#false",
                    "campaign_budget_id#INT#false", "cost_method_id#INT#false" },
            null,
            new String[] { "campaign_type_id#campaign_type#id", "deal_id#deal#id", "advertiser_id#advertiser#id",
                    "campaign_budget_id#campaign_budget#id", "time_zone_id#time_zone#id", "cost_method_id#cost_method#id" }),
    FLIGHT("flight",
            new String[] { "name#VARCHAR(100)#false", "description#VARCHAR(500)", "campaign_id#INT#false",
                    "flight_budget_id#INT", "cost_method_id#INT" },
            null,
            new String[] { "campaign_id#campaign#id", "flight_budget_id#flight_budget#id", "cost_method_id#cost_method#id" }),
    FLIGHT_ADVERTISEMENT("flight_advertisement",
            new String[] { "flight_id#INT#false", "advertisement_id#INT#false" },
            new String[] { "flight_id_advertisement_id#UNIQUE#flight_id,advertisement_id" },
            new String[] { "flight_id#flight#id", "advertisement_id#advertisement#id" }) {
        public List<Query> getQueries() {
            final List<Query> queries = new ArrayList<>();
            queries.add(new Query("ActiveFlightAdvertisementByFlightIds",
                    "(where: {is_deleted: {_eq: false}, flight_id: {_in: \" + flightIds + \"}})", "flightIds"));
            return queries;
        }
    },
    FLIGHT_TARGET("flight_target",
            new String[] { "flight_id#INT#false", "target_id#INT#false" },
            new String[] { "flight_id_target_id#UNIQUE#flight_id,target_id" },
            new String[] { "flight_id#flight#id", "target_id#target#id" }){
        public List<Query> getQueries() {
            final List<Query> queries = new ArrayList<>();
            queries.add(new Query("FlightsByTargetIds",
                    "(where: {is_deleted: {_eq: false}, target_id: {_in: \" + targetIds + \"}})",
                     "targetIds"));
            return queries;
        }
    },
    OS_TARGET("os_target",
            new String[] { "os_id#INT#false" },
            new String[] { "os_id#UNIQUE#os_id" },
            new String[] { "os_id#os#id"}){
        public List<Query> getQueries() {
            final List<Query> queries = new ArrayList<>();
            queries.add(new Query("TargetsByOsIds",
                    "(where: {is_deleted: {_eq: false}, os_id: {_in: \" + osIds + \"}})",
                    "osIds"));
            return queries;
        }
    },
    BROWSER_TARGET("browser_target",
            new String[] { "browser_id#INT#false" },
            new String[] { "browser_id#UNIQUE#browser_id" },
            new String[] { "browser_id#browser#id"}){
        public List<Query> getQueries() {
            final List<Query> queries = new ArrayList<>();
            queries.add(new Query("TargetsByBrowserIds",
                    "(where: {is_deleted: {_eq: false}, browser_id: {_in: \" + browserIds + \"}})",
                    "browserIds"));
            return queries;
        }
    },
    AD_ZONE_TARGET("ad_zone_target",
            new String[] { "ad_zone_id#INT#false" },
            new String[] { "ad_zone_id#UNIQUE#ad_zone_id" },
            new String[] { "ad_zone_id#ad_zone#id"}){
        public List<Query> getQueries() {
            final List<Query> queries = new ArrayList<>();
            queries.add(new Query("TargetsByAdZoneIds",
                    "(where: {is_deleted: {_eq: false}, ad_zone_id: {_in: \" + adZoneIds + \"}})",
                    "adZoneIds"));
            return queries;
        }
    },
    AD_ZONE_LOCATION_TARGET("ad_zone_location_target",
            new String[] { "ad_zone_location_id#INT#false" },
            new String[] { "ad_zone_location_id#UNIQUE#ad_zone_location_id" },
            new String[] { "ad_zone_location_id#ad_zone_location#id"}){
        public List<Query> getQueries() {
            final List<Query> queries = new ArrayList<>();
            queries.add(new Query("TargetsByAdZoneLocationIds",
                    "(where: {is_deleted: {_eq: false}, ad_zone_location_id: {_in: \" + adZoneLocationIds + \"}})",
                    "adZoneLocationIds"));
            return queries;
        }
    },
    KEYWORDS_TARGET("keywords_target",
            new String[] { "keyword_id#INT#false", "keyword_type#VARCHAR(50)#false" },
            new String[] { "keyword_type_keyword_id#UNIQUE#keyword_type,keyword_id" },
            null){
        public List<Query> getQueries() {
            final List<Query> queries = new ArrayList<>();
            queries.add(new Query("TargetsByKeywordTypeAndIds",
                    "(where: {is_deleted: {_eq: false}, keyword_type: {_eq: \\\"\" + type + \"\\\"}, keyword_id: {_in: \" + keywordIds + \"}})",
                     "String:type", "keywordIds"));
            return queries;
        }
    },
    DAY_PART_TARGET("day_part_target",
            new String[] { "day_part_id#INT#false" },
            new String[] { "day_part_id#UNIQUE#day_part_id" },
            new String[] { "day_part_id#day_part#id"}){
        public List<Query> getQueries() {
            final List<Query> queries = new ArrayList<>();
            queries.add(new Query("TargetsByDayPartIds",
                    "(where: {is_deleted: {_eq: false}, day_part_target_id: {_in: \" + dayPartIds + \"}})",
                    "dayPartIds"));
            return queries;
            
        }
    },
    SALES_REGION_TARGET("sales_region_target",
            new String[] { "sales_region_id#INT#false" },
            new String[] { "sales_region_id#UNIQUE#sales_region_id" },
            new String[] { "sales_region_id#sales_region#id"}){
        public List<Query> getQueries() {
            final List<Query> queries = new ArrayList<>();
            queries.add(new Query("TargetsBySalesRegionIds",
                    "(where: {is_deleted: {_eq: false}, sales_region_id: {_in: \" + salesRegionIds + \"}})",
                    "salesRegionIds"));
            return queries;
            
        }
    },
    BRAND_REGION_TARGET("brand_region_target",
            new String[] { "brand_region_id#INT#false" },
            new String[] { "brand_region_id#UNIQUE#brand_region_id" },
            new String[] { "brand_region_id#brand_region#id"}){
        public List<Query> getQueries() {
            final List<Query> queries = new ArrayList<>();
            queries.add(new Query("TargetsByBrandRegionIds",
                    "(where: {is_deleted: {_eq: false}, brand_region_id: {_in: \" + brandRegionIds + \"}})",
                    "brandRegionIds"));
            return queries;
            
        }
    },
    GEO_TARGET("geo_target",
            new String[] { "sales_country_id#INT", "sales_state_id#INT", "sales_city_id#INT", "sales_postal_code_id#INT" },
            new String[] { "sales_country_state_city_pc#UNIQUE#sales_country_id,sales_state_id,sales_city_id,sales_postal_code_id" },
            new String[] { "sales_country_id#country#id", "sales_state_id#state#id",
                    "sales_city_id#city#id", "sales_postal_code_id#postal_code#id" }) {
        public List<Query> getQueries() {
            final List<Query> queries = new ArrayList<>();
            queries.add(new Query("ActiveGeoTargetByCountryIdsOrStateIdsOrCityIdsOrPostalIds",
                    "(where: {is_deleted: {_eq: false}, _or: [\"\r\n"
                            + "                + \"                       {sales_city_id: {_in: \" + cityIds + \"}},\"\r\n"
                            + "                + \"                       {sales_country_id: {_in: \" + countryIds + \"}},\"\r\n"
                            + "                + \"                       {sales_postal_code_id: {_in: \" + postalIds + \"}},\"\r\n"
                            + "                + \"                       {sales_state_id: {_in: \" + stateIds + \"}}]})",
                    "countryIds", "stateIds", "cityIds", "postalIds"));
            return queries;
        }
    },
    DEVICE_TARGET("device_target",
            new String[] { "device_type_id#INT#false","device_make_id#INT#false", "device_model_id#INT#false" },
            new String[] { "device_type_make_model_type#UNIQUE#device_type_id,device_make_id,device_model_id" },
            new String[] { "device_type_id#device_type#id","device_make_id#device_make#id", "device_model_id#device_model#id" }){
        public List<Query> getQueries() {
            final List<Query> queries = new ArrayList<>();
            queries.add(new Query("ActiveDeviceTargetByDeviceTypeIdsOrDeviceMakeIdsOrDeviceModelIds",
                    "(where: {is_deleted: {_eq: false}, _or: [\"\r\n"
                    + "                + \"                       {device_type_id: {_in: \" + deviceTypeIds + \"}},\"\r\n"
                    + "                + \"                       {device_make_id: {_in: \" + deviceMakeIds + \"}},\"\r\n"
                    + "                + \"                       {device_model_id: {_in: \" + deviceModelIds + \"}}]})",
                    "deviceTypeIds", "deviceMakeIds", "deviceModelIds"));
            return queries;
        }
    },
    USER("user", 
            new String[] { "fname#VARCHAR(100)#false", "mname#VARCHAR(100)", "lname#VARCHAR(100)#false", "email#VARCHAR(200)#false", 
                    "account_id#INT#false", "join_date#DATE", "last_login_date#DATE"}, 
            new String[] { "email#UNIQUE#email" },
            new String[] { "account_id#account#id"}),
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
            new String[] { "account_id#account#id", "product_id#product#id"});

    private final String name;
    private final String[] cols;
    private final String[] cons;
    private final String[] keys;

    private WmAdTable(final String name, final String[] cols, final String[] cons, final String[] keys) {
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
