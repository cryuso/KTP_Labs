public class Palindrome {
    public static void main(String[] args){
        for(int i = 0; i < args.length; i++){
            String s = args[i];
            reverseString(s);
            isPalindrome(s);
        }
    }
    public static String reverseString(String s){
        String r = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            r += s.charAt(i);
            System.out.println(r);
        }
        return r;
    }
    public static Boolean isPalindrome(String s) {
        if (s.equals(reverseString(s))) {
            System.out.println("Является палиндромом");
        } else {
            System.out.println("Не является палиндромом");
        }
        return s.equals(reverseString(s));
    }
}
