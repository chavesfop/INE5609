/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estruturas.pilha;

/**
 *
 * @author chavesfop
 */
public class ListaEncadeadaDe<Tipo> {
    private Tipo[] dados;
    private int[] indice;
    private PilhaDe<Integer> posicoesLivre;
    private int tamanho;
    private int posicaoInicial, posicaoUltimo;
    
    
    
    private int _length(){
        return tamanho - this.posicoesLivre.qtdDadosInseridos();
    }
    
    private int _inserir(Tipo dado) throws Exception{
        if (this._length() >= tamanho){
            throw new Exception("A lista esta cheia");
        }
        int posicaoProximo = this.posicoesLivre.desempilhar();
        this.dados[posicaoProximo] = dado;
        return posicaoProximo;
    }
    
    public ListaEncadeadaDe(int tamanho){
        this.tamanho = tamanho;
        this.dados = (Tipo[]) new Object[tamanho];
        this.indice = new int[tamanho];
        this.posicaoInicial = -1;
        this.posicaoUltimo = -1;
        try{
            for (int i = tamanho-1; i >= 0; i--){
                this.posicoesLivre.empilhar(i);
            }
        }catch(Exception e){
            System.err.print(e);
        }
    }
    
    public void inserirNoFim(Tipo dado) throws Exception{
        boolean inicial = this._length() == 0;
        if (inicial){
            this.inserirNoInicio(dado);
            return;
        }
        
        int posicaoProximo = this._inserir(dado);
        this.indice[posicaoProximo] = -1;
        if (this.posicaoUltimo != -1){
            this.indice[this.posicaoUltimo] = posicaoProximo;
        }
        this.posicaoUltimo = posicaoProximo;
    }
    
    // *[ADD]  -> [FIRST] -> [FIRST_NEXT]
    public void inserirNoInicio(Tipo dado) throws Exception{
        int posicaoProximo = this._inserir(dado);
        int inicialAtual = this.posicaoInicial;
        
        this.posicaoInicial = posicaoProximo;
        this.indice[posicaoProximo] = inicialAtual;
        if (inicialAtual == -1){
            this.posicaoUltimo = posicaoProximo;
        }
    }
    
    // [...] -> [referencia] -> [add] -> [referencia_proximo]
    public void inserirDepoisDe(Tipo referencia, Tipo inserido) throws Exception{
        int posicaoReferencia = this.buscar(referencia);
        if (posicaoReferencia == -1){
            throw new Exception("Elemento não encontrado");
        }
        int posicaoInserida = this._inserir(inserido);
        int inseridoDeveApontar = this.indice[posicaoReferencia];
        this.indice[posicaoReferencia] = posicaoInserida;
        this.indice[posicaoInserida] = inseridoDeveApontar;
        if (inseridoDeveApontar == -1){
            this.posicaoUltimo = posicaoInserida;
        }
    }
    
    public void inserirAntesDe(Tipo referencia, Tipo inserido) throws Exception{
        int posicaoReferencia = this.buscar(referencia);
        if (posicaoReferencia == -1){
            throw new Exception("Elemento não encontrado");
        }
        
        if (this.busca_indiceAnterior == -1){
            this.inserirNoInicio(inserido);
            return;
        }
        
        this.inserirDepoisDe(this.dados[this.busca_indiceAnterior], inserido);
    }
    
    public void percorrer(){
        int i = this.posicaoInicial;
        while (i != -1){
            System.out.println("["+i+"] "+this.dados[i]);
            i = this.indice[i];
        }
    }
    
    
    /*
    Inicio da area de busca
    */
    private int busca_indiceAnterior;
    public int buscar(Tipo dado){
        this.busca_indiceAnterior = -1;
        int i = this.posicaoInicial;
        while (i != -1){
            if (this.dados[i] == dado){
                return i;
            }
            this.busca_indiceAnterior = i;
            i = this.indice[i];
        }
        return -1;
    }
    
    /*
    Fim da area de busca
    */
}
