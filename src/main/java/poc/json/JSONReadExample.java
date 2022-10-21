package poc.json;

import java.io.FileReader;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
  
public class JSONReadExample 
{
    public static void main(String[] args) throws Exception 
    {
        // parsing file "JSONExample.json"
        Object obj = new JSONParser().parse(new FileReader("C:\\projects\\wmd\\test-script\\prod-data-kevel\\Kevel-Campaign-Resp.json"));
          
        // typecasting obj to JSONObject
        JSONObject jo = (JSONObject) obj;
          
        // getting firstName and lastName
        Long campagignId = (Long) jo.get("Id");
        String name = (String) jo.get("Name");
//        String lastName = (String) jo.get("lastName");
          
        System.out.println("Campaign NAme --> " + name);
        System.out.println("Campaign ID --> " + campagignId);
          
        // getting age
//        long age = (long) jo.get("age");
//        System.out.println(age);
          
//        // getting address
//        Map address = ((Map)jo.get("address"));
//          
//        // iterating address Map
//        Iterator<Map.Entry> itr1 = address.entrySet().iterator();
//        while (itr1.hasNext()) {
//            Map.Entry pair = itr1.next();
//            System.out.println(pair.getKey() + " : " + pair.getValue());
//        }
          
        // getting phoneNumbers
        JSONArray ja = (JSONArray) jo.get("Flights");
          
        // iterating phoneNumbers
        Iterator itr2 = ja.iterator();
          
        while (itr2.hasNext()) 
        {
            JSONObject flObj = (JSONObject) itr2.next();
            boolean active = "true".equalsIgnoreCase(flObj.get("IsActive").toString());
            Object endDate = flObj.get("EndDate");
            System.out.println("*****************************************");
            System.out.println("Flight Id -->" + flObj.get("Id") + " Active -->" + active + " endDate -->" + flObj.get("EndDateISO"));
            if(active && endDate == null) {
                System.out.println("Flight Id -->" + flObj.get("Id"));
                System.out.println("Flight API URL -->" + "https://api.kevel.co/v1/flight/"+flObj.get("Id"));
                System.out.println("Flight Name -->" + flObj.get("Name"));
               // System.out.println("Keywords -->" + flObj.get("Keywords"));
            }
            System.out.println("*****************************************");
            
//            itr1 = ((Map) itr2.next()).entrySet().iterator();
//            while (itr1.hasNext()) {
//                Map.Entry pair = itr1.next();
//                if(pair.getKey().equals("IsActive")) {
//                    Boolean active = "true".equalsIgnoreCase(pair.getValue().toString()); 
//                    
//                }
//                System.out.println(pair.getKey() + " : " + pair.getValue());
//            }
        }
    }
}