package br.com.siscultbook.bean;

/**
 *
 * @author Carlos
 */
public enum EstadoDoPedido {

    Aberto,
    Finalizado,
    Cancelado,
    PreparandoParaEnvio,
    AguardandoConfirmacaoPagamento,
    EnviadoTransportadora,
    Entregue,
    Devolvido,
    EmTransito
}
