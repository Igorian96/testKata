import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение: ");
        String input = scanner.nextLine();
        System.out.println(calc(input));

    }
    public static String calc(String input) throws Exception {
        String result = null;
        String[] q = {"+", "-", "/", "*"};
        String[] q1 = {"\\+", "-", "/", "\\*"};
        int flag = -1;
        for (int i = 0; i < q.length; i++) {
            if(input.contains(q[i])) {
                flag = i;
                break;
            }
        }
        if(input.length() < 3){
            throw new Exception("строка не является математической операцией");
        }

        String[] strings = input.split(" " + q1[flag] + " ");

        if(flag == -1){
            throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        if(strings.length > 2){
            throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        if(isRoman(strings[0]) == isRoman(strings[1])){
            int a;
            int b;

            if(isRoman(strings[0])){
                a = conversionArabic(strings[0]);
                b = conversionArabic(strings[1]);
            } else {
                a = Integer.parseInt(strings[0]);
                b = Integer.parseInt(strings[1]);
            }
            switch (q[flag]){
                case "+":
                    result = String.valueOf((a + b));
                    break;
                case "-":
                    result = String.valueOf((a - b));
                    break;
                case "/":
                    result = String.valueOf((a / b));
                    break;
                case "*":
                    result = String.valueOf((a * b));
                    break;
            }
        } else {
            throw new Exception("используются одновременно разные системы счисления");
        }
        if(isRoman(strings[0])){
            result = conversionRoman(result);
        }
        return result;
    }

    public static boolean isRoman(String str){
        HashMap<Character, Integer> mapIs = new HashMap<>();

        mapIs.put('I', 1);
        mapIs.put('V', 5);
        mapIs.put('X', 10);
        mapIs.put('L', 50);
        mapIs.put('C', 100);
        mapIs.put('D', 500);
        mapIs.put('M', 1000);

        return mapIs.containsKey(str.charAt(0));
    }
    public static int conversionArabic(String str){
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);

        int last = str.length() - 1;
        char[] ar = str.toCharArray();
        int arabic;
        int result = map.get(ar[last]);
        for (int i = last - 1; i >= 0; i--) {
            arabic = map.get(ar[i]);

            if(arabic < map.get(ar[i + 1])){
                result = result - arabic;
            } else {
                result = result + arabic;
            }
        }
        return result;
    }
    public static String conversionRoman(String str) throws Exception {
        TreeMap<Integer, String> map1 = new TreeMap<>();
        int number = Integer.parseInt(str);
        map1.put(100, "C");
        map1.put(90, "XC");
        map1.put(50, "L");
        map1.put(40, "XL");
        map1.put(10, "X");
        map1.put(9, "IX");
        map1.put(5, "V");
        map1.put(4, "IV");
        map1.put(1, "I");

        String roman = "";
        int arabianKey;
        try {
            do {
                arabianKey = map1.floorKey(number);
                roman += map1.get(arabianKey);
                number -= arabianKey;
            } while (number != 0);
        } catch (NullPointerException o){
            throw new Exception("в римской системе нет отрицательных чисел");
        }
        return roman;
    }
}