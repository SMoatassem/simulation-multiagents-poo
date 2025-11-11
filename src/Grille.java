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
        // Example de Test pour le Jeu de la vie
//        grille[4][5] = 1;
//        grille[4][6] = 2;
//        grille[5][4] = 3;
//        grille[5][5] = 0;
//        grille[6][5] = 0;

        // Exemple de l'enonce jeu d'immigration
//        // Ligne 0
//        grille[1][1] = 3; grille[1][2] = 0; grille[1][3] = 1; grille[1][4] = 1; grille[1][5] = 0;
//        // Ligne 1
//        grille[2][1] = 3; grille[2][2] = 1; grille[2][3] = 1; grille[2][4] = 1; grille[2][5] = 2;
//        // Ligne 2
//        grille[3][1] = 1; grille[3][2] = 1; grille[3][3] = 3; grille[3][4] = 2; grille[3][5] = 2;
//        // Ligne 3
//        grille[4][1] = 0; grille[4][2] = 1; grille[4][3] = 2; grille[4][4] = 2; grille[4][5] = 2;
//        // Ligne 4
//        grille[5][1] = 0; grille[5][2] = 3; grille[5][3] = 2; grille[5][4] = 2; grille[5][5] = 1;

        // lignes 1 à 3
        for (int i = 1; i <= 3; i++) {
            grille[i][1] = 0; grille[i][2] = 0; grille[i][3] = 0;
            grille[i][4] = 1; grille[i][5] = 1; grille[i][6] = 1;
            grille[i][7] = 2; grille[i][8] = 2; grille[i][9] = 2;
            // grille[i][10] = 0; // reste 0
        }

        // lignes 4 à 6
        for (int i = 4; i <= 6; i++) {
            grille[i][1] = 3; grille[i][2] = 3; grille[i][3] = 3;
            grille[i][4] = 0; grille[i][5] = 0; grille[i][6] = 0;
            grille[i][7] = 1; grille[i][8] = 1; grille[i][9] = 1;
            // grille[i][10] = 0;
        }

        // lignes 7 à 9
        for (int i = 7; i <= 9; i++) {
            grille[i][1] = 2; grille[i][2] = 2; grille[i][3] = 2;
            grille[i][4] = 3; grille[i][5] = 3; grille[i][6] = 3;
            grille[i][7] = 0; grille[i][8] = 0; grille[i][9] = 0;
            // grille[i][10] = 0;
        }

        // ligne 10 (zone active du bas) : on met un peu de variété aussi
        grille[10][1] = 0;
        grille[10][2] = 1;
        grille[10][3] = 2;
        grille[10][4] = 3;
        grille[10][5] = 0;
        grille[10][6] = 1;
        grille[10][7] = 2;
        grille[10][8] = 3;
        grille[10][9] = 0;

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
    public void updateCasesGrille(int nombreEtats){
//        System.out.println("avant : " + Arrays.deepToString(grille));
        int[][] newGrille = new int[n+2][m+2]; // Grille temporaire
        for (int i = 1; i < n+1; i++){
            for (int j = 1; j < m+1; j++){
                int[][] couplesAparcourir = { {i-1, j}, {i-1, j-1}, {i-1, j+1}, {i, j-1}, {i+1, j-1},
                        {i+1, j}, {i+1, j+1}, {i, j+1}};
                if (nombreEtats == 1){
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
                else{
                    // Même si dans l'énoncé on a les états à partir de 1, on va à partir de 0 pour utiliser mod
                    int etat = grille[i][j];
                    int cptEtatk = 0;
                    for (int p = 0; p < 8; p++){
                        int idxI = couplesAparcourir[p][0];
                        int idxJ = couplesAparcourir[p][1];
                        boolean etatk = (grille[idxI][idxJ] == (etat +1)%nombreEtats);
                        // ajout de %nombreEtats car sinon on ne revient jamais à 0
                        cptEtatk = cptEtatk + (etatk ? 1 : 0);
                    }
                    if (cptEtatk >= 3){
                        newGrille[i][j] = (etat+1)%nombreEtats;
                    }
                    else{
                        newGrille[i][j] = etat;
                    }
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
