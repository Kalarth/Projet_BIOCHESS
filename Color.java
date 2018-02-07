import java.util.*;
import java.io.*;

public abstract class Color{
 private static Hashtable<String,String> colors = new Hashtable<String,String>();

 public static  void setColors(){
   colors.put("RESET","\u001B[0m");
   colors.put("BLACK","\u001B[30m");
   colors.put("RED","\u001B[31m");
   colors.put("GREEN","\u001B[32m");
   colors.put("YELLOW","\u001B[33m");
   colors.put("BLUE","\u001B[34m");
   colors.put("PURPLE","\u001B[35m");
   colors.put("CYAN","\u001B[36m");
   colors.put("WHITE","\u001B[37m");
}

  public static String colorTxt(String text,String color){
    String colorx = colors.get(color);
    return (colorx + text + colors.get("RESET"));
  }
}

////////////////////////////Autre////////////////////////////////
