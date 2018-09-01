package main.java.com.github.Lanchonete.controller;

import java.util.ArrayList;
import java.util.List;

import main.java.com.github.Lanchonete.model.Comanda;
import main.java.com.github.Lanchonete.model.Cozinha;
import main.java.com.github.Lanchonete.model.Pedido;

/**
 * Esta classe faz o gerenciamento das mesas.
 *
 * @see Comandas.
 * @see Gerencia.
 * @see Pedido.
 * @since 1.8.
 * @version 1.0.
 * @author Diones Gomes
 */
public class GerenciaMesa {

    private List<Comanda> mesas;

    /**
     * Iniciando o ArrayList, sem passar parâmentros.
     */
    public GerenciaMesa() {
        mesas = new ArrayList<>();
    }

    /**
     * Método para adicionar uma nova comanda para uma mesa.
     *
     * @param mesa Refere-se ao número da mesa que abriu uma comanda.
     * @return true ou false.
     */
    public boolean novaComanda(int mesa) {
        if (buscar(mesa) != -1) {// verifica se a mesa existe ou não 
            return false; // não pode ser aberta duas ou mais comandas na mesma mesa
        }
        return mesas.add(new Comanda(mesa));
    }

    /**
     * Método para fechar a comenada e adicionando ela na lista de comandas
     * fechadas.
     *
     * @param mesa Refere-se ao número da mesa que que abriu a comanda.
     * @return true ou false.
     */
    public boolean fecharComanda(int mesa) {
        if (buscar(mesa) == -1) {// verifica se a mesa existe ou não
            return false;// não se pode encerrar uma comanda que não existe
        }
        for (Pedido p : getComanda(mesa).getListarPedidos()) {
            if (p.isStatus() == false) {// não se pode encerrar uma comanda que não tenha sido atendido todos pedidos
                return false;
            }
        }
        return Gerencia.adicionarComanda(mesas.remove(buscar(mesa)));//  adiciona a comanda para gerenciar 
    }

    /**
     * Método para fazer um novo pedido para uma comanda que já está em aberto.
     *
     * @param mesa Refere-se ao número da mesa onde a comanda faz refência.
     * @param pedido Refere-se ao pedido da comanda.
     * @param cozinha Refer-se a cozinha para adicionar um novo pedido.
     * @return true para pedido adicionado, ou false para pedido não adicionado.
     */
    public boolean fazerPedido(int mesa, Pedido pedido, Cozinha cozinha) {
        if (buscar(mesa) != -1 && pedido.getProduto() != null) {
            cozinha.adicionarPedido(pedido); // adiciona à cozinha                            
            return mesas.get(buscar(mesa)).adicionaPedido(pedido);
        }
        Pedido.setContPedidos(Pedido.getContPedidos() - 1);// decrementa o contador de pedido pois o pedido p não pode ser feito então ele não sera contado com os demais
        return false;
    }

    /**
     * Método para visualização de todos os pedidos de uma mesa.
     *
     * @param mesa Refere-se a mesa que contém os pedidos.
     * @return uma String contendo todas os pedidos de uma mesa.
     */
    public String verPedidos(int mesa) {// retorna uma string com todos os pedidos de uma determinada comanda
        if (buscar(mesa) == -1) {
            return "";
        }
        return mesas.get(buscar(mesa)).toString();
    }

    /**
     * Método para modificar um pedido realizado para uma mesa.
     *
     * @param mesa Refere-se a mesa que o pedido faz referência.
     * @param pedido Refere-se ao pedido a ser a modificado.
     * @param numeroPedido Referese-se ao número do pedido a ser alterado.
     * @param cozinha Referencia a cozinha onde o pedido está em aberto para a
     * moficação.
     * @return true para pedido alterado ou false para pedido não alterado.
     */
    public boolean modificarPedido(int mesa, Pedido pedido, int numeroPedido, Cozinha cozinha) {
        if (ehNumeroPedidoValido(mesa, numeroPedido)) {// verifica se o numero do pedido existe (tambem verifica se a mesa existe) 
            if (!(mesas.get(buscar(mesa)).getPedido(numeroPedido).isStatus())) {//verifica se ele já não foi atendido
                Pedido.setContPedidos(Pedido.getContPedidos() - 1);//pelo fato de ter sido necessário instanciar um novo pedido, so para modificar um já existente, é decrementado o contador de pedidos
                pedido.setNumeroPedido(numeroPedido); // o pedido novo, que servirá para modificar o antigo, tem seu numero definido para o do pedido antigo 
                excluirPedido(mesa, numeroPedido, cozinha);//o pedido antigo é excluido da comanda e tambem da cozinha
                fazerPedido(mesa, pedido, cozinha);//o novo pedido é adicionado a comanda e a cozinha, substituindo o antigo 
                return true;
            }
        }
        return false;
    }

    /**
     * Método para remover um pedido da mesa juntamente também na cozinha.
     *
     * @param mesa Refere-se ao número da mesa que contém o pedido que será
     * removido.
     * @param numeroPedido Refere-se ao número do pedido que será removido.
     * @param cozinha Refere-se a cozinha que terá o pedido removido.
     * @return true para pedido removido ou false para pedido não removido.
     */
    public boolean excluirPedido(int mesa, int numeroPedido, Cozinha cozinha) {
        if (ehNumeroPedidoValido(mesa, numeroPedido)) {// verifica se o numero do pedido existe (tambem verifica se a mesa existe) 
            if (!(mesas.get(buscar(mesa)).getPedido(numeroPedido).isStatus())) {//verifica se ele já não foi atendido
                cozinha.removePedido(numeroPedido); //remover da lista da cozinha
                return mesas.get(buscar(mesa)).removePedido(numeroPedido);
            }
        }
        return false;
    }
    ///////////////////////////////////////////////////////////////////////////////////////

    /**
     * Método para buscar uma mesa na lista de mesas.s
     *
     * @param mesa Refere-se ao número da mesa que será recuperado.
     * @return 1 para mesa encontrada ou -1 caso a mesa não exista.
     */
    int buscar(int mesa) {
        if (!(mesas.isEmpty())) {// não se pode buscar comandas se elas não existem
            for (int i = 0; i < mesas.size(); i++) {
                if (mesas.get(i).getMesa() == mesa) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Recupera a mesa na lista de mesas.
     *
     * @param mesa Refere-se ao número da mesa a ser consultada.
     * @return a mesa caso exista, ou null se não existe.
     */
    public Comanda getComanda(int mesa) {//retorna a (tudo da)comanda de uma mesa especifica (relaciona esta classe com Cozinha) /////não confundir com getListaPedido() que retorna a lista de todos os pedidos da comanda mas não retorna data, numero e mesa 
        return mesas.get(buscar(mesa));
    }

    /**
     * Método para verificar se a mesa existe jutamente com um pedido.
     *
     * @param mesa Refere-se ao número da mesa que sera consultada.
     * @param numeroPedido Refere-se ao número da mesa que será consultada.
     * @return true para se a mesa e o pedido existem ou false caso contrário.
     */
    private boolean ehNumeroPedidoValido(int mesa, int numeroPedido) { // verifica se o numero do pedido e a mesa existem
        if (buscar(mesa) != -1) {
            return mesas.get(buscar(mesa)).buscarPedido(numeroPedido) != -1; // retorna o valor lógico da comparação
        }
        return false;
    }
}
