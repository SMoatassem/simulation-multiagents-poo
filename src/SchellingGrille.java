import java.util.ArrayList;
import java.util.Arrays;

public class SchellingGrille extends Grille{
    // Liste des cellules vacantes (chaque élément = {i, j})
    private ArrayList<int[]> cellsVacantes = new ArrayList<int[]>();


    public SchellingGrille(int n, int m){
        super(n, m); // Initialisation par la classe mère

        // Recherche initiale des cellules vacantes (état = 0)
        for (int i = 1; i < n+1; i++){
            for (int j = 1; j < m+1; j++){
                if(grille[i][j] == 0){
                    int[] couple = {i,j};   // coordonnées de la cellule vide
                    cellsVacantes.add(couple);
                }
            }
        }
    }

    @Override
    public void updateCasesGrille(int K){
        int[][] newGrille = new int[n+2][m+2]; // Grille temporaire pour éviter les conflits

        // On parcourt uniquement les cases utiles (1..n , 1..m)
        for (int i = 1; i < n+1; i++){
            for (int j = 1; j < m+1; j++){

                // 8 voisins de la cellule (i, j)
                int[][] couplesAparcourir = {
                        {i-1, j}, {i-1, j-1}, {i-1, j+1},
                        {i, j-1},
                        {i+1, j-1}, {i+1, j}, {i+1, j+1},
                        {i, j+1}
                };

                int etat = grille[i][j]; // état de l’agent (0 = vide, sinon groupe)

                // On ne traite que les agents (pas les cellules vides)
                if (etat != 0){
                    int cptEtatk = 0; // nombre de voisins différents

                    // Comptage des voisins de type différent (modèle de Schelling)
                    for (int p = 0; p < 8; p++){
                        int idxI = couplesAparcourir[p][0];
                        int idxJ = couplesAparcourir[p][1];
                        int voisin = grille[idxI][idxJ];

                        // voisin ≠ 0 et voisin ≠ groupe de l’agent → voisin différent
                        if (voisin != 0 && voisin != etat) {
                            cptEtatk++;
                        }
                    }

                    // Si trop de voisins différents → l’agent déménage
                    if (cptEtatk >= K){
                        newGrille[i][j] = 0;     // l’agent quitte la cellule (devient vacante)

                        // On ajoute l'ancienne position aux vacants
                        int[] couple = {i,j};
                        cellsVacantes.add(couple);

                        // On prend une des cellules vacantes (ici la première)
                        int[] nvEmplacement = cellsVacantes.get(0);
                        cellsVacantes.remove(0); // Suppression pour éviter réutilisation immédiate

                        // On place l’agent dans sa nouvelle cellule
                        newGrille[nvEmplacement[0]][nvEmplacement[1]] = etat;

                    }
                    else{
                        // L’agent reste où il est
                        newGrille[i][j] = etat;
                    }
                }
            }
        }

        // Mise à jour définitive de la grille
        for (int p = 0; p < n+2; p++){
            for (int q = 0; q < m+2; q++){
                grille[p][q] = newGrille[p][q];
            }
        }
    }

}
