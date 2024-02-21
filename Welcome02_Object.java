import core.data.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Welcome02_Object {
   public static void main(String[] args) {
      ArrayList<String> objects = new ArrayList<String>();
      ArrayList<Observation> places = new ArrayList<Observation>();
      Scanner input = new Scanner(System.in);
      
      String id1 = "KATL";
      objects.add(id1);
      DataSource ds1 = DataSource.connect("http://weather.gov/xml/current_obs/" + id1 + ".xml"); 
      //ds1.setCacheTimeout(15 * 60);  
      ds1.load();
      ds1.printUsageString();

      Observation ob1 = ds1.fetch("Observation", "weather", "temp_f", "wind_degrees");
      places.add(ob1);
      System.out.println(id1 + ": " + ob1);
      
      String id2 = "KSAV";
      objects.add(id2);
      DataSource ds2 = DataSource.connect("http://weather.gov/xml/current_obs/" + id2 + ".xml"); 
      //ds2.setCacheTimeout(15 * 60);  
      ds2.load();
      
      Observation ob2 = ds2.fetch("Observation", "weather", "temp_f", "wind_degrees");
      places.add(ob2);
      System.out.println(id2 + ": " + ob2);

      String id3 = "PHMK";
      objects.add(id3);
      DataSource ds3 = DataSource.connect("http://weather.gov/xml/current_obs/" + id3 + ".xml"); 
      //ds3.setCacheTimeout(15 * 60);  
      ds3.load();
      
      Observation ob3 = ds3.fetch("Observation", "weather", "temp_f", "wind_degrees");
      places.add(ob3);
      System.out.println(id3 + ": " + ob3);
      
      Observation coldest = null;
      String coldesT = null;
      for (int i =0; i < places.size(); i++){
         if (coldest == null || places.get(i).colderThan(coldest)){
            coldest = places.get(i);
            coldesT = objects.get(i);
         }

      }
      System.out.println("Colder at " + coldesT);

      DataSource stns = DataSource.connect("http://weather.gov/xml/current_obs/index.xml");
      stns.load();
      stns.printUsageString();

      //fetches an array of station ids and prints them all out
      /*String[] ids = stns.fetchStringArray("station/station_id");
      System.out.println(ids.length);

      //prints the first and last id
      System.out.println(ids[0]);
      System.out.println(ids[ids.length - 1]);

      //fetches two more arrays of data
      String[] urls = stns.fetchStringArray("station/xml_url");
      String[] states = stns.fetchStringArray("station/state");
      System.out.println(states.length);*/



      //scanner id reader
      /*System.out.println("Enter an id from the website to get weather data for: ");
      String id4 = input.nextLine();
      objects.add(id4);
      DataSource ds4 = DataSource.connect("http://weather.gov/xml/current_obs/" + id4 + ".xml"); 
      //ds4.setCacheTimeout(15 * 60);  
      ds4.load();
            
      Observation ob4 = ds4.fetch("Observation", "weather", "temp_f", "wind_degrees");
      places.add(ob4);
      System.out.println(id4 + ": " + ob4);
      
      System.out.println("Enter another id from the website to get weather data for: ");
      String id5 = input.nextLine();
      objects.add(id5);
      DataSource ds5 = DataSource.connect("http://weather.gov/xml/current_obs/" + id5 + ".xml"); 
      //ds4.setCacheTimeout(15 * 60);  
      ds5.load();

      Observation ob5 = ds5.fetch("Observation", "weather", "temp_f", "wind_degrees");
      places.add(ob5);
      System.out.println(id5 + ": " + ob5);

      //prints out which location is hotter
      if (ob4.colderThan(ob5)) {
         System.out.println("Hotter at " + id5);
      } else {
         System.out.println("Hotter at " + id4);
      }*/

      
   }
}


/* Represents a weather observation */
class Observation {
   float temp;    // in fahrenheit
   int windDir;   // in degrees
   String description;
   
   Observation(String description, float temp, int windDir) {
      this.description = description;
      this.temp = temp;
      this.windDir = windDir;
   }
   
   /* determine if the temperature of this observation is colder than 'that's */
   public boolean colderThan(Observation that) {
      return this.temp < that.temp;
   }
   
   /* produce a string describing this observation */
   public String toString() {
      return (temp + " degrees; The weather is " + description.toLowerCase() + ". (wind: " + windDir + " degrees)");
   }
}