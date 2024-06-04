package proyecto1;

public class Letter {
    int id;
    char character;
    boolean visited = false;
    LinkedList<Letter> adjLetters;
    
    public Letter(int id, char character){
        this.id = id;
        this.character = character;
        this.adjLetters = new LinkedList<>();
    }
    
    public void addAdjLetter(Letter adj){
        adjLetters.append(adj);
    }
}
