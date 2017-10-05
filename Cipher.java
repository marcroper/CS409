public class Cipher {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final int ALPHABET_SIZE = ALPHABET.length();

    public static String translate(String phrase, int shift) {
        System.out.println("translate");
        {
            String translatedString = "";
            int position = 0;
            int newPosition = 0;
            String ucPhrase = phrase.toUpperCase();
            int i = 0;
            while (i < ucPhrase.length()) {
                position = findPosition(ucPhrase.charAt(i));
                if (position != -1) {
                    newPosition = position + shift;
                    if (newPosition > ALPHABET_SIZE - 1) {
                        newPosition = newPosition - ALPHABET_SIZE;
                    } else if (newPosition < 0) {
                        newPosition = newPosition + ALPHABET_SIZE;
                    }
                    char newChar = ALPHABET.charAt(newPosition);
                    translatedString = translatedString + newChar;
                } else {
                    translatedString = translatedString + ucPhrase.charAt(i);
                }
                i++;
            }
            return translatedString;
        }
    }

    private static int findPosition(char letter) {
        System.out.println("findPosition");
        {
            for (int i = 0; i < ALPHABET_SIZE; i++) {
                if (letter == ALPHABET.charAt(i)) {
                    return i;
                }
            }
            return -1;
        }
    }

    public static String encrypt(String phrase, int shift) {
        System.out.println("encrypt");
        {
            return (translate(phrase, shift));
        }
    }

    public static String decrypt(String phrase, int shift) {
        System.out.println("decrypt");
        {
            return (translate(phrase, shift * -1));
        }
    }

    public static void main(String[] args) {
        System.out.println("main");
        {
            System.out.println(decrypt("Pe EF McFep?", 11));
        }
    }
}
