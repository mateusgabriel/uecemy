package br.uece.spring.api.pagamento.application.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.uece.spring.api.pagamento.application.interfaces.CursoFeignClient;
import br.uece.spring.api.pagamento.application.interfaces.UsuarioFeignClient;
import br.uece.spring.api.pagamento.application.model.NovoPagamentoDto;
import br.uece.spring.api.pagamento.application.model.PagamentoDto;
import br.uece.spring.api.pagamento.domain.exception.CursoNaoExistenteException;
import br.uece.spring.api.pagamento.domain.exception.PagamentoNaoRealizadoException;
import br.uece.spring.api.pagamento.domain.exception.UsuarioNaoExistenteException;

@Component
public class PagamentoService {
    
    @Autowired 
    private UsuarioFeignClient usuarioFeignClient;

    @Autowired
    private CursoFeignClient cursoFeignClient;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public PagamentoDto comprarCurso(NovoPagamentoDto dto) throws Exception {
        var usuario = usuarioFeignClient.buscarUsuario(dto.getIdUsuario());
        if (usuario == null) {
            throw new UsuarioNaoExistenteException();
        }

        var curso = cursoFeignClient.buscarCurso(dto.getIdCurso());
        if (curso == null) {
            throw new CursoNaoExistenteException();
        }

        var retorno = new PagamentoDto();
        if (!realizarPagamento(usuario.getNumeroCartaoCredito(), curso.getPreco())) {
            throw new PagamentoNaoRealizadoException();
        }

        retorno.setMensagem("Pagamento realizado com sucesso.");
        dispatchNotificarPagamento(dto);
        return retorno;
    }

    private boolean realizarPagamento(String numeroCartao, double preco) {
        if (preco == 0) {
            return false;
        }
        
        if (numeroCartao.equals("0123698574521698") && preco == 462) {
            return true;
        }

        if (numeroCartao.equals("0012302165900147") && preco == 580) {
            return false;
        }

        if (numeroCartao.startsWith("0") && preco >= 100) {
            return true;
        }

        if (numeroCartao.endsWith("9") && preco <= 0) {
            return true;
        }

        return false;
    }

    private void dispatchNotificarPagamento(NovoPagamentoDto dto) throws Exception {
        var exchange = "notification-exchange";
        var routingKey = "notification.success.pagamento.push";

        try {
            amqpTemplate.convertAndSend(exchange, routingKey, objectMapper.writeValueAsString(dto));
        } catch(Exception ex) {
            throw new Exception("Falha ao postar mensagem na fila.");
        }
        
    }
}
