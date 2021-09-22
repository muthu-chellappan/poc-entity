package poc.cache;

public enum CacheHelper {

    ACTIVE_CAMPAIGNS("Campaign", false) {
        public String[] getAttrs() {
            return new String[] { "Id", "Name", "Description", "StartDate", "EndDate", "CampaignTypeId", "DealId",
                    "TimeZoneId", "AdvertiserId", "CampaignBudgetId", "CostMethodId", "Active" };
        }
    },
    ACTIVE_FLIGHTS("Flight", false) {
        public String[] getAttrs() {
            return new String[] { "Id", "Name", "Description", "CampaignId", "FlightBudgetId", "CostMethodId",
                    "Active"};
        }
    },
    AD_ZONE_FLIGHTS("AdZone", true) {
        public String[] getAttrs() {
            return new String[] {"Name"};
        }
    },
    SALES_REGION_FLIGHTS("SalesRegion", true) {
        public String[] getAttrs() {
            return new String[] {"Name"};
        }
    },
    BRAND_REGION_FLIGHTS("BrandRegion", true) {
        public String[] getAttrs() {
            return new String[] {"Name"};
        }
    },
    AD_ZONE_LOCATION_FLIGHTS("AdZoneLocation", true) {
        public String[] getAttrs() {
            return new String[] {"Name"};
        }
    },
    BROWSER_FLIGHTS("Browser", true) {
        public String[] getAttrs() {
            return new String[] { "Name" };
        }
    },
    DEVICE_FLIGHTS("Device", true) {
        public String[] getAttrs() {
            return new String[] { "DeviceType", "DeviceMake", "DeviceModel" };
        }

        public boolean isTransient() {
            return true;
        }
    },
    GEO_FLIGHTS("Geo", true) {
        public String[] getAttrs() {
            return new String[] { "CountryCode", "StateCode", "CityCode", "PostalCode" };
        }

        public boolean isTransient() {
            return true;
        }
    },
    KEYWORDS_FLIGHTS("Keywords", true) {
        public String[] getAttrs() {
            return new String[] { "Keyword" };
        }
    },
    OS_FLIGHTS("Os", true) {
        public String[] getAttrs() {
            return new String[] { "Name" };
        }
    };

    private final String type;
    private final boolean targets;

    private CacheHelper(final String type, final boolean targets) {
        this.type = type;
        this.targets = targets;
    }

    public abstract String[] getAttrs();

    public String getType() {
        return type;
    }

    public String getTypePackage() {
        return type.toLowerCase();
    }

    public String getCacheType() {
        return name();
    }

    public boolean hasTargets() {
        return targets;
    }

    public String getMutator(final String attr) {
        return attr.split(":")[0];
    }

    public String getAccessor(final String attr) {
        final String accessor = attr.split(":")[1];
        if (accessor.indexOf(".") >= 0) {
            final String[] tokens = accessor.split("\\.");
            final StringBuilder builder = new StringBuilder(tokens[0]);
            for (int i = 1; i < tokens.length; i++) {
                builder.append("().get").append(tokens[i]).append("()");
            }
            return builder.toString();
        }
        return accessor;
    }

    public boolean isTransient() {
        return false;
    }

}
