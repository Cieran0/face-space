
public class Hash {

    
    /** 
     * Turns a string into a unique 64 bit integer (aka Long).
     * @param stringToHash The String to turn into the Long.
     * @return unique 64 bit integer (aka Long).
     */
    public static Long hash(String stringToHash) {
        long hashValue = 1;
        for (int c : stringToHash.toCharArray()) {
            hashValue *= (c) + stringToHash.charAt((int)((((double)c)/255f) * ((double)stringToHash.length())));
        }
        return hashValue;
    }

}
