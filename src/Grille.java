import java.util.Arrays;

public class Grille {
    private int[][] grille;
    private int[][] initialeGrille;
    private int n;
    private int m;

    public Grille(int n ,int m){
        this.n = n;
        this.m = m;
        grille = new int[n+2][m+2];
        // Example
        grille[4][5] = 1;
        grille[4][6] = 1;
        grille[5][4] = 1;
        grille[5][5] = 1;
        grille[6][5] = 1;

        initialeGrille = new int[grille.length][];
        for (int i = 0; i<n+2 ; i++){
            initialeGrille[i] = grille[i].clone();
        }
    }

    public void setGrille(int i, int j, int val){
        if (val == 1 || val == 0){
            this.grille[i][j] = val;
        }
    }
    public int getn(){
        return this.n;
    }

    public int getm(){
        return this.m;
    }

    public int getelement(int i, int j){
        return grille[i][j];
    }
    public void updateCasesGrille(){
//        System.out.println("avant : " + Arrays.deepToString(grille));
        int[][] newGrille = new int[n+2][m+2]; // Grille temporaire
        for (int i = 1; i < n+1; i++){
            for (int j = 1; j < m+1; j++){
                int cptAlive = grille[i-1][j] + grille[i-1][j-1] + grille[i-1][j+1]
                        + grille[i][j-1] + grille[i+1][j-1] + grille[i+1][j] + grille[i+1][j+1]
                        + grille[i][j+1];

                if (grille[i][j] == 0 && cptAlive == 3) {
                    newGrille[i][j] = 1;
                }
                else if(grille[i][j]==1 && (cptAlive == 3 || cptAlive == 2)){
                    newGrille[i][j] = 1;

                }
                else{
//                    System.out.println("le i est " + i + " le j est " + j + " le cptAlive est " + cptAlive);
                    newGrille[i][j] = 0;
                }
            }
        }
        for (int p = 0; p < n+2; p++){
            for (int q = 0; q < n+2; q++){
                grille[p][q] = newGrille[p][q];
            }
        }
//        System.out.println("après " + Arrays.deepToString(grille));
    }

    public void grilletoinit(){
        for (int i = 0; i < n+2; i++){
            for (int j = 0; j < n+2; j++){
                grille[i][j] = initialeGrille[i][j];
            }
        }
    }

}
