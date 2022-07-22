import java.util.ArrayList;
import java.util.List;

public class calcWithQestions {
    public static void main(String[] args) {
        String exp="2?+?5=69";
        exp=exp.trim().replace(" ", "");
        System.out.print("\033[H\033[J");
        System.out.println(exp);
        List<String> ls = Nmbr(exp);
        System.out.println(ls);
        // List<String> ls1 = parsNmbr(ls);
        // System.out.println(ls1);

    }

    public static List<Integer> parsNmbr(List<String> str) {
        List<Integer> res = new ArrayList<>();
        
        
        // for (String item:str) {
        //     res.add(Character.toString(item.charAt(item.length()-1)));
        // }
        return res;
    }

    public static List<String> Nmbr(String s){
        List<String> result= new ArrayList<>();
        String res="";
        for (int i = 0; i < s.length(); i++) {
            if(Character.isDigit(s.charAt(i))){
                res+=s.charAt(i);
            }else{
                if(s.charAt(i)=='?'){
                    res+="x";
                }
            }
            if(s.charAt(i)=='+' || s.charAt(i)=='='){
                
                result.add(res);
                res="";
            }
        }        
        result.add(res);
        return result;
    }
}
