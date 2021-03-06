import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
//--------------------------------------------------------------------
/**
 * COMP 2503 Winter 2020 Assignment 1.
 * <p>
 * This program reads a text file and compiles a list of the
 * words together with how many each word appears.
 * </p>
 * <p>
 * Words from a standard list of stop words are not included.
 * </p>
 * Updated Winter 2020
 *
 * @author Maryam Elahi
 * @author Dylan Borchert
 */
public class A1
{
    private static final int MAX_DISPLAY = 10;

    private ArrayList<Token> wordList = new ArrayList<>();

    private String[] stopwords = {"a", "about", "all", "am", "an",
            "and", "any", "are", "as", "at", "be", "been", "but", "by", "can",
            "cannot", "could", "did", "do", "does", "else", "for", "from",
            "get", "got", "had", "has", "have", "he", "her", "hers", "him",
            "his", "how", "i", "if", "in", "into", "is", "it", "its", "like",
            "more", "me", "my", "no", "now", "not", "of", "on", "one",
            "or", "our", "out", "said", "say", "says", "she", "so", "some",
            "than", "that", "the", "their", "them", "then", "there", "these",
            "they", "this", "to", "too", "us", "upon", "was", "we", "were",
            "what", "with", "when", "where", "which", "while", "who",
            "whom", "why", "will", "you", "your"};

    private int totalWordList = 0;

    private int stopWordList = 0;

    private Scanner input = new Scanner( System.in);

    public static void main( String[] args)
    {
        A1 a1 = new A1();
        a1.run();
    }


    /**
     * Will sort and print the final result of the wordList.
     */
    private void printResults()
    {

        System.out.println( "Total Words: " + totalWordList);
        System.out.println( "Unique Words: " + wordList.size());
        System.out.println( "Stop Words: " + stopWordList);
        System.out.println();
        System.out.println( MAX_DISPLAY + " Most Frequent");

        Collections.sort( wordList, new MostFrequentWords());
        printWordList( MAX_DISPLAY);

        System.out.println();
        System.out.println( MAX_DISPLAY + " Least Frequent");

        Collections.sort( wordList, new LeastFrequentWord());
        printWordList( MAX_DISPLAY);

        System.out.println();
        System.out.println( "All");

        Collections.sort( wordList);
        printWordList( wordList.size());
    }

    /**
     * Will print out words in the list.
     * The amount of words printed out depends on maxAmt.
     * To avoid code duplication in print results method.
     * @param maxAmt is the highest amount of words that will be printed.
     */
    private void printWordList( int maxAmt)
    {
        int listSize = wordList.size();

        for ( int i = 0; i < Math.min( listSize, maxAmt); i++)
        {
            System.out.println( wordList.get(i).toString());
        }
    }

    /**
     * Will use Scanner to read the input and add it to wordList.
     * <p>
     * If the word is a stop word, then it will not be added
     * </p>
     * <p>
     * If the word is unique, then it will be added to the wordList
     * </p>
     */
    private void readFile()
    {

        while ( input.hasNext())
        {
            String word = input.next().toLowerCase().
                    replaceAll("[^a-z]", "").trim();

            if ( word.length() > 0)
            {
                if ( isStopWord( word))
                {
                    stopWordList++;
                }
                else
                {
                    addWord( word);
                }

                totalWordList++;
            }
        }
    }

    /**
     * Will return true or false depending if the word is a stop word or not.
     * @return true, if the inputWord is a stop word
     *  false, if the inputWord is not a stop word.
     * @param inputWord is the word to be check if it's a stop word.
     */
    private boolean isStopWord( String inputWord)
    {
        //Converts the Array to an ArrayList to use .contains()
        return Arrays.asList( stopwords).contains( inputWord);
    }

    /**
     * Adds inputWord to wordlist ArrayList.
     * <p>
     * If the word is already in the list it will increment frequency by 1.
     * </p>
     * <p>
     * If word is not in the list it will add it to the wordList.
     * </p>
     * @param inputWord is the word that needs to be added to the list
     */
    private void addWord( String inputWord)
    {
        Token curWord = new Token( inputWord);

        //.contains uses overridden .equals method in Token
        if ( wordList.contains(curWord))
        {
            //.index uses overridden .equals method in Token
            int tokenIndex = wordList.indexOf( curWord);
            wordList.get( tokenIndex).addFrequency();
        }
        else
        {
            wordList.add( curWord);
        }
    }

    /**
     * Run the program. Read the file, then print the results.
     */
    public void run()
    {

        readFile();
        printResults();
    }

}