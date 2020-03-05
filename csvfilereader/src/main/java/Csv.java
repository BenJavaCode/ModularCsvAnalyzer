
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Csv {


    public static void main(String[] args) throws IOException {

        String end = "cont";
        Scanner scanner = new Scanner(System.in);
        ServiceImpl service = new ServiceImpl();

        //for reflection selection
        HashMap<Integer, String> methodNames = new HashMap<>();
        methodNames.put(1, "getTitelYear");
        methodNames.put(2, "getActor");
        methodNames.put(3, "getGenre");
        methodNames.put(4, "getRating");
        methodNames.put(5, "getInstructor");

        while (end.equals("cont")){

            ArrayList<String[]> listen = service.fileToArray(); //init
            System.out.println("press 1 to sort på title-year ");
            System.out.println("press 2 to sort movies by rating");
            System.out.println("press 3 to search for a parameter in a movie ");
            System.out.println("press 4 to create new csv file with the model attributes");


            String scan = scanner.next();

            switch (scan){
                case "1":
                    ArrayList<Model> stripedList = service.stripList(listen);
                    stripedList = service.sort(stripedList, scan);


                    for(Model d : stripedList) {
                        String results = d.toString();
                        System.out.println(results);
                    }

                    break;
                case "2":
                    ArrayList<Model> stripedList2 = service.stripList(listen);
                    stripedList = service.sort(stripedList2, scan);

                    for(Model d : stripedList) {
                        String results2 = d.toString();
                        System.out.println(results2);
                    }

                    break;
                case "3":

                    System.out.println("Which category do you want to search for? ");
                    System.out.println("press 1 for titelYear, 2 for actor, 3 for genre, 4 for rating, 5 for instructor");

                    int selected;
                    while (true){
                        String select = scanner.next();
                        try {
                            selected = Integer.parseInt(select);
                            break;
                        }catch (Exception idiot){
                            System.out.println("give a valid number plz.. try again: ");
                        }

                    }
                    String reflection = methodNames.get(selected);


                    System.out.println("search for something in your criteria: ");
                    String search = scanner.next();
                    ArrayList<Model> stripedList3 = service.stripList(listen);
                    HashMap<String, Integer> suggestions = service.suggestionsFromInput(stripedList3, search, reflection );

                    //Map with suggestions print
                    for(Map.Entry<String, Integer> entry: suggestions.entrySet()) {
                        String key = entry.getKey();
                        int value = entry.getValue();
                        System.out.println("press " + value + " for "+ key);
                    }
                    String choose = scanner.next();
                    ArrayList<Model> finalList = service.chooseSuggestionsAndRes(suggestions, stripedList3, choose, reflection);

                    for(Model d : finalList) {
                        String results2 = d.toString();
                        System.out.println(results2);
                    }

                    break;

                case "4":
                    ArrayList<Model> stripedList5 = service.stripList(listen);
                    service.ArrayToCsv(stripedList5);
                    break;

            }
            System.out.println("write 'cont' to continue, anything else to break");
            end = scanner.next();

        }






    }


}
