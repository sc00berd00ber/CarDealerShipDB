
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class UI {
    public void printList(ArrayList<HashMap<String,String>> list, String field) {
        list.forEach(item -> System.out.println(item.get(field)));
    }
    String getString(String question){
        var in = new Scanner(System.in);
        var answer = "";
        while(answer == "") {
            System.out.printf("%s: ", question);
            answer = in.nextLine();
        }
        return answer;
    }
    LocalDate getDate(String question){
        var in = new Scanner(System.in);
        LocalDate answer = null;
        while(answer == null) {
            System.out.printf("%s (YYYY-MM-DD): ", question);
            var text = in.nextLine();
            try {
                answer = LocalDate.parse(text);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
        }
        return answer;
    }
    Double getDouble(String question){
        var in = new Scanner(System.in);
        Double answer = null;
        while(answer == null) {
            System.out.printf("%s: ", question);
            var text = in.nextLine();
            try {
                answer = Double.parseDouble(text);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
        }
        return answer;
    }
    Integer getInteger(String question){
        var in = new Scanner(System.in);
        Integer answer = null;
        while(answer == null) {
            System.out.printf("%s: ", question);
            var text = in.nextLine();
            try {
                answer = Integer.parseInt(text);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
        }
        return answer;
    }
    public void showMenu(ArrayList<MenuOption> options, String title) {
        while(true) {
            System.out.println(title);
            for(var option : options) {
                System.out.println(option.toString());
            }
            String choice = getString("ENTER CHOICE");
            for(var option : options) {
                if(choice.equals(option.key)){
                    option.action.run();
                }
            }
            if(choice.equals("0")) {
                break; //exit while
            }
        }
    }
}
