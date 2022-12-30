import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);
        System.out.println("Enter the string:");
        String user_input = in.nextLine();

        StringCalculator s_calculator = new StringCalculator();

        int result = s_calculator.add(user_input);

        if (result == -1){
            System.out.println("Invalid input.");
        }
        else if (result == -2){
           System.out.println("You've entered negative numbers.");
        }
        else{
            System.out.println("result: " + result);
        }

    }

    public int add(String numbers){

        if (numbers.isEmpty()) return 0;

        int sum = 0;
        int ch_counter = 0;
        ArrayList<String> delimiters = new ArrayList<String>();
        ArrayList<Integer> negative_nums = new ArrayList<Integer>();

        delimiters.add(",");
        delimiters.add("\\n");

        if (numbers.startsWith("//")){

            ch_counter += 2;

            if (numbers.charAt(ch_counter) != '['){
                delimiters.add(String.valueOf(numbers.charAt(ch_counter)));
                ch_counter += 3;
            }

            else{
                //ch_counter = numbers.indexOf("\\n") + 2;
                ch_counter = numbers.indexOf("]\\n") + 3;

                String delimiters_str = numbers.substring(2, ch_counter - 2);

                Matcher m = Pattern.compile("\\[(.*?)\\]").matcher(delimiters_str);

                while (m.find()) {
                    delimiters.add(m.group(1));
                }

            }

        }

        delimiters.sort((s1, s2) -> -(s1.length() - s2.length()));
        numbers = numbers.substring(ch_counter);

//        System.out.println(numbers);

        for(String delimiter : delimiters){
            numbers = numbers.replace(delimiter, ",");
        }

        if ((numbers.length() >= 2) && numbers.charAt(numbers.length() - 1) == ',' && numbers.charAt(numbers.length() - 2) == ',') return -1;

        String[] numbers_to_process = numbers.split(",");
//        System.out.println(Arrays.toString(numbers_to_process));
        boolean consist_negatives = false;

        for(String number : numbers_to_process){
            try{
                int cur_num = Integer.parseInt(number);
                if (0 <= cur_num && cur_num <= 1000) sum += cur_num;
                else if (cur_num < 0) {
                    negative_nums.add(cur_num);
                    consist_negatives = true;
                }
            }

            catch (NumberFormatException e) {
                return -1;
            }

        }

//        System.out.println(numbers);
//        System.out.println(delimiters);
//        System.out.println(longest_delimiter);
//        System.out.println(ch_counter);

        if (!consist_negatives) return sum;

        else{
            System.out.println(negative_nums);
            return -2;
        }

    }

}
