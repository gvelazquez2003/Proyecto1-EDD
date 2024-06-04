package proyecto1;

public class Letter {
    char character;
    boolean visited = false;
    LinkedList<Letter> adjLetters;
    
    public Letter(char character){
        this.character = character;
        this.adjLetters = new LinkedList<>();
    }
    
    public void addAdjLetter(Letter adj){
        adjLetters.append(adj);
    }
}
