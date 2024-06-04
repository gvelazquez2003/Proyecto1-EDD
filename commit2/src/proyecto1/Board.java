/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
                Letter newLetter = new Letter(c);
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
                if (i < 3) {
                    int pos = (i + 1) * 4 + j; // Down
                    letter.addAdjLetter(letters.get(pos).data);
                }
                if (j > 0) {
                    int pos = i * 4 + j - 1; // Left
                    letter.addAdjLetter(letters.get(pos).data);
                }
                if (j < 3) {
                    int pos = i * 4 + j + 1; // Rigth
                    letter.addAdjLetter(letters.get(pos).data);
                }
                if (i > 0 && j > 0) {
                    int pos = (i - 1) * 4 + j - 1; // Diagonal (up-left)
                    letter.addAdjLetter(letters.get(pos).data);
                }
                if (i > 0 && j < 3) {
                    int pos = (i - 1) * 4 + j + 1; // Diagonal (up-right)
                    letter.addAdjLetter(letters.get(pos).data);
                }
                if (i < 3 && j > 0) {
                    int pos = (i + 1) * 4 + j - 1; // Diagonal (down-left)
                    letter.addAdjLetter(letters.get(pos).data);
                }
                if (i < 3 && j < 3) {
                    int pos = (i + 1) * 4 + j + 1;  // Diagonal (down-right)
                    letter.addAdjLetter(letters.get(pos).data);
                }
            }
        }
    }
}
        
