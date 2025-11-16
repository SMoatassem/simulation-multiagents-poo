import java.util.Arrays;

public class Grille {
    protected int[][] grille;         // Grille courante (avec bordures inutilisées)
    protected int[][] initialeGrille; // Copie de sauvegarde pour réinitialisation
    protected int n;                  // Nombre de lignes utiles (sans bordures)
    protected int m;                  // Nombre de colonnes utiles (sans bordures)

    public Grille(int n ,int m){
        this.n = n;
        this.m = m;
        grille = new int[n+2][m+2];   // +2 pour les bordures (1 à n, 1 à m sont les cases actives)

        // Exemple de remplissage manuel d'une grille 10x10
        // Ici on crée un motif complexe juste pour tester l'automate

        // lignes 1 à 3
        for (int i = 1; i <= 3; i++) {
            // trois premières colonnes vides
            grille[i][1] = 0; grille[i][2] = 0; grille[i][3] = 0;
            // quelques états variés
            grille[i][4] = 2; grille[i][5] = 3; grille[i][6] = 2;
            grille[i][7] = 2; grille[i][8] = 2; grille[i][9] = 2;
        }

        // lignes 4 à 6
        for (int i = 4; i <= 6; i++) {
            // bloc d'états 3
            grille[i][1] = 3; grille[i][2] = 3; grille[i][3] = 3;
            // bloc vide
            grille[i][4] = 0; grille[i][5] = 0; grille[i][6] = 0;
            // mélange d'états à droite
            grille[i][7] = 3; grille[i][8] = 2; grille[i][9] = 2;
        }

        // lignes 7 à 9
        for (int i = 7; i <= 9; i++) {
            // bloc d'états 2
            grille[i][1] = 2; grille[i][2] = 2; grille[i][3] = 2;
            // bloc d'états 3
            grille[i][4] = 3; grille[i][5] = 3; grille[i][6] = 3;
            // bloc vide
            grille[i][7] = 0; grille[i][8] = 0; grille[i][9] = 0;
        }

        // ligne 10 avec variété
        grille[10][1] = 0;
        grille[10][2] = 3;
        grille[10][3] = 2;
        grille[10][4] = 3;
        grille[10][5] = 0;
        grille[10][6] = 1;
        grille[10][7] = 2;
        grille[10][8] = 3;
        grille[10][9] = 0;

        // Sauvegarde de l’état initial
        initialeGrille = new int[grille.length][];
        for (int i = 0; i<n+2 ; i++){
            initialeGrille[i] = grille[i].clone();
        }
    }

    // Change une cellule seulement si val est 0 ou 1 (règle utile pour Jeu de la Vie)
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
        int[][] newGrille = new int[n+2][m+2]; // Grille temporaire pour éviter conflits

        // On parcourt uniquement les cases utiles (1..n, 1..m)
        for (int i = 1; i < n+1; i++){
            for (int j = 1; j < m+1; j++){

                // 8 voisins d'une cellule
                int[][] couplesAparcourir = {
                        {i-1, j}, {i-1, j-1}, {i-1, j+1},
                        {i, j-1},
                        {i+1, j-1}, {i+1, j}, {i+1, j+1},
                        {i, j+1}
                };

                // Mode Jeu de la Vie
                if (nombreEtats == 1){
                    // On considère seulement 0 (mort) et 1 (vivant)
                    int cptAlive =
                            grille[i-1][j]   + grille[i-1][j-1] + grille[i-1][j+1] +
                                    grille[i][j-1]   + grille[i+1][j-1] + grille[i+1][j] +
                                    grille[i+1][j+1] + grille[i][j+1];

                    // Règles de Conway
                    if (grille[i][j] == 0 && cptAlive == 3) {
                        newGrille[i][j] = 1;  // naissance
                    }
                    else if(grille[i][j]==1 && (cptAlive == 3 || cptAlive == 2)){
                        newGrille[i][j] = 1;  // survie
                    }
                    else{
                        newGrille[i][j] = 0;  // mort
                    }
                }

                else{
                    int etat = grille[i][j]; // état actuel
                    int cptEtatk = 0;        // nb de voisins dans l’état (etat+1) mod k

                    // On compte combien de voisins sont dans l’état suivant
                    for (int p = 0; p < 8; p++){
                        int idxI = couplesAparcourir[p][0];
                        int idxJ = couplesAparcourir[p][1];
                        boolean etatk = (grille[idxI][idxJ] == (etat +1)%nombreEtats);

                        cptEtatk += (etatk ? 1 : 0);
                    }

                    // Règle : si ≥3 voisins dans l’état suivant → transition
                    if (cptEtatk >= 3){
                        newGrille[i][j] = (etat+1)%nombreEtats;
                    }
                    else{
                        newGrille[i][j] = etat; // sinon on garde l'état
                    }
                }
            }
        }

        // Copie du résultat dans la grille principale
        for (int p = 0; p < n+2; p++){
            for (int q = 0; q < m+2; q++){
                grille[p][q] = newGrille[p][q];
            }
        }
    }

    // Réinitialise la grille à son état initial
    public void grilletoinit(){
        for (int i = 0; i < n+2; i++){
            for (int j = 0; j < n+2; j++){
                grille[i][j] = initialeGrille[i][j];
            }
        }
    }
}
