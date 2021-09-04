/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principalempresaimportacao;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author 
 */
public class AplicacaoEstoque {
   
    private Estoque estoquelist[] = new Estoque[100];
    private int posicaoAtual = 0;

    public static void main(String[] args) {
         AplicacaoEstoque app = new AplicacaoEstoque ();
         app.menu();
         app.telaPrincipal();
    }
    
    private void telaPrincipal(){ //tela de menu inicial
        int opcao = 0;
        do{
            opcao = menuInicial();
            switch (opcao){
                case 1:
                    menuCadastroDeProduto();
                    break;
                case 2:
                    menuMovimentacao();
                    break;
                case 3:
                    menuRelatorioEstoque();
                    break;
                case 4:
                    alterarPreco();
                            
                case 0:
                    System.out.println("Saindo do sistema");
                    break;
                default:
                    opcaoInvalida();
                    break; 
            }
         }while(opcao != 0);
   }
    private void menuRelatorioEstoque() { //RELATÓRIO 
        this.menu();
        System.out.println("RELATÓRIO");
        for (int i = 0; i < posicaoAtual; i++) {
            System.out.println("\n");
            System.out.println("Produto: \n" +
                    "CÓDIGO: " + i + "\n" +
                    estoquelist[i]);
            System.out.println("SALDO ATUALIZADO");
            Estoque estoqueMovimentacao = estoquelist[i];
                    double saldo = estoqueMovimentacao.getSaldo();

        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n");
        System.out.println("APERTE QUALQUER TECLA + ENTER PARA CONTINUAR");
        scanner.next();
        }
        
    }
    
    private void menuMovimentacao(){  //MOVIMENTAÇÃO
        Scanner scanner = new Scanner(System.in);
        System.out.println("EMPRESA DE IMPORTAÇÃO DE PRODUTOS LTDA.\nSISTEMA DE CONTROLE DE ESTOQUE MOVIMENTAÇÃO\n");
        System.out.println("1 - ENTRADA\n" + "2 - SA�?DA\n" + "0 - RETORNAR\n" + "OPÇÃO  : \n");
        int opcaoMovimentacao = scanner.nextInt();
        switch (opcaoMovimentacao){
            case 1:
                compraProduto();
                break;
            case 2:
                saidaProduto();
                break;
            case 0:
                System.out.println("Retornando para o menu.");
                break;
            default:
                opcaoInvalida();
                break;    
        }
                
    }
    
    private void saidaProduto(){ //O que mostra na tela do usuário sobre  a saida de produto
        String escolha;
        do{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Produtos em estoque:");
            String nomeProduto = scanner.nextLine();
            
            boolean controle = true;
            for (int i = 0; i < posicaoAtual; i++){
                if (nomeProduto.equalsIgnoreCase( estoquelist[i].getNomeDoProduto())){
                    controle=false;
                    Estoque estoqueMovimentacao = estoquelist[i];
                    System.out.println("Quantidade atual: " + estoqueMovimentacao.getQuantidaEmEstoque());
                    System.out.println("Quantidade saída: ");
                    int quantidadeSaida = scanner.nextInt();
                    System.out.println("Quantidae final: " + (estoqueMovimentacao.getQuantidaEmEstoque() - quantidadeSaida));
                    
                    if (estoqueMovimentacao.getQuantidaEmEstoque() < quantidadeSaida){
                        System.out.println("Quantidade maior que no estoque, saída não não permitida.");
                        break;
                    }
                    escolha = confirmaOperacao();
                    if(escolha.equalsIgnoreCase("S")){
                        estoqueMovimentacao.setDiminuirQuantidadeEmEstoque(quantidadeSaida);
                        estoquelist[i] = estoqueMovimentacao;
                    }
                   break;
                }
            }
            mensagemConsultaNaoEncontrada(controle);
            escolha = getRepetirOperacao();
        }
        while(escolha.equalsIgnoreCase("S"));
    }
    private void compraProduto(){ //entrada de produto no estoque
        String escolha;
        do{
            Scanner scanner = new Scanner (System.in);
            System.out.println("Compra de produtos");
            System.out.println("Nome do produto");
            String nomeProduto = scanner.nextLine();
            Estoque estoqueMovimentacao;
            boolean controle = true;
            for(int i =0; i < posicaoAtual; i++){
                if (nomeProduto.equalsIgnoreCase(estoquelist[i].getNomeDoProduto()))
                    controle = false;
                estoqueMovimentacao = estoquelist[i];
                System.out.println("Quantidade atual: " + estoqueMovimentacao.getQuantidaEmEstoque());
                System.out.println("Quantidade de entrada: ");
                int quantidadeEntrada = scanner.nextInt();
                System.out.println("Quantidade final :" + (estoqueMovimentacao.getQuantidaEmEstoque()+ quantidadeEntrada));
                escolha = confirmaOperacao();
                if (escolha.equalsIgnoreCase("S")){
                    estoqueMovimentacao.setAdicionaQuantidadeEmEstoque(quantidadeEntrada);
                    estoquelist[i] = estoqueMovimentacao;
                }
                break;
            
            }
        mensagemConsultaNaoEncontrada(controle);
        escolha = getRepetirOperacao();
        
    }while (escolha.equalsIgnoreCase("S"));
}
    private void opcaoInvalida(){
        System.out.println("Opção Inválida");
    }
    private int menuInicial(){  // tela do menu inicial
        int opcao;
        System.out.println("MENU PRINCIPAL\n" + "1 - CADASTRO DE PRODUTO\n" + "2 - MOVIMENTAÇÃO\n" + "3 - RELATÓRIOS\n" + "4 - REAJUSTE DE PREÇO\n" + "0 - FINALIZAR\n" + "OPÇÃO  : _\n");
        opcao = getEscolhaMenu();
        return opcao;
       }
    private void menuCadastroDeProduto(){ // para cadastro de produto
        int opcao;
        System.out.println("1 - INCLUSÃO\n" + "2 - ALTERAÇÃO\n" + "3 - CONSULTA\n" + "4 - EXCLUSÃO\n" + "0 - RETORNAR\n");
        opcao = getEscolhaMenu();
        switch (opcao){
            case 1:
                cadastrarProduto();
                break;
            case 2:
                alterarProduto();
                break;
            case 3:
                consultarProduto();
                break;
            case 4:
                excluirProduto();
                break;
            default:
                opcaoInvalida();
                break;               
        }
    }
    
    private void excluirProduto(){
        String escolha;
        do{
            Scanner scanner = new Scanner(System.in);
            this.menu();
            System.out.println("Exclusão de Produto");
            System.out.println("Informe o nome do produto para pesquisa.");
            String nomeConsulta = scanner.nextLine();
            boolean controle = true;
            ArrayList<Estoque> arrayList = new ArrayList();
            arrayList.add(new Estoque());
            for (int i = 0; i < posicaoAtual; i++){
                scanner = new Scanner(System.in);
                Estoque estoque = arrayList.get(i);
                if (nomeConsulta.equalsIgnoreCase(estoquelist[i].getNomeDoProduto())){
                  controle = false;  
                  System.out.println(estoquelist[i].toString());
                  System.out.println("CONFIRMA EXCLUSÃO ( S/N ) ?");
                  escolha = scanner.next();
                  if (escolha.equalsIgnoreCase("S")){
                      for (int j = i; j < posicaoAtual - 0; j++){
                          estoquelist[j] = estoquelist[j + 1];
                          posicaoAtual -- ;
                      }
                  }
                  break;
                }
                
            }
            mensagemConsultaNaoEncontrada(controle);
            escolha = getRepetirOperacao();
        }while (escolha.equalsIgnoreCase("S")); 
    }
    private void consultarProduto(){
        String escolha;
        do{
            Scanner scanner = new Scanner(System.in);
            this.menu();
            System.out.println("CONSULTA DOS PRODUTOS");
            System.out.println("Informe o nome do mantimento para pesquisa");
            String nomeConsulta = scanner.nextLine();
            boolean controle=true;
            for (int i = 0; i < posicaoAtual; i++){
                if (nomeConsulta.equalsIgnoreCase(estoquelist[i].getNomeDoProduto())){
                    controle = false;
                    System.out.println(estoquelist[i].toString());
                    break;
                }
            }
            mensagemConsultaNaoEncontrada(controle);
            escolha = getRepetirOperacao();
            
        }while(escolha.equalsIgnoreCase("S"));
    }
  
    
    private void alterarProduto(){
        String escolha;
        do{
            Scanner scanner = new Scanner(System.in);
            this.menu();
            System.out.println("Alteraração de produto");
            System.out.println("Informe o nome do produto para alterar");
            String nomeConsulta = scanner.nextLine();
            boolean controle=true;
            for (int i = 0; i < posicaoAtual; i++){
                if (nomeConsulta.equalsIgnoreCase(estoquelist[i].getNomeDoProduto())){
                    controle = false;
                    System.out.println("Produto encotrado\n");
                    Estoque estoque = setDadosEstoque(); 
                    escolha = confirmaOperacao();
                    if (escolha.equalsIgnoreCase("S")){
                        estoquelist[i] = estoque;
                    }
                    break;
                    
                            
                }
            }
            mensagemConsultaNaoEncontrada(controle);
            escolha = getRepetirOperacao();
        }while (escolha.equalsIgnoreCase("S"));
    }
    
    private void mensagemConsultaNaoEncontrada(boolean controle){
        if(controle){
            System.out.println("Produto não encontrado");
        }
    }
    private void cadastrarProduto(){
        String escolha;
        do{
            this.menu();
            System.out.println("Cadastro de produto:");
            Estoque estoque =setDadosEstoque();
            escolha = confirmaOperacao();
            if (escolha.equalsIgnoreCase("S")){
                estoquelist[posicaoAtual] = estoque;
                posicaoAtual++;
            }
            escolha = getRepetirOperacao();
        } while (escolha.equalsIgnoreCase("S"));
    }
     
    private Estoque setDadosEstoque() {  //entrada de dados do teclado.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o nome do produto:");
        String nome = scanner.nextLine();
        System.out.println("Informe a unidade de medida (KG, lt, g...):");
        scanner = new Scanner(System.in);
        String unidade = scanner.nextLine();
        System.out.println("Informe a quantidade:");
        int quantidade = scanner.nextInt();
        System.out.println("Informe o preço desde item: ");
        double preco = scanner.nextDouble();

        Estoque estoque = new Estoque();
        estoque.setNomeDoProduto(nome);
        estoque.setUnidade(unidade);
        estoque.setQuantidadeEmEstoque(quantidade);
        estoque.setPreco(preco);
        return estoque;
}
    
    private void alterarPreco(){ //O que mostra na tela do usuário 
        String escolha;
        do{
            Scanner scanner = new Scanner(System.in);
            System.out.println("ALTERAÇÃO DE PREÇO");
            System.out.println("Nome do produto:");
            String nomeProduto = scanner.nextLine();
            
            boolean controle = true;
            for (int i = 0; i < posicaoAtual; i++){
                if (nomeProduto.equalsIgnoreCase( estoquelist[i].getNomeDoProduto())){
                    controle=false;
                    Estoque estoqueMovimentacao = estoquelist[i];
                    System.out.println("Preço atual: " + estoqueMovimentacao.getPreco());
                    System.out.println("Novo preço: ");
                    double precoAtual = scanner.nextDouble();
                    
                    if (precoAtual < 0){
                        System.out.println("Preço inválido, insira algum acima de 0");
                        break;
                    }
                    escolha = confirmaOperacao();
                    if(escolha.equalsIgnoreCase("S")){
                        estoqueMovimentacao.setPreco(precoAtual);
                        estoquelist[i] = estoqueMovimentacao;
                    }
                   break;
                }
            }
            mensagemConsultaNaoEncontrada(controle);
            escolha = getRepetirOperacao();
        }
        while(escolha.equalsIgnoreCase("S"));
    }
    
   private String getRepetirOperacao() {
        Scanner scanner = new Scanner(System.in);
        String escolha;
        System.out.println("REPETIR OPERAÇÃO ( S/N ) ? ");
        escolha = scanner.next();
        return escolha;
   }
   private String confirmaOperacao() {
        Scanner scanner = new Scanner(System.in);
        String escolha;
        System.out.println("CONFIRMA OPERAÇÃO ( S/N ) ?");
        escolha = scanner.next();
        return escolha;
   }
   private int getEscolhaMenu() {
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.next());
    }
   private void menu() {
        System.out.println("EMPRESA DE IMPORTAÇÃO DE PRODUTOS LDTA. SISTEMA DE CONTROLE DE ESTOQUE.");
        System.out.println("___");
    }
}
  
    



