package proyecto1;

public class Board {
    LinkedList<Letter> letters; // Adjacency list
   
    public Board(){
        this.letters = new LinkedList<>();
    }
    
    public void builtBoard(String chars){
        // Add nodes
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                char c = chars.charAt(index);
                Letter newLetter = new Letter(i*4+j,c);
                letters.append(newLetter);
                index += 2;
            }
        }
        
        // Add relationships
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Letter letter = letters.get(i*4 + j).data;
                
                if (i > 0) {
                    int pos = (i - 1) * 4 + j; // Up
                    letter.addAdjLetter(letters.get(pos).data);
                }
                if (i > 0 && j < 3) {
                    int pos = (i - 1) * 4 + j + 1; // Diagonal (up-right)
                    letter.addAdjLetter(letters.get(pos).data);
                }
                if (j < 3) {
                    int pos = i * 4 + j + 1; // Rigth
                    letter.addAdjLetter(letters.get(pos).data);
                }
                if (i < 3 && j < 3) {
                    int pos = (i + 1) * 4 + j + 1;  // Diagonal (down-right)
                    letter.addAdjLetter(letters.get(pos).data);
                }
                if (i < 3) {
                    int pos = (i + 1) * 4 + j; // Down
                    letter.addAdjLetter(letters.get(pos).data);
                }
                if (i < 3 && j > 0) {
                    int pos = (i + 1) * 4 + j - 1; // Diagonal (down-left)
                    letter.addAdjLetter(letters.get(pos).data);
                }
                if (j > 0) {
                    int pos = i * 4 + j - 1; // Left
                    letter.addAdjLetter(letters.get(pos).data);
                }
                if (i > 0 && j > 0) {
                    int pos = (i - 1) * 4 + j - 1; // Diagonal (up-left)
                    letter.addAdjLetter(letters.get(pos).data);
                }
            }
        }
    }
    
    void clearVisited(){
        Node<Letter> current = letters.head;
        while (current != null) {
            current.data.visited = false;
            current = current.next;
        }
    }
    
    // Get the index of a node
    int getIndex(Node<Letter> node){
        if(letters.head == null) 
            return -1;
        
        if(letters.head.data.id == node.data.id)
            return 0;
        int index = 1;
        Node<Letter> current = letters.head.next;
        while (current != null) {
            if(current.data.id == node.data.id){
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }
    
    public boolean searchWordDFS(int v, String word, int start) {
        Node<Letter> current = letters.get(v);
        current.data.visited = true;
        
        if (start == word.length()) {
            return true;
        }
     
        char letter = word.charAt(start);
        int n = current.data.adjLetters.size;
        for(int j = 0; j < n; j++){
            Node<Letter> adj = current.data.adjLetters.get(j);
            if (!adj.data.visited && adj.data.character == letter){
                int adjIndex = getIndex(adj);
                if (searchWordDFS(adjIndex, word, start + 1)) {
                    return true;
                }
            }
        }
        current.data.visited = false;
        return false;
    }
    
     public boolean searchWordBFS(String word) {
        Queue<Node<Letter>> queue = new Queue<>();

        // Start a BFS for each node with the first letter of the word
        Node<Letter> letter = letters.head;
        while (letter != null) {
            if (!letter.data.visited && letter.data.character == word.charAt(0)) {
                queue.enqueue(letter);
                
                int start = 1;
                Queue<Node<Letter>> visited = new Queue<>();
                while (!queue.isEmpty()) {
                    Node<Letter> current = queue.dequeue();
                    if(current.data.character != word.charAt(start-1)){
                        continue;
                    }
                    current.data.visited = true;
                    visited.enqueue(current);
                    if(start == word.length() && word.charAt(word.length()-1) == current.data.character){
                        return true;
                    } 

                    int n = current.data.adjLetters.size;
                    boolean foundLetter = false;
                    for(int j = 0; j < n; j++){
                        Node<Letter> adj = current.data.adjLetters.get(j);
                        if (start < word.length() && !adj.data.visited && adj.data.character == word.charAt(start)) {
                            // Match character
                            queue.enqueue(adj);
                            foundLetter = true;
                        }
                    }
                    if(foundLetter){
                        start += 1;
                    } else {
                        current.data.visited = false;
                    }
                }
                
                while (!visited.isEmpty()) {
                    Node<Letter> visitedLetter = visited.dequeue();
                    visitedLetter.data.visited = false;
                }
                letter.data.visited = false;
            }
            letter = letter.next;
        }
        
        return false;
    }
    
    public void searchDictionary(Dictionary dict, int method){
        Node<Word> current = dict.words.head;
        //Search for all the words in the dictionary
        while(current != null){
            if(method == 0){
                //Search first letter
                char firstLetter = current.data.word.charAt(0);
                Node<Letter> letter = letters.head;
                while (letter != null) {
                    if(letter.data.character == firstLetter && !letter.data.visited){
                        int firstLetterIndex = getIndex(letter);
                        long startTime = System.currentTimeMillis();
                        boolean found = searchWordDFS(firstLetterIndex, current.data.word, 1);
                        long endTime = System.currentTimeMillis();
                        current.data.found = found;
                        current.data.time = endTime - startTime;
                    }
                    letter = letter.next;
                }
            } else {
                long startTime = System.currentTimeMillis();
                System.out.println("epa " + current.data.word);
                boolean found = searchWordBFS(current.data.word);
                long endTime = System.currentTimeMillis();
                current.data.found = found;
                current.data.time = endTime - startTime;
            }
            
            current = current.next;
        }
    }
}
