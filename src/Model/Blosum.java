/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Daniel
 */
public class Blosum {
        
    private static int[][] blosum;
    
    
    
    private static int[][] blosum45 = {
        
        /*
            A   R   N   D   C   Q   E   G   H   I   L   K   M   F   P   S   T   W   Y   V 
        A { 5, -2, -1, -2, -1, -1, -1,  0, -2, -1, -1, -1, -1, -2, -1,  1,  0, -2, -2,  0}, 
        R {-2,  7,  0, -1, -3,  1,  0, -2,  0, -3, -2,  3, -1, -2, -2, -1, -1, -2, -1, -2}, 
        N {-1,  0,  6,  2, -2,  0,  0,  0,  1, -2, -3,  0, -2, -2, -2,  1,  0, -4, -2, -3}, 
        D {-2, -1,  2,  7, -3,  0,  2, -1,  0, -4, -3,  0, -3, -4, -1,  0, -1, -4, -2, -3}, 
        C {-1, -3, -2, -3, 12, -3, -3, -3, -3, -3, -2, -3, -2, -2, -4, -1, -1, -5, -3, -1}, 
        Q {-1,  1,  0,  0, -3,  6,  2, -2,  1, -2, -2,  1,  0, -4, -1,  0, -1, -2, -1, -3},  
        E {-1,  0,  0,  2, -3,  2,  6, -2,  0, -3, -2,  1, -2, -3,  0,  0, -1, -3, -2, -3}, 
        G { 0, -2,  0, -1, -3, -2, -2,  7, -2, -4, -3, -2, -2, -3, -2,  0, -2, -2, -3, -3}, 
        H {-2,  0,  1,  0, -3,  1,  0, -2, 10, -3, -2, -1,  0, -2, -2, -1, -2, -3,  2, -3}, 
        I {-1, -3, -2, -4, -3, -2, -3, -4, -3,  5,  2, -3,  2,  0, -2, -2, -1, -2,  0,  3}, 
        L {-1, -2, -3, -3, -2, -2, -2, -3, -2,  2,  5, -3,  2,  1, -3, -3, -1, -2,  0,  1}, 
        K {-1,  3,  0,  0, -3,  1,  1, -2, -1, -3, -3,  5, -1, -3, -1, -1, -1, -2, -1, -2}, 
        M {-1, -1, -2, -3, -2,  0, -2, -2,  0,  2,  2, -1,  6,  0, -2, -2, -1, -2,  0,  1},
        F {-2, -2, -2, -4, -2, -4, -3, -3, -2,  0,  1, -3,  0,  8, -3, -2, -1,  1,  3,  0},
        P {-1, -2, -2, -1, -4, -1,  0, -2, -2, -2, -3, -1, -2, -3,  9, -1, -1, -3, -3, -3},
        S { 1, -1,  1,  0, -1,  0,  0,  0, -1, -2, -3, -1, -2, -2, -1,  4,  2, -4, -2, -1},
        T { 0, -1,  0, -1, -1, -1, -1, -2, -2, -1, -1, -1, -1, -1, -1,  2,  5, -3, -1,  0},
        W {-2, -2, -4, -4, -5, -2, -3, -2, -3, -2, -2, -2, -2,  1, -3, -4, -3, 15,  3, -3},
        Y {-2, -1, -2, -2, -3, -1, -2, -3,  2,  0,  0, -1,  0,  3, -3, -2, -1,  3,  8, -1},
        V { 0, -2, -3, -3, -1, -3, -3, -3, -3,  3,  1, -2,  1,  0, -3, -1,  0, -3, -1,  5}
        */
        
        { 5, -2, -1, -2, -1, -1, -1,  0, -2, -1, -1, -1, -1, -2, -1,  1,  0, -2, -2,  0}, 
        {-2,  7,  0, -1, -3,  1,  0, -2,  0, -3, -2,  3, -1, -2, -2, -1, -1, -2, -1, -2}, 
        {-1,  0,  6,  2, -2,  0,  0,  0,  1, -2, -3,  0, -2, -2, -2,  1,  0, -4, -2, -3}, 
        {-2, -1,  2,  7, -3,  0,  2, -1,  0, -4, -3,  0, -3, -4, -1,  0, -1, -4, -2, -3}, 
        {-1, -3, -2, -3, 12, -3, -3, -3, -3, -3, -2, -3, -2, -2, -4, -1, -1, -5, -3, -1}, 
        {-1,  1,  0,  0, -3,  6,  2, -2,  1, -2, -2,  1,  0, -4, -1,  0, -1, -2, -1, -3},  
        {-1,  0,  0,  2, -3,  2,  6, -2,  0, -3, -2,  1, -2, -3,  0,  0, -1, -3, -2, -3}, 
        { 0, -2,  0, -1, -3, -2, -2,  7, -2, -4, -3, -2, -2, -3, -2,  0, -2, -2, -3, -3}, 
        {-2,  0,  1,  0, -3,  1,  0, -2, 10, -3, -2, -1,  0, -2, -2, -1, -2, -3,  2, -3}, 
        {-1, -3, -2, -4, -3, -2, -3, -4, -3,  5,  2, -3,  2,  0, -2, -2, -1, -2,  0,  3}, 
        {-1, -2, -3, -3, -2, -2, -2, -3, -2,  2,  5, -3,  2,  1, -3, -3, -1, -2,  0,  1}, 
        {-1,  3,  0,  0, -3,  1,  1, -2, -1, -3, -3,  5, -1, -3, -1, -1, -1, -2, -1, -2}, 
        {-1, -1, -2, -3, -2,  0, -2, -2,  0,  2,  2, -1,  6,  0, -2, -2, -1, -2,  0,  1},
        {-2, -2, -2, -4, -2, -4, -3, -3, -2,  0,  1, -3,  0,  8, -3, -2, -1,  1,  3,  0},
        {-1, -2, -2, -1, -4, -1,  0, -2, -2, -2, -3, -1, -2, -3,  9, -1, -1, -3, -3, -3},
        { 1, -1,  1,  0, -1,  0,  0,  0, -1, -2, -3, -1, -2, -2, -1,  4,  2, -4, -2, -1},
        { 0, -1,  0, -1, -1, -1, -1, -2, -2, -1, -1, -1, -1, -1, -1,  2,  5, -3, -1,  0},
        {-2, -2, -4, -4, -5, -2, -3, -2, -3, -2, -2, -2, -2,  1, -3, -4, -3, 15,  3, -3},
        {-2, -1, -2, -2, -3, -1, -2, -3,  2,  0,  0, -1,  0,  3, -3, -2, -1,  3,  8, -1},
        { 0, -2, -3, -3, -1, -3, -3, -3, -3,  3,  1, -2,  1,  0, -3, -1,  0, -3, -1,  5}
    };
        
    
    
    
    
    private static int[][] blosum62 = {      
        
      /*    
            A   R   N   D   C   Q   E   G   H   I   L   K   M   F   P   S   T   W   Y   V
        A { 4, -1, -2, -2,  0, -1, -1,  0, -2, -1, -1, -1, -1, -2, -1,  1,  0, -3, -2,  0},
	R {-1,  5,  0, -2, -3,  1,  0, -2,  0, -3, -2,  2, -1, -3, -2, -1, -1, -3, -2, -3},
	N {-2,  0,  6,  1, -3,  0,  0,  0,  1, -3, -3,  0, -2, -3, -2,  1,  0, -4, -2, -3},
	D {-2, -2,  1,  6, -3,  0,  2, -1, -1, -3, -4, -1, -3, -3, -1,  0, -1, -4, -3, -3},
	C { 0, -3, -3, -3,  9, -3, -4, -3, -3, -1, -1, -3, -1, -2, -3, -1, -1, -2, -2, -1},
	Q {-1,  1,  0,  0, -3,  5,  2, -2,  0, -3, -2,  1,  0, -3, -1,  0, -1, -2, -1, -2},
	E {-1,  0,  0,  2, -4,  2,  5, -2,  0, -3, -3,  1, -2, -3, -1,  0, -1, -3, -2, -2},
	G { 0, -2,  0, -1, -3, -2, -2,  6, -2, -4, -4, -2, -3, -3, -2,  0, -2, -2, -3, -3},
	H {-2,  0,  1, -1, -3,  0,  0, -2,  8, -3, -3, -1, -2, -1, -2, -1, -2, -2,  2, -3},
	I {-1, -3, -3, -3, -1, -3, -3, -4, -3,  4,  2, -3,  1,  0, -3, -2, -1, -3, -1,  3},
	L {-1, -2, -3, -4, -1, -2, -3, -4, -3,  2,  4, -2,  2,  0, -3, -2, -1, -2, -1,  1},
	K {-1,  2,  0, -1, -3,  1,  1, -2, -1, -3, -2,  5, -1, -3, -1,  0, -1, -3, -2, -2},
	M {-1, -1, -2, -3, -1,  0, -2, -3, -2,  1,  2, -1,  5,  0, -2, -1, -1, -1, -1,  1},
	F {-2, -3, -3, -3, -2, -3, -3, -3, -1,  0,  0, -3,  0,  6, -4, -2, -2,  1,  3, -1},
	P {-1, -2, -2, -1, -3, -1, -1, -2, -2, -3, -3, -1, -2, -4,  7, -1, -1, -4, -3, -2},
	S { 1, -1,  1,  0, -1,  0,  0,  0, -1, -2, -2,  0, -1, -2, -1,  4,  1, -3, -2, -2},
	T { 0, -1,  0, -1, -1, -1, -1, -2, -2, -1, -1, -1, -1, -2, -1,  1,  5, -2, -2,  0},
	W {-3, -3, -4, -4, -2, -2, -3, -2, -2, -3, -2, -3, -1,  1, -4, -3, -2, 11,  2, -3},
	Y {-2, -2, -2, -3, -2, -1, -2, -3,  2, -1, -1, -2, -1,  3, -3, -2, -2,  2,  7, -1},
	V { 0, -3, -3, -3, -1, -2, -2, -3, -3,  3,  1, -2,  1, -1, -2, -2,  0, -3, -1,  4}
        */
        
        
        
        { 4, -1, -2, -2,  0, -1, -1,  0, -2, -1, -1, -1, -1, -2, -1,  1,  0, -3, -2,  0},
	{-1,  5,  0, -2, -3,  1,  0, -2,  0, -3, -2,  2, -1, -3, -2, -1, -1, -3, -2, -3},
	{-2,  0,  6,  1, -3,  0,  0,  0,  1, -3, -3,  0, -2, -3, -2,  1,  0, -4, -2, -3},
	{-2, -2,  1,  6, -3,  0,  2, -1, -1, -3, -4, -1, -3, -3, -1,  0, -1, -4, -3, -3},
	{ 0, -3, -3, -3,  9, -3, -4, -3, -3, -1, -1, -3, -1, -2, -3, -1, -1, -2, -2, -1},
	{-1,  1,  0,  0, -3,  5,  2, -2,  0, -3, -2,  1,  0, -3, -1,  0, -1, -2, -1, -2},
	{-1,  0,  0,  2, -4,  2,  5, -2,  0, -3, -3,  1, -2, -3, -1,  0, -1, -3, -2, -2},
	{ 0, -2,  0, -1, -3, -2, -2,  6, -2, -4, -4, -2, -3, -3, -2,  0, -2, -2, -3, -3},
	{-2,  0,  1, -1, -3,  0,  0, -2,  8, -3, -3, -1, -2, -1, -2, -1, -2, -2,  2, -3},
	{-1, -3, -3, -3, -1, -3, -3, -4, -3,  4,  2, -3,  1,  0, -3, -2, -1, -3, -1,  3},
	{-1, -2, -3, -4, -1, -2, -3, -4, -3,  2,  4, -2,  2,  0, -3, -2, -1, -2, -1,  1},
	{-1,  2,  0, -1, -3,  1,  1, -2, -1, -3, -2,  5, -1, -3, -1,  0, -1, -3, -2, -2},
	{-1, -1, -2, -3, -1,  0, -2, -3, -2,  1,  2, -1,  5,  0, -2, -1, -1, -1, -1,  1},
	{-2, -3, -3, -3, -2, -3, -3, -3, -1,  0,  0, -3,  0,  6, -4, -2, -2,  1,  3, -1},
	{-1, -2, -2, -1, -3, -1, -1, -2, -2, -3, -3, -1, -2, -4,  7, -1, -1, -4, -3, -2},
	{ 1, -1,  1,  0, -1,  0,  0,  0, -1, -2, -2,  0, -1, -2, -1,  4,  1, -3, -2, -2},
	{ 0, -1,  0, -1, -1, -1, -1, -2, -2, -1, -1, -1, -1, -2, -1,  1,  5, -2, -2,  0},
	{-3, -3, -4, -4, -2, -2, -3, -2, -2, -3, -2, -3, -1,  1, -4, -3, -2, 11,  2, -3},
	{-2, -2, -2, -3, -2, -1, -2, -3,  2, -1, -1, -2, -1,  3, -3, -2, -2,  2,  7, -1},
	{ 0, -3, -3, -3, -1, -2, -2, -3, -3,  3,  1, -2,  1, -1, -2, -2,  0, -3, -1,  4}
    };
    
    
    
    
    
    
    
    private static int[][] blosum80 = {
        
        /* 
            A   R   N   D   C   Q   E   G   H   I   L   K   M   F   P   S   T   W   Y   V
        A { 7, -3, -3, -3, -1, -2, -2,  0, -3, -3, -3, -1, -2, -4, -1,  2,  0, -5, -4, -1} 
        R {-3,  9, -1, -3, -6,  1, -1, -4,  0, -5, -4,  3, -3, -5, -3, -2, -2, -5, -4, -4} 
        N {-3, -1,  9,  2, -5,  0, -1, -1,  1, -6, -6,  0, -4, -6, -4,  1,  0, -7, -4, -5} 
        D {-3, -3,  2, 10, -7, -1,  2, -3, -2, -7, -7, -2, -6, -6, -3, -1, -2, -8, -6, -6} 
        C {-1, -6, -5, -7, 13, -5, -7, -6, -7, -2, -3, -6, -3, -4, -6, -2, -2, -5, -5, -2} 
        Q {-2,  1,  0, -1, -5,  9,  3, -4,  1, -5, -4,  2, -1, -5, -3, -1, -1, -4, -3, -4} 
        E {-2, -1, -1,  2, -7,  3,  8, -4,  0, -6, -6,  1, -4, -6, -2, -1, -2, -6, -5, -4} 
        G { 0, -4, -1, -3, -6, -4, -4,  9, -4, -7, -7, -3, -5, -6, -5, -1, -3, -6, -6, -6} 
        H {-3,  0,  1, -2, -7,  1,  0, -4, 12, -6, -5, -1, -4, -2, -4, -2, -3, -4,  3, -5} 
        I {-3, -5, -6, -7, -2, -5, -6, -7, -6,  7,  2, -5,  2, -1, -5, -4, -2, -5, -3,  4} 
        L {-3, -4, -6, -7, -3, -4, -6, -7, -5,  2,  6, -4,  3,  0, -5, -4, -3, -4, -2,  1} 
        K {-1,  3,  0, -2, -6,  2,  1, -3, -1, -5, -4,  8, -3, -5, -2, -1, -1, -6, -4, -4} 
        M {-2, -3, -4, -6, -3, -1, -4, -5, -4,  2,  3, -3,  9,  0, -4, -3, -1, -3, -3,  1} 
        F {-4, -5, -6, -6, -4, -5, -6, -6, -2, -1,  0, -5,  0, 10, -6, -4, -4,  0,  4, -2} 
        P {-1, -3, -4, -3, -6, -3, -2, -5, -4, -5, -5, -2, -4, -6, 12, -2, -3, -7, -6, -4} 
        S { 2, -2,  1, -1, -2, -1, -1, -1, -2, -4, -4, -1, -3, -4, -2,  7,  2, -6, -3, -3} 
        T { 0, -2,  0, -2, -2, -1, -2, -3, -3, -2, -3, -1, -1, -4, -3,  2,  8, -5, -3,  0} 
        W {-5, -5, -7, -8, -5, -4, -6, -6, -4, -5, -4, -6, -3,  0, -7, -6, -5, 16,  3, -5} 
        Y {-4, -4, -4, -6, -5, -3, -5, -6,  3, -3, -2, -4, -3,  4, -6, -3, -3,  3, 11, -3} 
        V {-1, -4, -5, -6, -2, -4, -4, -6, -5,  4,  1, -4,  1, -2, -4, -3,  0, -5, -3,  7} 
        */
    
        { 7, -3, -3, -3, -1, -2, -2,  0, -3, -3, -3, -1, -2, -4, -1,  2,  0, -5, -4, -1}, 
        {-3,  9, -1, -3, -6,  1, -1, -4,  0, -5, -4,  3, -3, -5, -3, -2, -2, -5, -4, -4}, 
        {-3, -1,  9,  2, -5,  0, -1, -1,  1, -6, -6,  0, -4, -6, -4,  1,  0, -7, -4, -5}, 
        {-3, -3,  2, 10, -7, -1,  2, -3, -2, -7, -7, -2, -6, -6, -3, -1, -2, -8, -6, -6}, 
        {-1, -6, -5, -7, 13, -5, -7, -6, -7, -2, -3, -6, -3, -4, -6, -2, -2, -5, -5, -2}, 
        {-2,  1,  0, -1, -5,  9,  3, -4,  1, -5, -4,  2, -1, -5, -3, -1, -1, -4, -3, -4}, 
        {-2, -1, -1,  2, -7,  3,  8, -4,  0, -6, -6,  1, -4, -6, -2, -1, -2, -6, -5, -4}, 
        { 0, -4, -1, -3, -6, -4, -4,  9, -4, -7, -7, -3, -5, -6, -5, -1, -3, -6, -6, -6}, 
        {-3,  0,  1, -2, -7,  1,  0, -4, 12, -6, -5, -1, -4, -2, -4, -2, -3, -4,  3, -5}, 
        {-3, -5, -6, -7, -2, -5, -6, -7, -6,  7,  2, -5,  2, -1, -5, -4, -2, -5, -3,  4}, 
        {-3, -4, -6, -7, -3, -4, -6, -7, -5,  2,  6, -4,  3,  0, -5, -4, -3, -4, -2,  1}, 
        {-1,  3,  0, -2, -6,  2,  1, -3, -1, -5, -4,  8, -3, -5, -2, -1, -1, -6, -4, -4}, 
        {-2, -3, -4, -6, -3, -1, -4, -5, -4,  2,  3, -3,  9,  0, -4, -3, -1, -3, -3,  1}, 
        {-4, -5, -6, -6, -4, -5, -6, -6, -2, -1,  0, -5,  0, 10, -6, -4, -4,  0,  4, -2}, 
        {-1, -3, -4, -3, -6, -3, -2, -5, -4, -5, -5, -2, -4, -6, 12, -2, -3, -7, -6, -4}, 
        { 2, -2,  1, -1, -2, -1, -1, -1, -2, -4, -4, -1, -3, -4, -2,  7,  2, -6, -3, -3}, 
        { 0, -2,  0, -2, -2, -1, -2, -3, -3, -2, -3, -1, -1, -4, -3,  2,  8, -5, -3,  0}, 
        {-5, -5, -7, -8, -5, -4, -6, -6, -4, -5, -4, -6, -3,  0, -7, -6, -5, 16,  3, -5}, 
        {-4, -4, -4, -6, -5, -3, -5, -6,  3, -3, -2, -4, -3,  4, -6, -3, -3,  3, 11, -3}, 
        {-1, -4, -5, -6, -2, -4, -4, -6, -5,  4,  1, -4,  1, -2, -4, -3,  0, -5, -3,  7} 
    };
    
    public static void setBlosumMatrix(int blosumMatrix){
        switch (blosumMatrix){
            case 45: blosum = blosum45;
            break;
            case 62: blosum = blosum62;
            break;
            case 80: blosum = blosum80;
            break;
            default: blosum = blosum62;
        }
    }
    
    
    public static int getBlosumMatrix(){
        int matrix = 0;
        if (blosum == blosum45){
            matrix = 45;
        } else if (blosum == blosum62){
            matrix = 62;
        } else if (blosum == blosum80){
            matrix = 80;
        }
        return matrix;
    }
    
    
        public static int getIndex(char a) throws InvalidAlphabetException {
    	// check for upper and lowercase characters
    	switch ((String.valueOf(a)).toUpperCase().charAt(0)) {
	    	case 'A': return 0;
	    	case 'R': return 1;
	    	case 'N': return 2;
	    	case 'D': return 3;
	    	case 'C': return 4;
	    	case 'Q': return 5;
	    	case 'E': return 6;
	    	case 'G': return 7;
	    	case 'H': return 8;
	    	case 'I': return 9;
	    	case 'L': return 10;
	    	case 'K': return 11;
	    	case 'M': return 12;
	    	case 'F': return 13;
	    	case 'P': return 14;
	    	case 'S': return 15;
	    	case 'T': return 16;
	    	case 'W': return 17;
	    	case 'Y': return 18;
	    	case 'V': return 19;
	    	default: throw new InvalidAlphabetException(a, "Invalid amino acid character: ");
    	}
    }
        
        
    private static int getScore(int i, int j) {
    	
    	return blosum[i][j];
    }
    
    
    
    
    public static int getAlignScore(char a, char b) throws InvalidAlphabetException{
        
        return getScore(getIndex(a),getIndex(b));
    }
        
}
