import java.util.Scanner;

public class Main {

    public static No raiz;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String entrada = scanner.nextLine();
            if (entrada.startsWith("I ")) {
                String letra = entrada.substring(2);
                inserir(raiz, letra);
            } else if (entrada.equals("INFIXA")) {
                imprimirINFIXA(raiz);
                System.out.println();
            } else if (entrada.equals("PREFIXA")) {
                imprimirPREFIXA(raiz);
                System.out.println();
            } else if (entrada.equals("POSFIXA")) {
                imprimirPOSFIXA(raiz);
                System.out.println();
            } else if (entrada.startsWith("P ")) {
                String letra = entrada.substring(2);
                System.out.println(letra + " " + pesquisar(raiz, letra));
            }
        }
        scanner.close();
    }

    public static void inserir(No no, String letra) {
        raiz = inserirRecursivamente(no, letra);
    }

    public static No inserirRecursivamente(No no, String letra) {
        if (no == null) {
            return new No(letra, null, null);
        }

        int comp = letra.compareTo(no.getLetra());

        if (comp < 0) {
            no.setEsquerdo(inserirRecursivamente(no.getEsquerdo(), letra));
        } else if (comp > 0) {
            no.setDireito(inserirRecursivamente(no.getDireito(), letra));
        }
        return no;
    }

    public static String pesquisar(No no, String letra) {
        if (no == null) return "nao existe";

        int comp = letra.compareTo(no.getLetra());

        if (comp == 0) return "existe";

        if (comp < 0) return pesquisar(no.getEsquerdo(), letra);
        return pesquisar(no.getDireito(), letra);
    }

    public static void imprimirINFIXA(No no) {

        if (no == null) return;

        imprimirINFIXAAux(no, new StringBuilder());
    }

    private static void imprimirINFIXAAux(No no, StringBuilder sb) {
        if (no == null) return;
        imprimirINFIXAAux(no.getEsquerdo(), sb);
        if (sb.length() > 0) sb.append(" ");
        sb.append(no.getLetra());
        imprimirINFIXAAux(no.getDireito(), sb);
        if (sb.length() > 0 && no == raiz) System.out.print(sb.toString());
    }

    public static void imprimirPREFIXA(No no) {
        if (no == null) return;
        imprimirPREFIXAAux(no, new StringBuilder());
    }

    private static void imprimirPREFIXAAux(No no, StringBuilder sb) {
        if (no == null) return;
        if (sb.length() > 0) sb.append(" ");
        sb.append(no.getLetra());
        imprimirPREFIXAAux(no.getEsquerdo(), sb);
        imprimirPREFIXAAux(no.getDireito(), sb);
        if (sb.length() > 0 && no == raiz) System.out.print(sb.toString());
    }

    public static void imprimirPOSFIXA(No no) {
        if (no == null) return;
        imprimirPOSFIXAAux(no, new StringBuilder());
    }

    private static void imprimirPOSFIXAAux(No no, StringBuilder sb) {
        if (no == null) return;
        imprimirPOSFIXAAux(no.getEsquerdo(), sb);
        imprimirPOSFIXAAux(no.getDireito(), sb);
        if (sb.length() > 0) sb.append(" ");
        sb.append(no.getLetra());
        if (sb.length() > 0 && no == raiz) System.out.print(sb.toString());
    }

    // criei uma classe auxiliar pra servir de nó da árvore, tive que socar dentro do Main pra usar no teste
    static class No {
        private String letra;
        private No esquerdo;
        private No direito;
        public No(String letra, No esquerdo, No direito) {
            this.letra = letra;
            this.esquerdo = esquerdo;
            this.direito = direito;
        }
        public String getLetra() {
            return letra;
        }
        public void setLetra(String letra) {
            this.letra = letra;
        }
        public No getEsquerdo() {
            return esquerdo;
        }
        public void setEsquerdo(No esquerdo) {
            this.esquerdo = esquerdo;
        }
        public No getDireito() {
            return direito;
        }
        public void setDireito(No direito) {
            this.direito = direito;
        }
    }
}