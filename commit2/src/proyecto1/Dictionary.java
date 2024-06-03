package proyecto1;

public class Dictionary {
    LinkedList<Word> words;
    
    public Dictionary(){
        this.words = new LinkedList<>();
    }
    
    public boolean addWord(String word) {
        // Check if word is already in the list
        Node<Word> current = words.head;
        while (current != null) {
            if (current.data.word.equals(word)) {
                return false; // Element found in the list
            }
            current = current.next;
        }
        
        // Add word to list
        Word newWord = new Word(word);
        words.append(newWord);
        return true;
    }
}
