
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test {
	
	
	public static void main(String[] args) {
	
		NW("AGC","AAAC");
	}

public static void NW(String f1, String f2) {      
  
        String seq = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[] seq1 = new int[f1.length()];
        int[] seq2 = new int[f2.length()];
        int i;
        int j;
        for (i = 0; i < f1.length(); i++) {       
            for (j = 0; j < seq.length(); j++) {   
                if (f1.charAt(i) == seq.charAt(j))
                    seq1[i] = j;
                
            }
        }
        for (i = 0; i < f2.length(); i++) {
            for (j = 0; j < seq.length(); j++) {    
                if (f2.charAt(i) == seq.charAt(j))  
                    seq2[i] = j;
            }
        }
        int gap = 2;                              
        int match, delete, insert;
        int[][] F = new int[f1.length()+1][f2.length()+1];
        for (j = 0; j <= f2.length(); j++) {
            F[0][j] = gap * j;
        }
        for (i = 0; i <= f1.length(); i++) {
            F[i][0] = gap * i;
        }
        for (i = 1; i <= f1.length(); i++) {     
            for (j = 1; j <= f2.length(); j++) {
            	match  = F[i-1][j-1]+comp(seq1[i-1],seq2[j-1]);
            	
                delete = F[i - 1][j] + gap;
                insert = F[i][j - 1] + gap;
                if ((match < delete) && (match < insert)) {
                    F[i][j] = match;
                } else if (delete < insert) {
                    F[i][j] = delete;
                } else {
                    F[i][j] = insert;
                }
            }
        }        
        String Alignmentf1 = "";                
        String Alignmentf2 = "";
        i = f1.length();
        j = f2.length();
        while ((i > 0) && (j > 0)) {
            int Score = F[i][j];
            int ScoreDiag = F[i-1][j-1];     
            int ScoreLeft = F[i - 1][j];
            int ScoreUp = F[i][j-1];
            if (Score == (ScoreDiag + comp(seq1[i-1],seq2[j-1]))) {
                Alignmentf1 = f1.charAt(i-1) + Alignmentf1;
                Alignmentf2 = f2.charAt(j-1) + Alignmentf2;
                i = i - 1;
                j = j - 1;
            } else if (Score == ScoreLeft + gap) {
            	Alignmentf1 = f1.charAt(i-1) + Alignmentf1;
                Alignmentf2 = "-" + Alignmentf2;
                i = i - 1;
            } else {
            	   Alignmentf1 = "-" + Alignmentf1;
                   Alignmentf2 = f2.charAt(j-1) + Alignmentf2;
                   j = j - 1;
            }
        }
        while (i > 0) {
            Alignmentf1 = f1.charAt(i-1) + Alignmentf1;
            Alignmentf2 = "-" + Alignmentf2;
            i = i - 1;
        }
        while (j > 0) {
            Alignmentf1 = "-" + Alignmentf1;
            Alignmentf2 = f2.charAt(j-1) + Alignmentf2;
            j = j - 1;
        }
        System.out.println("Global alignment score=" + F[f1.length()][f2.length()]);
        System.out.println("Sequence1= "+Alignmentf1);
        System.out.println("Sequence2= "+Alignmentf2);
        for (i = 0; i <= f1.length(); i++) {     
            for (j = 0; j <= f2.length(); j++) {
            	System.out.print(F[i][j]+" ");
            }
            System.out.println('\n');
            }
     
    }

	public static int comp(int a,int b)
	{
		if(a==b)
			return 0;
		else
			return 1;
	}


    
}