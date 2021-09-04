-- Insert into generation
INSERT INTO campaign_type (created_by,updated_by,campaign_type_value) VALUES ('admin','admin','AdSales'); - DIRECT_SOLD
INSERT INTO campaign_type (created_by,updated_by,campaign_type_value) VALUES ('admin','admin','Marketplace');

INSERT INTO buying_type (created_by,updated_by,name,type) VALUES ('admin','admin','Sponsorship','Sponsorship');
INSERT INTO buying_type (created_by,updated_by,name,type) VALUES ('admin','admin','Premium','Premium');
INSERT INTO buying_type (created_by,updated_by,name,type) VALUES ('admin','admin','Standard','Standard');

INSERT INTO cost_method (created_by,updated_by,code,description) VALUES ('admin','admin','CPM','Cost Per Milli');
INSERT INTO cost_method (created_by,updated_by,code,description) VALUES ('admin','admin','CPC','Cost Per Click');
INSERT INTO cost_method (created_by,updated_by,code,description) VALUES ('admin','admin','CPA','Cost Per Action');

INSERT INTO ad_zone (created_by,updated_by,name,description) VALUES ('admin','admin','Homepage','Homepage');
INSERT INTO ad_zone (created_by,updated_by,name,description) VALUES ('admin','admin','Other','Other');

INSERT INTO ad_zone_location (created_by,updated_by,name,description) VALUES ('admin','admin','deliveries-listing-cards','deliveries-listing-cards');
INSERT INTO ad_zone_location (created_by,updated_by,name,description) VALUES ('admin','admin','homepage-card-row-deliveries','homepage-card-row-deliveries');
INSERT INTO ad_zone_location (created_by,updated_by,name,description) VALUES ('admin','admin','homepage-card-row-dispensaries','homepage-card-row-dispensaries');
INSERT INTO ad_zone_location (created_by,updated_by,name,description) VALUES ('admin','admin','map-listing-cards','map-listing-cards');
INSERT INTO ad_zone_location (created_by,updated_by,name,description) VALUES ('admin','admin','homepage-carousel-1','homepage-carousel-1');
INSERT INTO ad_zone_location (created_by,updated_by,name,description) VALUES ('admin','admin','homepage-carousel-2','homepage-carousel-2');
INSERT INTO ad_zone_location (created_by,updated_by,name,description) VALUES ('admin','admin','homepage-carousel-3','homepage-carousel-3');
INSERT INTO ad_zone_location (created_by,updated_by,name,description) VALUES ('admin','admin','homepage-carousel-4','homepage-carousel-4');
INSERT INTO ad_zone_location (created_by,updated_by,name,description) VALUES ('admin','admin','homepage-promo-tile-big','homepage-promo-tile-big');
INSERT INTO ad_zone_location (created_by,updated_by,name,description) VALUES ('admin','admin','homepage-promo-tile-small','homepage-promo-tile-small');
INSERT INTO ad_zone_location (created_by,updated_by,name,description) VALUES ('admin','admin','oserp-banner-top','oserp-banner-top');
INSERT INTO ad_zone_location (created_by,updated_by,name,description) VALUES ('admin','admin','products-banner-top','products-banner-top');
INSERT INTO ad_zone_location (created_by,updated_by,name,description) VALUES ('admin','admin','sale-carousel','sale-carousel');
INSERT INTO ad_zone_location (created_by,updated_by,name,description) VALUES ('admin','admin','serp-banner-top','serp-banner-top'); 
change this to ad_zome

INSERT INTO device_type (created_by,updated_by,name,description) VALUES ('admin','admin','Mobile','Mobile');
INSERT INTO device_type (created_by,updated_by,name,description) VALUES ('admin','admin','Desktop','Desktop');
                
INSERT INTO budget_duration (created_by,updated_by,duration_value) VALUES ('admin','admin','Daily');
INSERT INTO budget_duration (created_by,updated_by,duration_value) VALUES ('admin','admin','Lifetime');

IOS, Andriod - OS

Chrome, IE, Safari, Firefox - browser


Role
----
Admin
Campaign_Manager


Drop user_account
add account_id in user table

Just keep account & user, advertiser.

creative_Format
change column format to type (display, native 0,0, video 0,0)
width 
height

300 x 250 --large
728 x 90  --medium
120 x 60  --small

targetting - keyword_targeting


advertisement
add field choose_performing_creative boolean default false


remove campaigh_budget_id from flight_budget table

remove browser_version, os_version tables
add table os_target

deal_type
id
type ( Private, PMP)

deal
-----
id 
name
deal_type_id
start_Date
end_date


Add column deal_id in campaign table - nullable column

remove locale table..

Keywords
--------
id
keyword
delimiter

keywords_target
--------------

daypart
-------
id
name (earlymorning,morning)

daypart_target


