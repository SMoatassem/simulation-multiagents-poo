import java.util.ArrayList;

public class SchellingGrille extends Grille{
    private ArrayList<int[]> cellsVacantes = new ArrayList<int[]>();


    public SchellingGrille(int n, int m){
        super(n, m);
        // On cherche les cellules vacantes initialement
        for (int i = 1; i < n+1; i++){
            for (int j = 1; j < m+1; j++){
                if(grille[i][j] == 0){
                    int[] couple = {i,j};
                    cellsVacantes.add(couple);
                }
            }
        }
    }

    @Override
    public void updateCasesGrille(int K){
        int[][] newGrille = new int[n+2][m+2]; // Grille temporaire
        for (int i = 1; i < n+1; i++){
            for (int j = 1; j < m+1; j++){
                int[][] couplesAparcourir = { {i-1, j}, {i-1, j-1}, {i-1, j+1}, {i, j-1}, {i+1, j-1},
                        {i+1, j}, {i+1, j+1}, {i, j+1}};
                int etat = grille[i][j];
                int cptEtatk = 0;
                for (int p = 0; p < 8; p++){
                    int idxI = couplesAparcourir[p][0];
                    int idxJ = couplesAparcourir[p][1];
                    boolean diff = (grille[idxI][idxJ] != etat);
                    // ajout de %nombreEtats car sinon on ne revient jamais à 0
                    int voisin = grille[idxI][idxJ];
                    if (voisin != 0 && voisin != etat) {
                        cptEtatk++;
                    }                }
                if (cptEtatk >= K){
                    newGrille[i][j] = 0;
                    int[] couple = {i,j};
                    cellsVacantes.add(couple);
                    int[] nvEmplacement = cellsVacantes.get(0);
                    cellsVacantes.remove(0); // faut s'assurer qu'on a au moins un vide au début

                    newGrille[nvEmplacement[0]][nvEmplacement[1]] = etat;
                }
                else{
                    newGrille[i][j] = etat;
                }
            }

            }
        for (int p = 0; p < n+2; p++){
            for (int q = 0; q < m+2; q++){
                grille[p][q] = newGrille[p][q];
            }
        }
        }

//        System.out.println("après " + Arrays.deepToString(grille));
}
