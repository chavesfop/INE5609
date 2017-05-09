/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estruturas.pilha;

/**
 *
 * @author chavesfop
 * @param <Tipo> generics
 */
public class PilhaDe<Tipo> {
    private Tipo[] dados;
    private int tamanho;
    private int qtdDados;
    
    public PilhaDe(int tamanho){
        this.tamanho = tamanho;
        this.dados = (Tipo[]) new Object[tamanho];
        this.qtdDados = 0;
    }

    public PilhaDe(Tipo[] dados, int tamanho) throws Exception {
        if(dados.length > tamanho){
            throw new Exception("A quantidade de dados é maior que "+tamanho);
        }
        this.dados = dados;
        this.tamanho = tamanho;
        this.qtdDados = this.dados.length;
    }
    
    public Tipo desempilhar() throws Exception{
        if (this.qtdDados == 0){
            throw new Exception("A pilha esta vazia");
        }
        return this.dados[--this.qtdDados]; 
    }
    
    public void empilhar(Tipo dado) throws Exception{
        if (this.qtdDados >= this.tamanho){
            throw new Exception("A pilha esta cheia");
        }
        this.dados[this.qtdDados++] = dado;
    }
    
    public int qtdDadosInseridos(){
        return this.qtdDados;
    }
}
