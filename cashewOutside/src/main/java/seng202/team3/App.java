package seng202.team3;

import com.google.gson.Gson;
import org.joda.money.Money;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        System.out.println( "Hello World!" );

        MyObject myObject = new MyObject();
        Gson gson = new Gson();
        String jsonString = gson.toJson(myObject);

        System.out.println(jsonString);
    }
}
