/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tripadvisor;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import  com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.rdf.model.Model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import org.apache.jena.iri.impl.Main;

/**
 *
 * @author user
 */
public class TripAdvisor {
    /**
     * @param args the command line arguments
     */
    static boolean budget =true;
    static String source,destination;
    static void type(String name){
        //FileManager.get().addLocatorClassLoader(Main.class.getClassLoader());
        Model model = FileManager.get().loadModel("TripAdviser.rdf");
        String queryString = 
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>"+
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"+
                "PREFIX trip: <http://www.semanticweb.org/user/ontologies/2015/3/untitled-ontology-7#>"+
                "SELECT ?"+name+"\n"+
                    "WHERE { ?"+name+" ?p trip:"+name+"}";
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        try{
            ResultSet Results = qexec.execSelect();
            ResultSetFormatter.out(System.out,Results,query);
        }finally{
            qexec.close();
        }
    }
    static void leisure_subclass(){
        System.out.println("Which sub-activity under leisure are you doing?");
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        String input = scanner.nextLine();
        String queryString;
        Query query;
        QueryExecution qexec;
        Model model = FileManager.get().loadModel("TripAdviser.rdf");
        switch(input.toLowerCase()){
            case "relax":
                queryString = 
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                    "PREFIX trip: <http://www.semanticweb.org/user/ontologies/2015/3/untitled-ontology-7#>\n" +
                        "SELECT ?RelaxType\n" +
                            "WHERE { trip:Relax trip:is_part_of ?RelaxType}";
                query = QueryFactory.create(queryString);
                qexec = QueryExecutionFactory.create(query, model);
                try{
                    ResultSet Results = qexec.execSelect();
                    ResultSetFormatter.out(System.out,Results,query);
                }finally{
                    qexec.close();
                }
                System.out.println("Which park? (case sensitive) ");
                park();
                break;
            case "dining":
                System.out.println("Which hotel? (case sensitive) ");
                //Model model = FileManager.get().loadModel("TripAdviser.rdf");
                queryString = 
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                    "PREFIX trip: <http://www.semanticweb.org/user/ontologies/2015/3/untitled-ontology-7#>\n" +
                        "SELECT ?Hotel\n" +
                            "WHERE { trip:Dining trip:is_part_of ?Hotel}";
                query = QueryFactory.create(queryString);
                qexec = QueryExecutionFactory.create(query, model);
                try{
                    ResultSet Results = qexec.execSelect();
                    ResultSetFormatter.out(System.out,Results,query);
                }finally{
                    qexec.close();
                }
                break;

            default:
                System.out.println("invalid choice");
                break;
        }
        //String source = scanner.nextLine();
        //System.out.println("Source = "+source);
    }
    static void attraction_subClass(){
        System.out.println("Which attraction are you in?");
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        String input = scanner.nextLine();
        String queryString;
        Query query;
        QueryExecution qexec;
        Model model = FileManager.get().loadModel("TripAdviser.rdf");
        switch(input.toLowerCase()){
            case "park":
                System.out.println("Which park? (case sensitive) ");
                park();
                break;
            case "hall":
                System.out.println("Where is the hall? (case sensitive) ");
                //Model model = FileManager.get().loadModel("TripAdviser.rdf");
                queryString = 
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                    "PREFIX trip: <http://www.semanticweb.org/user/ontologies/2015/3/untitled-ontology-7#>\n" +
                        "SELECT ?Hall\n" +
                            "WHERE { trip:Hall trip:has_hall ?Hall}";
                query = QueryFactory.create(queryString);
                qexec = QueryExecutionFactory.create(query, model);
                try{
                    ResultSet Results = qexec.execSelect();
                    ResultSetFormatter.out(System.out,Results,query);
                }finally{
                    qexec.close();
                }
                break;
                case "cityscape":
                System.out.println("Where is the cityscape located? (case sensitive) ");
                //Model model = FileManager.get().loadModel("TripAdviser.rdf");
                queryString = 
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                    "PREFIX trip: <http://www.semanticweb.org/user/ontologies/2015/3/untitled-ontology-7#>\n" +
                        "SELECT ?Place\n" +
                            "WHERE { trip:Cityscape trip:has_cityscape ?Place}";
                query = QueryFactory.create(queryString);
                qexec = QueryExecutionFactory.create(query, model);
                try{
                    ResultSet Results = qexec.execSelect();
                    ResultSetFormatter.out(System.out,Results,query);
                }finally{
                    qexec.close();
                }
                break;
                    case "adventure":
                System.out.println("Where are you? (case sensitive) ");
                //Model model = FileManager.get().loadModel("TripAdviser.rdf");
                queryString = 
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                    "PREFIX trip: <http://www.semanticweb.org/user/ontologies/2015/3/untitled-ontology-7#>\n" +
                        "SELECT ?Place\n" +
                            "WHERE { trip:Adventure trip:type_of_adventure ?Place}";
                query = QueryFactory.create(queryString);
                qexec = QueryExecutionFactory.create(query, model);
                try{
                    ResultSet Results = qexec.execSelect();
                    ResultSetFormatter.out(System.out,Results,query);                
                }finally{
                    qexec.close();
                }
                break;

            default:
                System.out.println("invalid choice");
                break;
        }
    }
    static void music_subclass(){
        System.out.println("Where are you attending the concert?");
        Model model = FileManager.get().loadModel("TripAdviser.rdf");
        String queryString = 
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>"+
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"+
                "PREFIX trip: <http://www.semanticweb.org/user/ontologies/2015/3/untitled-ontology-7#>"+
                "SELECT ?Hall "+
                    "WHERE {trip:Concert trip:is_part_of ?Hall}";
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        try{
            ResultSet Results = qexec.execSelect();
            ResultSetFormatter.out(System.out,Results,query);
        }finally{
            qexec.close();
        }
    }
    static void places(){
        System.out.println("Where are you?");
        Model model = FileManager.get().loadModel("TripAdviser.rdf");
        String queryString = 
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>"+
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"+
                "PREFIX trip: <http://www.semanticweb.org/user/ontologies/2015/3/untitled-ontology-7#>"+
                "SELECT ?Place "+
                    "WHERE {trip:Places_of_Historical_Importance trip:has_history_of_city ?Place}";
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        try{
            ResultSet Results = qexec.execSelect();
            ResultSetFormatter.out(System.out,Results,query);
        }finally{
            qexec.close();
        }
    }
    static void museum(){
        System.out.println("Please select the Museum you are in.");
        Model model = FileManager.get().loadModel("TripAdviser.rdf");
        String queryString = 
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>"+
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"+
                "PREFIX trip: <http://www.semanticweb.org/user/ontologies/2015/3/untitled-ontology-7#>"+
                "SELECT ?Museum "+
                    "WHERE {trip:Museum trip:has_museum ?Museum}";
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        try{
            ResultSet Results = qexec.execSelect();
            ResultSetFormatter.out(System.out,Results,query);
        }finally{
            qexec.close();
        }
    }
    static void park(){
        Model model = FileManager.get().loadModel("TripAdviser.rdf");
        String queryString = 
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>"+
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"+
                "PREFIX trip: <http://www.semanticweb.org/user/ontologies/2015/3/untitled-ontology-7#>"+
                "SELECT ?Park "+
                    "WHERE {trip:Park trip:has_park ?Park}";
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        try{
            ResultSet Results = qexec.execSelect();
            ResultSetFormatter.out(System.out,Results,query);
        }finally{
            qexec.close();
        }        
    }
    public static void main(String[] args) throws IOException {
        
        // TODO code application logic here
        FileManager.get().addLocatorClassLoader(Main.class.getClassLoader());
        Model model = FileManager.get().loadModel("TripAdviser.rdf");
        String cont;
        do{
            System.out.println("Please select the heuristic you want to follow :");
            System.out.println("1. Straight Line Distance");
            System.out.println("2. Straight Line Distance + Entry Fee");
            Scanner scanner = new Scanner(new InputStreamReader(System.in));
            String input = scanner.nextLine();
            switch(input){
                case "1":
                    budget = false;
                    break;
                case "2":
                    budget = true;
                    break;  
                default:
                    System.exit(0);
            }
           // String source;
            System.out.println("Which activity are you currently pursuing?");
            System.out.println("1. Leisure");
            System.out.println("2. Music");
            System.out.println("3. History of City");
            System.out.println("4. Attraction");
            System.out.println("5. Art");
            String str;
        
            String choice = scanner.nextLine();
            switch(choice.toLowerCase()){
                case "leisure":
                    str = "Leisure";
                    type(str);
                    leisure_subclass();
                    break;
                case "music":
                    str = "Music";
                    type(str);
                    music_subclass();
                    break;
                case "history of city":
                    str = "History_of_City";
                    type(str);
                    places();
                    break;
                case "attraction":
                    str = "Attraction";
                    type(str);
                    attraction_subClass();
                    break;
                case "art":
                    str = "Art";
                    type(str);
                    museum();
                    break;
                default:
                    System.out.println("invalid choice");
                    System.exit(0);
                    break;
            }
            System.out.println("Which one? (case sensitive)");
            source = scanner.nextLine();
            System.out.println("Source : "+source);
        
            dijikstra(source);
            
            AstarSearchEvent.runAStar(source, destination);
        
            System.out.println("Do you want to continue?");
            cont = scanner.nextLine();
        }while(cont.equalsIgnoreCase("yes"));
    }
    static void dijikstra(String source){
        int sum=0;
        Map<String,Double> weights = new TreeMap<String,Double>();
        Map<String,Double> destinationMap = new TreeMap<String,Double>();
        String[] destinationName = new String[13];
        Map<String,Integer> placesList = new HashMap<String,Integer>();
        int lineNo=0;
        try {
            if(source.equalsIgnoreCase("Reunion_Tower_Geo_Deck")||source.equalsIgnoreCase("DallasSkyline")){
                source = "Reunion_Tower";
            }
            File file = new File("Trip_Advisor.txt");
            FileWriter fileWritter = new FileWriter(file.getName());
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            BufferedReader r = new BufferedReader(new FileReader("TripAdvisor_Data1.csv"));
            String line = r.readLine();
            if (line != null) {
                do {
            
                    String rowData[] = line.split(",");// Split the text file rows using tab spacing.
                    if(rowData[0].equalsIgnoreCase("Source")){
                        for(int i=1;i<=12;i++){
                
                            int j=i-1;
                            destinationName[j]= rowData[i];
                        }
                    }
               // System.out.println("source");

        //System.out.println(rowData[1]);
                    if(source.equalsIgnoreCase(rowData[0])){
                        for(int i=1;i<=12;i++){
                
                            String dist[] = rowData[i].split(":");
                            int j =i-1;
              //  System.out.println("distnce:"+dist[0]);
               // System.out.println("rowdata:"+rowData[i]);
                            destinationMap.put(destinationName[j], Double.parseDouble(dist[0]));
                
                        }
           // System.out.println(rowData[1]+"\t"+rowData[2]+"\t"+rowData[3]+"\t"+rowData[4]+"\t"+rowData[5]+"\t"+rowData[6]+"\t"+rowData[7]+"\t"+rowData[8]+"\t"+rowData[9]+"\t"+rowData[10]+"\t"+rowData[11]+"\t"+rowData[12]);
                    } 
                    line = r.readLine();
                } while (line != null);
                for(Object o:destinationMap.keySet()){
                    String destName = o.toString();
                    double dist= destinationMap.get(o);
            //System.out.println(destName+""+dist);
                }
            }
            System.out.println("Which activity do you want to do now?");
            System.out.println("1. Leisure");
            System.out.println("2. Music");
            System.out.println("3. History of City");
            System.out.println("4. Attraction");
            System.out.println("5. Art");
            Scanner scanner = new Scanner(new InputStreamReader(System.in));
            String choice = scanner.nextLine();
            String str;
            int i=0;
            double min =Double.POSITIVE_INFINITY;
       // double[] dist1=null;
            switch(choice.toLowerCase()){
                case "leisure":
                    str = "Leisure";
                    type(str);
                    placesList=leisure_sub_class();
                    for(Object s:placesList.keySet()){
                        double dist1 = destinationMap.get(s);
                        int entreeFee = placesList.get(s);
                        if((dist1+entreeFee)<min){
                            min= (dist1+entreeFee);
                        }
                    //System.out.println("dist :"+dist1);
                    }
                    for(Object s:placesList.keySet()){
                        double dist1 = destinationMap.get(s);
                        if(dist1==min){
                            System.out.println("Destination: "+s);
                            destination = s.toString();
                        }
                    //System.out.println("dist :"+dist1);
                    }
                    break;
                case "music":
                    str = "Music";
                    type(str);
                    //
                    placesList=music_sub_class();
                    for(Object s:placesList.keySet()){
                        double dist1 = destinationMap.get(s);
                        int entreeFee = placesList.get(s);
                        if((dist1+entreeFee)<min){
                            min= (dist1+entreeFee);
                        }
                    //System.out.println("dist :"+dist1);
                    }
                    for(Object s:placesList.keySet()){
                        double dist1 = destinationMap.get(s);
                        if(dist1==min){
                            System.out.println("Destination : "+s);
                            destination = s.toString();
                        }
                    //System.out.println("dist :"+dist1);
                    }
                //music_subclass();
                    break;
                case "history of city":
                    str = "History_of_City";
                    type(str);
                    //
                    placesList=history_sub_class();
                    
                    for(Object s:placesList.keySet()){
                        double dist1 = destinationMap.get(s);
                        int entreeFee = placesList.get(s);
                        if((dist1+entreeFee)<min){
                            min= (dist1+entreeFee);
                        }
                    //System.out.println("dist :"+dist1);
                    }
                    for(Object s:placesList.keySet()){
                        double dist1 = destinationMap.get(s);
                        if(dist1==min){
                            System.out.println("Destination : "+s);
                            destination = s.toString();
                        }
                    //System.out.println("dist :"+dist1);
                    }
                //places();
                    break;
                case "attraction":
                    str = "Attraction";
                    type(str);
                    //
                    placesList=attraction_sub_class();
                    
                    for(Object s:placesList.keySet()){
                        double dist1 = destinationMap.get(s);
                        int entreeFee = placesList.get(s);
                        if((dist1+entreeFee)<min){
                            min= (dist1+entreeFee);
                        }
                    //System.out.println("dist :"+dist1);
                    }
                    for(Object s:placesList.keySet()){
                        double dist1 = destinationMap.get(s);
                        if(dist1==min){
                            System.out.println("Destination : "+s);
                            destination = s.toString();
                        }
                    //System.out.println("dist :"+dist1);
                    }
                //attraction_subClass();
                    break;
                case "art":
                    str = "Art";
                    type(str);
                    //
                    
                    placesList=art_sub_class();
                    
                    for(Object s:placesList.keySet()){
                        double dist1 = destinationMap.get(s);
                        int entreeFee = placesList.get(s);
                        if((dist1+entreeFee)<min){
                            min= (dist1+entreeFee);
                        }
                    //System.out.println("dist :"+dist1);
                    }
                    for(Object s:placesList.keySet()){
                        double dist1 = destinationMap.get(s);
                        if(dist1==min){
                            System.out.println("Destination : "+s);
                            destination = s.toString();
                        }
                    //System.out.println("dist :"+dist1);
                    }
                //museum();
                    
                    break;
                    
                default:
                    System.out.println("invalid choice");
                    System.exit(0);
            }
        
        } catch (IOException e) {
        System.out.println(e);
        }
    }
    static HashMap<String,Integer> leisure_sub_class(){
        HashMap<String,Integer> list1 = new HashMap<String,Integer>();
        System.out.println("Which sub-activity under leisure do you want to do?");
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        String input = scanner.nextLine();
        String queryString;
        Query query;
        QueryExecution qexec;
        Model model = FileManager.get().loadModel("TripAdviser.rdf");
        switch(input.toLowerCase()){
            case "relax":
                queryString = 
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                    "PREFIX trip: <http://www.semanticweb.org/user/ontologies/2015/3/untitled-ontology-7#>\n" +
                        "SELECT ?RelaxType\n" +
                            "WHERE { trip:Relax trip:is_part_of ?RelaxType}";
                query = QueryFactory.create(queryString);
                qexec = QueryExecutionFactory.create(query, model);
                try{
                    ResultSet Results = qexec.execSelect();
                    ResultSetFormatter.out(System.out,Results,query);
                }finally{
                    qexec.close();
                }
                //System.out.println("Which park? (case sensitive) ");
               list1= park1();
                break;
            case "dining":
                list1= dining1();
                break;

            default:
                System.out.println("invalid choice");
                break;
        }
        //String source = scanner.nextLine();
        //System.out.println("Source = "+source);
        return list1;
    }
    static HashMap<String,Integer> music_sub_class(){
        HashMap<String,Integer> list1 = new HashMap<String,Integer>();
        System.out.println("Which sub-activity under music do you want to do?");
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        String input = scanner.nextLine();
        String queryString;
        Query query;
        QueryExecution qexec;
        Model model = FileManager.get().loadModel("TripAdviser.rdf");
        switch(input.toLowerCase()){
            case "concert":
                
                list1= concert1();
                break;

            default:
                System.out.println("invalid choice");
                break;
        }
        //String source = scanner.nextLine();
        //System.out.println("Source = "+source);
        return list1;
    }
    static HashMap<String,Integer> attraction_sub_class(){
        HashMap<String,Integer> list1 = new HashMap<String,Integer>();
        System.out.println("Which sub-activity under attraction do you want to do?");
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        String input = scanner.nextLine();
        String queryString;
        Query query;
        QueryExecution qexec;
        Model model = FileManager.get().loadModel("TripAdviser.rdf");
        switch(input.toLowerCase()){
            case "adventure":
                
                list1= adventure1();
                break;
            case "cityscape":
                
                list1= cityscape1();
                break;
            case "hall":
                
                list1= hall1();
                break;
            case "park":
                
                list1= park1();
                break;

            default:
                System.out.println("invalid choice");
                break;
        }
        //String source = scanner.nextLine();
        //System.out.println("Source = "+source);
        return list1;
    }
    static HashMap<String,Integer> art_sub_class(){
        HashMap<String,Integer> list1 = new HashMap<String,Integer>();
        System.out.println("Which sub-activity under art do you want to do?");
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        String input = scanner.nextLine();
        String queryString;
        Query query;
        QueryExecution qexec;
        Model model = FileManager.get().loadModel("TripAdviser.rdf");
        switch(input.toLowerCase()){
            case "museum":
                
                list1= museum1();
                break;

            default:
                System.out.println("invalid choice");
                break;
        }
        //String source = scanner.nextLine();
        //System.out.println("Source = "+source);
        return list1;
    }
    static HashMap<String,Integer> history_sub_class(){
        HashMap<String,Integer> list1 = new HashMap<String,Integer>();
        System.out.println("Which sub-activity under History of City do you want to do?");
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        String input = scanner.nextLine();
        String queryString;
        Query query;
        QueryExecution qexec;
        Model model = FileManager.get().loadModel("TripAdviser.rdf");
        switch(input.toLowerCase()){
            case "places_of_historical_importance":
                
                list1= historical1();
                break;

            default:
                System.out.println("invalid choice");
                break;
        }
        //String source = scanner.nextLine();
        //System.out.println("Source = "+source);
        return list1;
    }
    static HashMap<String,Integer> park1(){
        HashMap<String,Integer> places1 = new HashMap<String,Integer>();
        Model model = FileManager.get().loadModel("TripAdviser.rdf");
        String queryString = 
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>"+
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"+
                "PREFIX trip: <http://www.semanticweb.org/user/ontologies/2015/3/untitled-ontology-7#>"+
                "SELECT ?Park "+
                    "WHERE {trip:Park trip:has_park ?Park}";
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        try{
            ResultSet Results = qexec.execSelect();
            while(Results.hasNext()){
                QuerySolution qr = Results.next();
               // System.out.println(qr.toString());
                String val[] = qr.toString().split("#");
                String subVal[] = val[1].split(">");
              // System.out.println("subval[o]:"+subVal[0]);
                if(budget){
                    int entreeFee=0;
                    String q = 
                            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
                            "PREFIX owl: <http://www.w3.org/2002/07/owl#>"+
                            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
                            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"+
                            "PREFIX trip: <http://www.semanticweb.org/user/ontologies/2015/3/untitled-ontology-7#>"+
                                "SELECT *"+
                            "WHERE { trip:"+subVal[0]+" trip:entry_fee ?c}";
                    Query qu = QueryFactory.create(q);
                    QueryExecution qex = QueryExecutionFactory.create(qu, model);
                    ResultSet Res = qex.execSelect();
                    while(Res.hasNext()){
                        QuerySolution qs = Res.next();
                       // System.out.println(qs.toString());
                        String[] v1 = qs.toString().split("\"");
                        //System.out.print("v1:"+v1[1]+"subval:"+subVal[0]);
                        entreeFee=Integer.parseInt(v1[1]);
                        //System.out.println(entreeFee);
                        places1.put(subVal[0], entreeFee);
                    }
                    
                    
                }
                else{
                    places1.put(subVal[0], 0);
                }
            }
            return places1;
           // ResultSetFormatter.out(System.out,Results,query);
        }finally{
            qexec.close();
        }        
    }
    static HashMap<String,Integer> dining1(){
        HashMap<String,Integer> places1 = new HashMap<String,Integer>();
        Model model = FileManager.get().loadModel("TripAdviser.rdf");
        String queryString = 
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>"+
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"+
                "PREFIX trip: <http://www.semanticweb.org/user/ontologies/2015/3/untitled-ontology-7#>"+
                "SELECT ?Hotel "+
                    "WHERE {trip:Dining trip:is_part_of ?Hotel}";
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        try{
            ResultSet Results = qexec.execSelect();
            while(Results.hasNext()){
                QuerySolution qr = Results.next();
               // System.out.println(qr.toString());
                String val[] = qr.toString().split("#");
                String subVal[] = val[1].split(">");
              // System.out.println("subval[o]:"+subVal[0]);
                if(budget){
                    int entreeFee=0;
                    String q = 
                            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
                            "PREFIX owl: <http://www.w3.org/2002/07/owl#>"+
                            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
                            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"+
                            "PREFIX trip: <http://www.semanticweb.org/user/ontologies/2015/3/untitled-ontology-7#>"+
                                "SELECT *"+
                            "WHERE { trip:"+subVal[0]+" trip:entry_fee ?c}";
                    Query qu = QueryFactory.create(q);
                    QueryExecution qex = QueryExecutionFactory.create(qu, model);
                    ResultSet Res = qex.execSelect();
                    while(Res.hasNext()){
                        QuerySolution qs = Res.next();
                        //System.out.println(qs.toString());
                        String[] v1 = qs.toString().split("\"");
                        //System.out.print("v1:"+v1[1]+"subval:"+subVal[0]);
                        entreeFee=Integer.parseInt(v1[1]);
                        places1.put(subVal[0], entreeFee);
                    }
                    
                    
                }
                else{
                    places1.put(subVal[0], 0);
                }
            }
            return places1;
           // ResultSetFormatter.out(System.out,Results,query);
        }finally{
            qexec.close();
        }        
    }
    static HashMap<String,Integer> concert1(){
        HashMap<String,Integer> places1 = new HashMap<String,Integer>();
        Model model = FileManager.get().loadModel("TripAdviser.rdf");
        String queryString = 
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>"+
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"+
                "PREFIX trip: <http://www.semanticweb.org/user/ontologies/2015/3/untitled-ontology-7#>"+
                "SELECT ?Hall "+
                    "WHERE {trip:Concert trip:is_part_of ?Hall}";
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        try{
            ResultSet Results = qexec.execSelect();
            while(Results.hasNext()){
                QuerySolution qr = Results.next();
               // System.out.println(qr.toString());
                String val[] = qr.toString().split("#");
                String subVal[] = val[1].split(">");
              // System.out.println("subval[o]:"+subVal[0]);
                if(budget){
                    int entreeFee=0;
                    String q = 
                            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
                            "PREFIX owl: <http://www.w3.org/2002/07/owl#>"+
                            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
                            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"+
                            "PREFIX trip: <http://www.semanticweb.org/user/ontologies/2015/3/untitled-ontology-7#>"+
                                "SELECT *"+
                            "WHERE { trip:"+subVal[0]+" trip:entry_fee ?c}";
                    Query qu = QueryFactory.create(q);
                    QueryExecution qex = QueryExecutionFactory.create(qu, model);
                    ResultSet Res = qex.execSelect();
                    while(Res.hasNext()){
                        QuerySolution qs = Res.next();
                        //System.out.println(qs.toString());
                        String[] v1 = qs.toString().split("\"");
                        //System.out.print("v1:"+v1[1]+"subval:"+subVal[0]);
                        entreeFee=Integer.parseInt(v1[1]);
                        places1.put(subVal[0], entreeFee);
                    }
                    
                    
                }
                else{
                    places1.put(subVal[0], 0);
                }
            }
            return places1;
           // ResultSetFormatter.out(System.out,Results,query);
        }finally{
            qexec.close();
        }        
    }
    static HashMap<String,Integer> historical1(){
        HashMap<String,Integer> places1 = new HashMap<String,Integer>();
        Model model = FileManager.get().loadModel("TripAdviser.rdf");
        String queryString = 
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>"+
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"+
                "PREFIX trip: <http://www.semanticweb.org/user/ontologies/2015/3/untitled-ontology-7#>"+
                "SELECT ?Place "+
                    "WHERE {trip:Places_of_Historical_Importance trip:has_history_of_city ?Place}";
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        try{
            ResultSet Results = qexec.execSelect();
            while(Results.hasNext()){
                QuerySolution qr = Results.next();
               // System.out.println(qr.toString());
                String val[] = qr.toString().split("#");
                String subVal[] = val[1].split(">");
              // System.out.println("subval[o]:"+subVal[0]);
                if(budget){
                    int entreeFee=0;
                    String q = 
                            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
                            "PREFIX owl: <http://www.w3.org/2002/07/owl#>"+
                            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
                            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"+
                            "PREFIX trip: <http://www.semanticweb.org/user/ontologies/2015/3/untitled-ontology-7#>"+
                                "SELECT *"+
                            "WHERE { trip:"+subVal[0]+" trip:entry_fee ?c}";
                    Query qu = QueryFactory.create(q);
                    QueryExecution qex = QueryExecutionFactory.create(qu, model);
                    ResultSet Res = qex.execSelect();
                    while(Res.hasNext()){
                        QuerySolution qs = Res.next();
                        //System.out.println(qs.toString());
                        String[] v1 = qs.toString().split("\"");
                        System.out.print("v1:"+v1[1]+"subval:"+subVal[0]);
                        entreeFee=Integer.parseInt(v1[1]);
                        places1.put(subVal[0], entreeFee);
                    }
                    
                    
                }
                else{
                    places1.put(subVal[0], 0);
                }
            }
            return places1;
           // ResultSetFormatter.out(System.out,Results,query);
        }finally{
            qexec.close();
        }        
    }
    static HashMap<String,Integer> museum1(){
        HashMap<String,Integer> places1 = new HashMap<String,Integer>();
        Model model = FileManager.get().loadModel("TripAdviser.rdf");
        String queryString = 
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>"+
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"+
                "PREFIX trip: <http://www.semanticweb.org/user/ontologies/2015/3/untitled-ontology-7#>"+
                "SELECT ?Museum "+
                    "WHERE {trip:Museum trip:has_museum ?Museum}";
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        try{
            ResultSet Results = qexec.execSelect();
            while(Results.hasNext()){
                QuerySolution qr = Results.next();
               // System.out.println(qr.toString());
                String val[] = qr.toString().split("#");
                String subVal[] = val[1].split(">");
              // System.out.println("subval[o]:"+subVal[0]);
                if(budget){
                    int entreeFee=0;
                    String q = 
                            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
                            "PREFIX owl: <http://www.w3.org/2002/07/owl#>"+
                            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
                            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"+
                            "PREFIX trip: <http://www.semanticweb.org/user/ontologies/2015/3/untitled-ontology-7#>"+
                                "SELECT *"+
                            "WHERE { trip:"+subVal[0]+" trip:entry_fee ?c}";
                    Query qu = QueryFactory.create(q);
                    QueryExecution qex = QueryExecutionFactory.create(qu, model);
                    ResultSet Res = qex.execSelect();
                    while(Res.hasNext()){
                        QuerySolution qs = Res.next();
                        //System.out.println(qs.toString());
                        String[] v1 = qs.toString().split("\"");
                        //System.out.print("v1:"+v1[1]+"subval:"+subVal[0]);
                        entreeFee=Integer.parseInt(v1[1]);
                        places1.put(subVal[0], entreeFee);
                    }
                    
                    
                }
                else{
                    places1.put(subVal[0], 0);
                }
            }
            return places1;
           // ResultSetFormatter.out(System.out,Results,query);
        }finally{
            qexec.close();
        }        
    }
    static HashMap<String,Integer> adventure1(){
        HashMap<String,Integer> places1 = new HashMap<String,Integer>();
        Model model = FileManager.get().loadModel("TripAdviser.rdf");
        String queryString = 
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>"+
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"+
                "PREFIX trip: <http://www.semanticweb.org/user/ontologies/2015/3/untitled-ontology-7#>"+
                "SELECT ?Place "+
                    "WHERE {trip:Adventure trip:type_of_adventure ?Place}";
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        try{
            ResultSet Results = qexec.execSelect();
            while(Results.hasNext()){
                QuerySolution qr = Results.next();
               // System.out.println(qr.toString());
                String val[] = qr.toString().split("#");
                String subVal[] = val[1].split(">");
              // System.out.println("subval[o]:"+subVal[0]);
                if(budget){
                    int entreeFee=0;
                    String q = 
                            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
                            "PREFIX owl: <http://www.w3.org/2002/07/owl#>"+
                            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
                            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"+
                            "PREFIX trip: <http://www.semanticweb.org/user/ontologies/2015/3/untitled-ontology-7#>"+
                                "SELECT *"+
                            "WHERE { trip:"+subVal[0]+" trip:entry_fee ?c}";
                    Query qu = QueryFactory.create(q);
                    QueryExecution qex = QueryExecutionFactory.create(qu, model);
                    ResultSet Res = qex.execSelect();
                    while(Res.hasNext()){
                        QuerySolution qs = Res.next();
                        //System.out.println(qs.toString());
                        String[] v1 = qs.toString().split("\"");
                        //System.out.print("v1:"+v1[1]+"subval:"+subVal[0]);
                        entreeFee=Integer.parseInt(v1[1]);
                        places1.put(subVal[0], entreeFee);
                    }
                    
                    
                }
                else{
                    places1.put(subVal[0], 0);
                }
            }
            return places1;
           // ResultSetFormatter.out(System.out,Results,query);
        }finally{
            qexec.close();
        }        
    }
    static HashMap<String,Integer> cityscape1(){
        HashMap<String,Integer> places1 = new HashMap<String,Integer>();
        Model model = FileManager.get().loadModel("TripAdviser.rdf");
        String queryString = 
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>"+
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"+
                "PREFIX trip: <http://www.semanticweb.org/user/ontologies/2015/3/untitled-ontology-7#>"+
                "SELECT ?Place "+
                    "WHERE { trip:Cityscape trip:has_cityscape ?Place}";
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        try{
            ResultSet Results = qexec.execSelect();
            while(Results.hasNext()){
                QuerySolution qr = Results.next();
               // System.out.println(qr.toString());
                String val[] = qr.toString().split("#");
                String subVal[] = val[1].split(">");
              // System.out.println("subval[o]:"+subVal[0]);
                if(budget){
                    int entreeFee=0;
                    String q = 
                            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
                            "PREFIX owl: <http://www.w3.org/2002/07/owl#>"+
                            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
                            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"+
                            "PREFIX trip: <http://www.semanticweb.org/user/ontologies/2015/3/untitled-ontology-7#>"+
                                "SELECT *"+
                            "WHERE { trip:"+subVal[0]+" trip:entry_fee ?c}";
                    Query qu = QueryFactory.create(q);
                    QueryExecution qex = QueryExecutionFactory.create(qu, model);
                    ResultSet Res = qex.execSelect();
                    while(Res.hasNext()){
                        QuerySolution qs = Res.next();
                        //System.out.println(qs.toString());
                        String[] v1 = qs.toString().split("\"");
                        //System.out.print("v1:"+v1[1]+"subval:"+subVal[0]);
                        entreeFee=Integer.parseInt(v1[1]);
                        places1.put(subVal[0], entreeFee);
                    }
                    
                    
                }
                else{
                    places1.put(subVal[0], 0);
                }
            }
            return places1;
           // ResultSetFormatter.out(System.out,Results,query);
        }finally{
            qexec.close();
        }        
    }
    static HashMap<String,Integer> hall1(){
        HashMap<String,Integer> places1 = new HashMap<String,Integer>();
        Model model = FileManager.get().loadModel("TripAdviser.rdf");
        String queryString = 
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>"+
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"+
                "PREFIX trip: <http://www.semanticweb.org/user/ontologies/2015/3/untitled-ontology-7#>"+
                "SELECT ?Hall "+
                    "WHERE {trip:Hall trip:has_hall ?Hall}";
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        try{
            ResultSet Results = qexec.execSelect();
            while(Results.hasNext()){
                QuerySolution qr = Results.next();
               // System.out.println(qr.toString());
                String val[] = qr.toString().split("#");
                String subVal[] = val[1].split(">");
              // System.out.println("subval[o]:"+subVal[0]);
                if(budget){
                    int entreeFee=0;
                    String q = 
                            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
                            "PREFIX owl: <http://www.w3.org/2002/07/owl#>"+
                            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
                            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"+
                            "PREFIX trip: <http://www.semanticweb.org/user/ontologies/2015/3/untitled-ontology-7#>"+
                                "SELECT *"+
                            "WHERE { trip:"+subVal[0]+" trip:entry_fee ?c}";
                    Query qu = QueryFactory.create(q);
                    QueryExecution qex = QueryExecutionFactory.create(qu, model);
                    ResultSet Res = qex.execSelect();
                    while(Res.hasNext()){
                        QuerySolution qs = Res.next();
                        //System.out.println(qs.toString());
                        String[] v1 = qs.toString().split("\"");
                        //System.out.print("v1:"+v1[1]+"subval:"+subVal[0]);
                        entreeFee=Integer.parseInt(v1[1]);
                        places1.put(subVal[0], entreeFee);
                    }
                    
                    
                }
                else{
                    places1.put(subVal[0], 0);
                }
            }
            return places1;
           // ResultSetFormatter.out(System.out,Results,query);
        }finally{
            qexec.close();
        }        
    }
}
