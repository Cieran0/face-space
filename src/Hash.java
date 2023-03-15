
public class Hash {

    public static Long hash(String stringToHash) {
        long hashValue = 1;
        for (int c : stringToHash.toCharArray()) {
            hashValue *= (c) + stringToHash.charAt((int)((((double)c)/255f) * ((double)stringToHash.length())));
        }
        System.out.println(hashValue);
        return hashValue;
    }

}
