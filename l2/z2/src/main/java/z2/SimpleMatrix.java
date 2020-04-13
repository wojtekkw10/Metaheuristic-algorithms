package z2;

import java.util.ArrayList;

public class SimpleMatrix {
    public ArrayList<SubMatrix> matrix = new ArrayList<>();
    public int width;
    public int height;
    public int K;

    public SimpleMatrix(int width, int height, int K){
        this.height = height;
        this.width = width;
        this.K = K;
    }

    public ArrayList<SubMatrix> getMatrix(){
        return matrix;
    }

    public void addSubMatrix(SubMatrix sub){
        matrix.add(sub);
    }

    /**
     * Funkcja sprawdza dana macierz jest poprawna
     * tzn. czy bloki maja rozmiar wiekszy niz K
     * czy macierz jest w pelni wypelniona blokami
     * czy bloki nie nachodza na siebie
     * Jesli warunki sa spelnione to zwraca falsz
     * W przeciwnym wypadku false
     * @return czy macierz jest poprawna
     */
    public boolean check(){
        //initialize fullMatrix to -1
        ArrayList<ArrayList<Integer>> fullMatrix = new ArrayList<>();
        //generowanie pustej macierzy
        for (int i = 0; i < width; i++) {
            ArrayList<Integer> column = new ArrayList<>();
            for (int j = 0; j < height; j++) {
                column.add(-1);
            }
            fullMatrix.add(column);
        }

        //wypelnienie macierzy ze sprawdzeniem nakladania sie blokow
        for (int i = 0; i < matrix.size(); i++) {
            SubMatrix sub = matrix.get(i);
            for(int j=0; j<sub.width; j++){
                for(int k=0; k<sub.height; k++){
                    if(fullMatrix.get(j+sub.posX).get(k+sub.posY) == -1){
                        fullMatrix.get(j+sub.posX).set(k+sub.posY, sub.value);
                    }
                    else {
                        return false;
                    }
                }
            }
        }
        return isFullyFilled(fullMatrix) && respectsK(matrix);
    }

    /**
     * Funkcja sprawdza czy macierz jest w pelni pokryta
     * @param matrix dana macierz
     * @return czy jest pokryta
     */
    private boolean isFullyFilled(ArrayList<ArrayList<Integer>> matrix){
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                if(matrix.get(i).get(j) == -1) return false;
            }
        }
        return true;
    }

    /**
     * Funkcja sprawdza czy bloki macierzy maja wielkosc > K
     * @param matrix dana amacierz
     * @return czy bloki sa wieksze niz K
     */
    private boolean respectsK(ArrayList<SubMatrix> matrix){
        for (int i = 0; i < matrix.size(); i++) {
            if(matrix.get(i).width < K || matrix.get(i).height < K) return false;
        }
        return true;

    }

    /**
     * Funkcja konwertuje macierz z postaci listy blokow do postaci pelnej (tablicy kwadratowej)
     * @param matrix macierz w postaci listy blokow
     * @return macierz pelna
     */
    private ArrayList<ArrayList<Integer>> toFullMatrix(ArrayList<SubMatrix> matrix){
        //initialize fullMatrix to -1
        ArrayList<ArrayList<Integer>> fullMatrix = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            ArrayList<Integer> column = new ArrayList<>();
            for (int j = 0; j < height; j++) {
                column.add(-1);
            }
            fullMatrix.add(column);
        }

        for (int i = 0; i < matrix.size(); i++) {
            SubMatrix sub = matrix.get(i);
            for(int j=0; j<sub.width; j++){
                for(int k=0; k<sub.height; k++){
                    fullMatrix.get(j+sub.posX).set(k+sub.posY, sub.value);
                }
            }
        }
        return fullMatrix;
    }

    public ArrayList<ArrayList<Integer>> getFullMatrix(){
        return toFullMatrix(this.matrix);
    }

    /**
     * Funkcja rysuje macierz w postaci pelnej ładnej tzn kazdy wiersz ma taka sama szerokosc
     * Oraz każda wartosc w macierzy ma inny kolor
     * @return string z macierza
     */
    public String printColor(){
        String[] colors = {"\u001B[30m", "\u001B[31m", "\u001B[32m", "\u001B[33m", "\u001B[34m", "\u001B[35m", "\u001B[36m", "\u001B[37m"};
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(0);
        numbers.add(32);
        numbers.add(64);
        numbers.add(128);
        numbers.add(160);
        numbers.add(192);
        numbers.add(223);
        numbers.add(256);
        StringBuilder sb = new StringBuilder();
        if(check()) sb.append("Matrix Congruent\n");
        else sb.append("WARNING: Matrix NOT congruent\n");
        ArrayList<ArrayList<Integer>> fullMatrix = toFullMatrix(this.matrix);

        for (int i = 0; i < fullMatrix.size(); i++) {
            for (int j = 0; j < fullMatrix.get(i).size(); j++) {

                if(numbers.indexOf(fullMatrix.get(i).get(j)) != -1){
                    sb.append(colors[numbers.indexOf(fullMatrix.get(i).get(j))]);
                }
                sb.append(fullMatrix.get(i).get(j));
                if(fullMatrix.get(i).get(j).toString().length() == 1) sb.append("   ");
                if(fullMatrix.get(i).get(j).toString().length() == 2) sb.append("  ");
                if(fullMatrix.get(i).get(j).toString().length() == 3) sb.append(" ");
                sb.append("\u001B[0m");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Funkcja rysuje macierz w postaci pelnej surowej
     * Potrzebna ze wzgledu na wymagania zadania
     * @return string z macierza
     */
    public String printClean(){
        StringBuilder sb = new StringBuilder();
        ArrayList<ArrayList<Integer>> fullMatrix = toFullMatrix(this.matrix);

        for (int i = 0; i < fullMatrix.size(); i++) {
            for (int j = 0; j < fullMatrix.get(i).size(); j++) {
                sb.append(fullMatrix.get(i).get(j)).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
