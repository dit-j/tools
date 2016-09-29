package de.jawb.tools.iso;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    
    public static List<String> getContent(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        List<String> content = new ArrayList<String>();
        String line;
        while ((line = br.readLine()) != null) {
            content.add(line);
        }
        br.close();
        
        return content;
    }
    
    static int find(String[] arr, String what) {
        for (int i = 0; i < arr.length; i++) {
            if (what.equals(arr[i])) {
                return i;
            }
        }
        return -1;
        
    }
    
    static Map<String, String> parsePairs(String file) throws IOException{
        List<String> contryConts = getContent(new File(file));
        Map<String, String> map = new HashMap<String, String>();
        for (String l : contryConts) {
            String[] pair = l.toLowerCase().split(":");
            String key = pair[0].trim();
            String value = "";
            if(pair.length == 2){
                value = pair[1].trim();
            }
            map.put(key, value);
        }
        return map;
    }
    
    static Map<String, String> parseContryCapitals() throws IOException{
        List<String> contryConts = getContent(new File("c:\\country_capital.txt"));
        Map<String, String> map = new HashMap<String, String>();
        for (String l : contryConts) {
            String[] pair = l.split(":");
            String iso2 = pair[0].toLowerCase().trim();
            String capital = pair[1].trim();
            
            if(capital.length() > 1){
                capital = capital.substring(0, capital.length() - 1);
            } else {
                capital = "";
            }
            
            map.put(iso2, capital);
        }
        return map;
    }
    
    
    static Map<String, String> parseContryContinent() throws IOException{
        List<String> contryConts = getContent(new File("c:\\country_continent.txt"));
        Map<String, String> map = new HashMap<String, String>();
        for (String l : contryConts) {
            
            String[] pair = l.split(":");
            
            String country = pair[0].trim().toLowerCase();
            String continent = pair[1].trim().toLowerCase();
            
            map.put(country, Continent.getByCode(continent).name());
            
            
        }
        
        return map;
    }
    
    
    static Map<String, String> parseCurrencyNames() throws IOException{
        List<String> lines = getContent(new File("c:\\country_currency_names.txt"));
        Map<String, String> map = new HashMap<String, String>();
        
        for (String l : lines) {
            
            String[] pair = l.split("\t");
            
            if(pair.length == 4){
                String key   = pair[3].trim().toLowerCase();
                String value = pair[2].trim();
                map.put(key, value);
            } else {
                System.err.println(l);
            }
        }
        return map;
    }
    
    
    public static void main(String[] args) throws IOException {
        
        List<String> contryNames = getContent(new File("c:\\country_names.txt"));
        Map<String, String> conts = parseContryContinent();
        Map<String, String> isos = parsePairs("c:\\countryISO2_countryISO3.txt");
        Map<String, String> phones = parsePairs("c:\\country_phone.txt");
        Map<String, String> capits = parseContryCapitals();

        Map<String, String> currency = parsePairs("c:\\country_currency.txt");
        Map<String, String> currNames = parseCurrencyNames();
        
        String templ = "%s(\"%s\", \"%s\", \"%s\", \"%s\", \"%s\", Currency.%s, Continent.%s),";
        
        for (String l : contryNames) {
            
            String[] pair = l.replaceAll("\"", "").split(":");
            
            String name         = pair[1].trim();
            String name2        = name.replaceAll(" ", "_").replaceAll("-", "").replaceAll("\\.", "");
            String iso2         = pair[0].toLowerCase();
            String iso3         = isos.get(iso2);
            String capital      = capits.get(iso2);
            String continent    = conts.get(iso2);
            String phone        = phones.get(iso2);
            String currencyCode = currency.get(iso2).trim();
            
            String currencyName = currencyCode.length() > 0 ?  Currency.getByCode(currencyCode).name(): "Unknown";
            
            String format = String.format(templ, name2, name, iso2, iso3, capital, phone, currencyName, continent);
            System.out.println(format);
        }
        
    }
    
    //
    // http://www.xe.com/symbols.php
    //
    
    
    
//    static String hexCodeToUnicode(String hex) {
//        int l = 4 - hex.length();
//        String app = "";
//        for (int i = 0; i < l; i++) {
//            app += "0";
//        }
//        return "\\u" + app + hex;
//    }
//    
//    public static void main(String[] args) throws Exception {
//        
//        String f = "f:\\Desktop\\s.txt";
//        String t = "f:\\Desktop\\t.txt";
////        System.out.println(Character.toString((char) 0x60b));
//        
////        List<String> lines = FileAccess.getContent(new File(f));
////        for (String l : lines) {
////            FileAccess.writeString(new File(t), l.replaceAll("<td", "\n<td").replaceAll("<tr", "\n<tr"), false);
////        }
//        
//        List<String> lines = FileAccess.getContent(new File(t));
//        List<String> list = new ArrayList<>();
//        Map<String, String> map = new HashMap<>();
//        int c = 0;
//        String name = "";
//        String code = "";
//        String val = "";
//        for (String l : lines) {
//            if (l.startsWith("<tr")) {
//                c = 0;
//            } else if (l.startsWith("<td")) {
//                c++;
//            }
//            
//            if (c == 1) {
//                name = l.replaceAll("<td>", "").replaceAll("</td>", "");
//                name = name.substring(name.indexOf('>') + 1).replaceAll("</a>", "");
//            } else if (c == 2) {
//                code = l.replaceAll("<td>", "").replaceAll("</td>", "").toLowerCase();
//            } else if (c == 7) {
//                val = l.replaceAll("<td>", "").replaceAll("</td>", "");
//                
//                if (val.length() > 0) {
//                    
//                    String[] hexs = val.split(",");
//                    String value = "";
//                    for (String h : hexs) {
//                        value += hexCodeToUnicode(h.trim());
//                    }
//                    
//                    val = value;
//                } else {
//                    val = name;
//                }
//                
//                map.put(code, val);
//                list.add(code + "=" + val);
//                
//            }
//        }
//
//        for (Currency m : Currency.values()) {
//            
//            String sym = map.get(m.code);
//            
//            if(sym == null){
//                sym = m.name;
//            }
//            
////            System.out.println("currency.Full." + m.code + " = " + m.name);
//            System.out.println("currency.Short." +m.code + " = " + sym);
//            
//        }
//        
//    }
    
}
