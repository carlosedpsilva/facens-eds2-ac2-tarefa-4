package br.facens.eds2;

public class Aluno {

    private int topicosEscritos = 0;
    private int curtidasRecebidas = 0;
    private int cursosComprados = 0;
    private int cursosDisponiveisParaCompra = 0;

    public int getTopicosEscritos() {
        return topicosEscritos;
    }

    public void setTopicosEscritos(int topicosEscritos) {
        this.topicosEscritos = topicosEscritos;
    }

    public int getCurtidasRecebidas() {
        return curtidasRecebidas;
    }

    public void setCurtidasRecebidas(int curtidasRecebidas) {
        this.curtidasRecebidas = curtidasRecebidas;
    }

    public int getCursosComprados() {
        return cursosComprados;
    }

    public void setCursosComprados(int cursosComprados) {
        this.cursosComprados = cursosComprados;
    }

    public int getCursosDisponiveisParaCompra() {
        return cursosDisponiveisParaCompra;
    }

    public void setCursosDisponiveisParaCompra(int cursosDisponiveisParaCompra) {
        this.cursosDisponiveisParaCompra = cursosDisponiveisParaCompra;
    }

    public void verificarElegibilidadeParaCurso(int cursosDisponieisNaPlataforma) {
        if (this.topicosEscritos >= 2
                && this.curtidasRecebidas >= 5
                && this.cursosComprados < cursosDisponieisNaPlataforma) {
            this.cursosDisponiveisParaCompra++;
        }
    }

}
