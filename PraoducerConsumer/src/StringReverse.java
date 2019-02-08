public class StringReverse {
    public static void main(String[] args){
        String s = "-----23--qww2-s---sadfsdfe34--------323--33----sdfas-------";
        int K = 9;
        char[] inputArray = s.toCharArray();
        char[] output = new char[s.length()];
        int index = s.length()-1;
        int outputIndex = index;
        System.out.println(s);
        while (index >= 0){
            System.out.println(index + " is the initial index");
            for (int i = 0; i < K && index>=0; i++) {
                char c = inputArray[index];
                System.out.println(c + " is the char at index "+index );
                if(c == '-'){
                    System.out.println(index + " is the index");
                    index--;
                    i--;
                    continue;
                }
                output[outputIndex] = c;
                index--;
                outputIndex--;
            }
            if(outputIndex > 0 && index > 0){
                System.out.println(outputIndex + " **** " + index);
                output[outputIndex] = '-';
                outputIndex--;

            }
        }
        StringBuffer sb = new StringBuffer();
        for (char c : output) {
            System.out.println(String.valueOf(c));
            if((int)c != 0){
                //System.out.println("string buffer length: "+ sb.length() + " char is: " + (c==' ')+ (c=='-'));
                if((sb.length() == 0 && c == '-')){
                    continue;
                }
                sb.append(c);
            }
        }
        System.out.println(sb.toString().toUpperCase());
    }
}
