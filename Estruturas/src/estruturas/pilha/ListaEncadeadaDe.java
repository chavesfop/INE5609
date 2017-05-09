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
    
    public void adicionarNoFim(Tipo dado) throws Exception{
        if (this._length() >= tamanho){
            throw new Exception("A lista esta cheia");
        }
        boolean inicial = this._length() == 0;
        int posicaoProximo = this.posicoesLivre.desempilhar();
        
        if (inicial){
            this.posicaoInicial = posicaoProximo;
        }
        this.dados[posicaoProximo] = dado;
        this.indice[posicaoProximo] = -1;
        this.indice[this.posicaoUltimo] = posicaoProximo;
        this.posicaoUltimo = posicaoProximo;
    }
    
    // *[ADD]  -> [FIRST] -> [FIRST_NEXT]
    public void adicionarNoInicio(Tipo dado) throws Exception{
        if (this._length() >= tamanho){
            throw new Exception("A lista esta cheia");
        }
        int posicaoProximo = this.posicoesLivre.desempilhar();
        int inicialAtual = this.posicaoInicial;
        
        this.posicaoInicial = posicaoProximo;
        this.dados[posicaoProximo] = dado;
        this.indice[posicaoProximo] = inicialAtual;
        if (inicialAtual == -1){
            this.posicaoUltimo = posicaoProximo;
        }
    }
    
    public void percorrer(){
        int i = this.posicaoInicial;
        while (i != -1){
            System.out.println("["+i+"] "+this.dados[i]);
            i = this.indice[i];
        }
    }
    
    public int buscar(Tipo dado){
        int i = this.posicaoInicial;
        while (i != -1){
            if (this.dados[i] == dado){
                return i;
            }
            System.out.println("["+i+"] "+this.dados[i]);
            i = this.indice[i];
        }
        return 0;
    }
    
    public Tipo proximo(){
        return null;
    }
    
}
