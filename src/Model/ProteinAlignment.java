package Model;

import Controller.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author MT9HY82Q69F2JG7V8V77
 */
public class ProteinAlignment { 
    
    private int[][] scores;
    private int[][] directions;
    private boolean global;    
    private int alignmentScore;
    private int gapPenalty;
    private int extendedGap;
    private boolean hasAffineGap;
    private ArrayList<Character> sequence1 = new ArrayList<>();
    private ArrayList<Character> sequence2 = new ArrayList<>();
    
    //StringBuilders represent first sequence, lines if there is a direct match,
    //  and the second sequence
    StringBuilder firstSB = new StringBuilder();
    StringBuilder matchingSB = new StringBuilder();
    StringBuilder secondSB = new StringBuilder();
    
    
    private double percentageMatch;
    
        
    public ProteinAlignment(Protein p1, Protein p2){
             
        scores = new int[p2.sequence.length+1][p1.sequence.length+1];
        directions = new int[p2.sequence.length+1][p1.sequence.length+1];
    
    }
    
    
    public void setGapPenalty(int penalty){
        if (penalty > 0){
            gapPenalty = - penalty;
        } else {
            gapPenalty = penalty;
        }
    }
    
    public void setAffineGap(boolean affine){
        hasAffineGap = affine;
    }
    
    public boolean getAffineGap(){
        return hasAffineGap;
    }
    
    
    
    
    
    public void setExtendedPenalty(int penalty){
        if (penalty > 0){
            extendedGap = -penalty;
        } else {
            extendedGap = penalty;
        }
    }
    
    public int getExtendedPenalty(){
        return extendedGap;
    }
    
    
    public void isGlobal(boolean isGlobal){
        global = isGlobal;
    }
    
    public int getScore(){
        return alignmentScore;
    }
    
    public double getPercentageMatch(){
        return percentageMatch;
    }
    
    
    public ArrayList<Character> getSequence1(){
        return this.sequence1;
    }
    
    public ArrayList<Character> getSequence2(){
        return this.sequence2;
    }
    
    
    
    
    
    public StringBuilder getFirstSB(){
        return firstSB;
    }
    
    public StringBuilder getMatchingSB(){
        return matchingSB;
    }
    
    public StringBuilder getSecondSB(){
        return secondSB;
    }
    
    
    
    
    
    
    public void scoreArray(Protein p1, Protein p2) 
            throws InvalidAlphabetException{
        
        //variable to be used to fill first row and column of scores array
        int xyScore = 0;
        
        //if global alignment, first row and column fill with increasing
        //  gap penalties, otherwise they are set as 0.
        if (global) {
            xyScore = gapPenalty;
        } else if (!global){
            xyScore = 0;
        }
        
        
        
        
        // Declaring seq1 and seq2 to the two protein sequences to be aligned
        char[] seq1 = p1.sequence;
        char[] seq2 = p2.sequence;
        
        
        
        // Fills first row of scoring matrix with increasing gap penalties
        for (int x=0;x<=seq1.length;x++){
            scores[0][x] = xyScore*x;
        }
        
        
        // Fills first column of scoring matrix with increasing gap penalties
        for (int y=0;y<=seq2.length;y++){
            scores[y][0] = xyScore*y;
        }
        
        //fills the first row with 2's, representing gaps in the upper sequence
        for (int x=1;x<=seq1.length;x++){
            directions[0][x] = 2;
        }
        
        
        //fills the first column with 1's, representing gaps in lower sequence
        for (int y=1;y<=seq2.length;y++){
            directions[y][0] = 1;
        }


        
        // Declares variables to be used in Needleman-Wunsch algorithm
        int best, up, left, diag;
        
        // If affineGap = false, extendedGap is set to gapPenalty,
        //   even if extendedGap has been set beforehand
        if (!hasAffineGap){
            extendedGap = gapPenalty;
        }
        
        // This fills the NW matrix with the best scores
        for (int y=1;y<=seq2.length;y++){
            for (int x=1;x<=seq1.length;x++){
                
                
 
                
                // Scores the element which came from row above
                if (directions[y-1][x] == 1){
                    up = scores[y-1][x] + extendedGap;
                } else {
                    up = scores[y-1][x] + gapPenalty;
                }
                
                // Scores the element which came from column to the left
                if (directions[y][x-1] == 2){
                    left = scores[y][x-1] + extendedGap;
                } else {
                    left = scores[y][x-1] + gapPenalty;
                }
                
                
                // Scores the element diagonal to the current one
                diag = scores[y-1][x-1] + 
                    Blosum.getAlignScore(seq1[x-1], seq2[y-1]);
                
                // Find the value with the highest score
                best = Math.max(Math.max(up, left),diag);
                
                
                
                // Sets each element in the matrix
                scores[y][x] = best;
                
                
                // This if-else statement keeps a record of where the current 
                //  element moved from
                if (best == up){
                    
                    directions[y][x] = 1;
                    
                } else if (best == left){
                    
                    directions[y][x] = 2;
                    
                } else if (best == diag){
                    
                    directions[y][x] = 3;
                }
                
            }
        }
        
        
        
        //initialises int x and y to be used to find traceback
        int x = 0;
        int y = 0;
        
        //initialise the int values to be used to find the maximum value of the
        //  last row and column
        int lastColY = 0;
        int lastRowX = 0;
        
        
        /*
        *   If global alignment is checked, start the traceback from the last
        *       element in directions array and end traceback at last element
        *       in directions array.
        */
        if(global){
            
            //  Initialise x and y so the directions array traces back from 
            //      the last element.
            y = seq2.length;
            x = seq1.length;
            
            
            //  Ends when there are no more elements in the directions array
            while(directions[y][x]>0){
            
            
                int position = directions[y][x];
                
                //  If the current position == 1, this means it has come from
                //      the element above.
                if (position == 1){
                    sequence1.add(0,'-');
                    sequence2.add(0, seq2[y-1]);
                    y--;
                    
                
                //  If the current position == 2, this means it has come from
                //      the element to the left.
                } else if (position == 2){
                    sequence1.add(0, seq1[x-1]);
                    sequence2.add(0,'-');
                    x--;
                    
                //  If the current position == 3, this means it has come from
                //      the diagonal element.
                } else if (position == 3){
                    sequence1.add(0, seq1[x-1]);
                    sequence2.add(0, seq2[y-1]);
                    x--;
                    y--;
                    
                }
            }
            // Sets the score of alignment to the last element in scores array.
            alignmentScore = scores[seq2.length][seq1.length];
            
            
        // If it is local alignment, we get the largest element between last 
        //  column and row, and start the traceback from that position.
        } else if (!global){
            
            
            // Finds the largest element in last column.
            int lastColMax = scores[0][seq1.length];
            for (int j=1;j<=seq2.length;j++){
                if (scores[j][seq1.length] > lastColMax){
                    lastColMax = scores[j][seq1.length];
                    lastColY = j;
                }
            }
            
            // Finds the largest element in the last row.
            int lastRowMax = scores[seq2.length][0];
            for (int i=1;i<=seq1.length;i++){
                if (scores[seq2.length][i] > lastRowMax){
                    lastRowMax = scores[seq2.length][i];
                    lastRowX = i;
                }
            }
            
            // Compares last row and column to find largest element,
            //  sets initial traceback coordinates (x and y) to this element.
            if (lastColMax > lastRowMax){
                x = seq1.length;
                y = lastColY;
            } else if (lastRowMax > lastColMax) {
                x = lastRowX;
                y = seq2.length;
            } else {
                y = seq2.length;
                x = seq1.length;
            }
            
            // Sets the total alignment score to the start of this traceback
            alignmentScore = scores[y][x];
            
            
            while(directions[y][x]>0  && scores[y][x]>0){
            
                
                int position = directions[y][x];
                
                //if current position is up, add a gap to the upper sequence
                if (position == 1){
                    sequence1.add(0,'-');
                    sequence2.add(0, seq2[y-1]);
                    y--;
                    
                //if current position is left, add a gap to the lower sequence
                } else if (position == 2){
                    sequence1.add(0, seq1[x-1]);
                    sequence2.add(0,'-');
                    x--;
                    
                //if current position is diagonal, there is a match
                } else if (position == 3){
                    sequence1.add(0, seq1[x-1]);
                    sequence2.add(0, seq2[y-1]);
                    x--;
                    y--;
                    
                }
            }
            
            
        }
        
        double numberOfMatches = 0;
        
        /*
        * This adds a line to a stringbuilder if there is a match, and a space 
        *   if there is not a match. Also counts the percentage match between
        *   the two sequences.
        */
        for (int i=0;i<=sequence1.size()-1;i++){
            
            
            firstSB.append(sequence1.get(i).toString());
            secondSB.append(sequence2.get(i).toString());
            
            if (Objects.equals(sequence1.get(i), sequence2.get(i))){
                matchingSB.append("|");
                numberOfMatches++;
                
            } else {
                matchingSB.append(" ");
            }
        }
        //sets the match percentage of the two sequences
        percentageMatch = ((numberOfMatches*100)/Math.max(sequence1.size(), sequence2.size()));
    }
    
    
    
    
    
    
    public String toString() {
        return "Alignment Score: " + alignmentScore + "\n"
                + "Blosum matrix used: " + Blosum.getBlosumMatrix() + "\n"
                + "Using global alignment: " + global + "\n"
                + "Has affine gap: " + hasAffineGap + 
                ", extended penalty: " + extendedGap + "\n"
                + "-------------------------------------- \n"
                + sequence1 + "\n"
                + sequence2;
    }
    
    
    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     * @throws Model.InvalidAlphabetException
     * @throws Controller.EmptySequenceException
     */
    public static void main(String[] args) throws FileNotFoundException, 
            InvalidAlphabetException, EmptySequenceException {
        
        
        String test1 = "src/Resources/Human_CS_O75390_fasta.txt";
        String test2 = "src/Resources/Yeast_CS_P00890_fasta.txt";
        String test3 = "test.txt";
        char[] test4 = "HNDPHEA".toUpperCase().toCharArray();
        char[] test5 = "DESA".toUpperCase().toCharArray();
        
        
        Protein protein1 = new Protein(test1);        
        Protein protein2 = new Protein(test5);
        
        
        
        ProteinAlignment pa = new ProteinAlignment(protein1,protein2);
        
        
        Blosum.setBlosumMatrix(62);
        pa.isGlobal(true);
        pa.setAffineGap(false);
        pa.setGapPenalty(-8);
        pa.setExtendedPenalty(-2);
        
        
        pa.scoreArray(protein1, protein2);
        
        //Prints Score array
        for (int[] row : pa.scores){
            System.out.println(Arrays.toString(row));
        }
        
        for (int[] row : pa.directions){
            System.out.println(Arrays.toString(row));
        }
        
        System.out.println("Protein molecule: " + protein1.molecule + 
                ", species: " + protein1.species);
        System.out.println("Protein molecule: " + protein2.molecule + 
                ", species: " + protein2.species);
        System.out.println(pa.percentageMatch);
        
        System.out.println(pa.toString());
              
    }
    
}
