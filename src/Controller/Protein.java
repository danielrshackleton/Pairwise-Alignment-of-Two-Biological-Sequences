/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author MT9HY82Q69F2JG7V8V77
 */
public class Protein {
    
    public String species;
    public String molecule;
    public char[] sequence;
    
    
    //Constructor for if a file is uploaded
    public Protein(File file) throws FileNotFoundException{
        
        if (file.getAbsolutePath().endsWith("fasta.txt")){
            
        
        try {
            
            
           
        // Scans a FASTA file and adds each word to a string to store everything
        Scanner scan = new Scanner(file);
        scan.useDelimiter(" ,");
        String str;
        String store="";
        
        while(scan.hasNext()){
            str = scan.next();
            str = str.trim();
            store = store.concat(str);
        }
        
        // Stores molecule name in a String
        this.molecule = store.substring(store.indexOf("_") + 1, store.indexOf(","));
        
        // Stores species in a String
        this.species = store.substring(store.indexOf("=") + 1, store.indexOf("G") - 1);
        
        // Removes all whitespace and adds protein sequence to array
        this.sequence = store.substring(store.indexOf("\n")).replaceAll("\\s+","").toCharArray();
        
        } catch(Exception e){
            
            System.out.println("nah");
        }
        
        } else throw new FileNotFoundException("Must be a FASTA file, " +
                "please try again.");
    }
    
    
    
    //Scans a fasta file name within the current project
    public Protein(String file) throws FileNotFoundException{
        
        if (file.endsWith("fasta.txt")){
            
        
        try {
            
            
           
        // Scans a FASTA file and adds each word to a string to store everything
        Scanner scan = new Scanner(new File(file));
        scan.useDelimiter(" ,");
        String str;
        String store="";
        
        while(scan.hasNext()){
            str = scan.next();
            str = str.trim();
            store = store.concat(str);
        }
        
        // Stores molecule name in a String
        this.molecule = store.substring(store.indexOf("_") + 1, store.indexOf(","));
        
        // Stores species in a String
        this.species = store.substring(store.indexOf("=") + 1, store.indexOf("G") - 1);
        
        // Removes all whitespace and adds protein sequence to array
        this.sequence = store.substring(store.indexOf("\n")).replaceAll("\\s+","").toCharArray();
        
        } catch(Exception e){
            
            System.out.println("nah");
        }
        
        } else throw new FileNotFoundException("Must be a FASTA file, " +
                "please try again.");
    }
    
    
    //Constructor for if a text sequence is enterred rather than FASTA file
    public Protein(char[] sequence) throws EmptySequenceException{
        
        if (sequence.length == 0){
            throw new EmptySequenceException("Please fill in both sequences");
        }
        this.molecule = "not defined";
        this.species = "not defined";
        this.sequence = sequence;
    }
}
    
