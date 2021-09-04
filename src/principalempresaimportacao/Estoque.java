/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principalempresaimportacao;

/**
 *
 * @author Joao Caetano
 */
public class Estoque {
    private String nomedoproduto;
    private double precounitario;
    private String unidade;
    private int quantidadeEmEstoque;
    private double preco;
    
    void setNomeDoProduto(String nomeDoProduto){
        this.nomedoproduto = nomeDoProduto;
    }
    String getNomeDoProduto(){
        return this.nomedoproduto;
    }
     void setPrecoUnitario(double precoUnitario){
        this.precounitario = precoUnitario;
    }
    double getPrecoUnitario(){
        return this.precounitario;
    }
     void setUnidade(String unidade){
        this.unidade = unidade;
    }
    String getUnidade(){
        return this.unidade;
    }
     void setAdicionaQuantidadeEmEstoque(int adicionaQuantidadeEmEstoque){
        this.quantidadeEmEstoque += quantidadeEmEstoque;
    }
    void setDiminuirQuantidadeEmEstoque(int diminuirQuantidadeEmEstoque ){
         this.quantidadeEmEstoque -= diminuirQuantidadeEmEstoque;
         } 
    int getQuantidaEmEstoque(){
        return this.quantidadeEmEstoque;
    }
     void setQuantidadeEmEstoque(int quantidadeEmEstoque){
        this.quantidadeEmEstoque = quantidadeEmEstoque;
     }
     void setPreco (double preco){
         this.preco = preco;
     }
     double getPreco (){
         return this.preco;
     }
     @Override
    public String toString() {
        return "NOME: " + nomedoproduto + "\n" +
                "UNIDADE: " + unidade + "\n" +
                "QUANTIDADE: " + quantidadeEmEstoque + "\n" + 
                "PREÇO UNIDADE: " + preco;
    }
   
     double getSaldo(){
     double saldo = getPreco() * getQuantidaEmEstoque();
                    System.out.println("Total: " + saldo);
                   
        return 0;
    }
     
    
    
}
