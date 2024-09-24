import java.util.Scanner;
import java.io.*;

public class WordCount
{
    Map<String, Integer> counter;
    String input;
    public WordCount(String filename)
    {
        //read the text from the specified file
        String text = readFile(filename);
        
        //if filename doesn't exist, then use filename as the text itself
        if(text == null){
            text = filename;
            input = text;
        }
        //initialize the counter instance variable using the countWords helper method
        counter = countWords(text);
    }
    
    /*
     *  Return the number of times the specified word was used in the document
     *  Don't forget to sanitize word!
     */
    public int getWordCount(String word)
    {
        word = sanitize(word);
      
        return counter.get(word) == null ? 0 : counter.get(word);
    }
    
    /*
     *  Return a Set containing all of the words that were used in this document
     */
    public Set<String> getDistinctWords()
    {
          
        return counter.keySet();
    }
    
    /*
     *  Return number of different words that were used in this document
     */
    public int countDistinctWords()
    {
       
        return getDistinctWords().size();
    }
    
    /*
     *  Return a Mapping of Word->Occurances in the specified text String
     *
     *  This method can be used like this:
     *
     *      Map<String, Integer> countAllWords = WordCount.countWords("one ring to rule them all one ring to find them");
     *      Integer countRing = countAllWords.get("ring");
                    *      System.out.println(countRing); //this should output 2 because "ring" appears twice in the String
     */
    private Map<String, Integer> countWords(String text)
    {
        
        
        Map<String, Integer> ret = new ListMap<String, Integer>();
        String[] words = (text).split(" ");
        for(int i = 0; i < words.length; i++){
            words[i] = words[i].trim();
            words[i] = sanitize(words[i]);
        }
        for(int i = 0; i < words.length; i++){
            String str = words[i];
            if(str.equals("") || str == null)
                continue;
            if(ret.containsKey(str)){
          
                ret.put((str), (ret.get((str)) + 1));
                
            }else{
                ret.put((str), 1);
            }
        
        }
     
        return ret;
    }
    
    /*
     *  Return a string that contains all of the text stored in the spefied file
     *  Each line of the file will be separated by a line break (\n) in the returned String
     *  If the file does not exist, then return null
     *  If the file is empty, then return the empty String
     */
    private String readFile(String filename)
    {
        try
        {
            Scanner in = new Scanner(new File(filename));
            
            String ret = "";
            while(in.hasNextLine())
                ret += in.nextLine() + "\n";
                
            return ret; 
        }
        catch(Exception e)
        {
            return null;
        }
    }
    
    /*
     *  Return a sanitized version of the input string.
     *
     *
     *  This method should remove all characters from str that are not letters or a single quote
     *  It should also make the the string lower case.
     *
     *  Examples:
     *      Mr.         =>          mr
     *      Hello       =>          hello
     *      they're     =>          they're
     *      "Hello"     =>          hello
     *      hello!      =>          hello
     *      however,    =>          however
     *      ke$ha       =>          keha
     *      :-)         =>                  (empty string)
     *      mays'       =>          mays'
     *      'ere        =>          'ere
     *      jump-scare  =>          jumpscare
     */
    private String sanitize(String str)
    {
        str = str.toLowerCase();
        
        //Create a String to store all legal letters in the sanitized String
        String legal = "qwertyuioplkjhgfdsazxcvbnm'";
        
        String ret = "";
        for(int i=0; i<str.length(); i++)
        {
            //isolate the letter at index i
            String letter = str.substring(i, i+1);
            
            //if the letter is a legal letter then frankenstein it into the ret String
            if(legal.indexOf(letter) > -1)
                ret += letter;
        }
        
        return ret;
    }
}