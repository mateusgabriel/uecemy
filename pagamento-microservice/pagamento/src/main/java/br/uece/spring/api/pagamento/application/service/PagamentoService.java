package br.uece.spring.api.pagamento.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uece.spring.api.pagamento.application.interfaces.CursoFeignClient;
import br.uece.spring.api.pagamento.application.interfaces.UsuarioFeignClient;
import br.uece.spring.api.pagamento.application.model.NovoPagamentoDto;
import br.uece.spring.api.pagamento.application.model.PagamentoDto;

@Service
public class PagamentoService {
    
    @Autowired 
    private UsuarioFeignClient usuarioFeignClient;

    @Autowired
    private CursoFeignClient cursoFeignClient;

    public PagamentoDto comprarCurso(NovoPagamentoDto dto) {
        var usuario = usuarioFeignClient.buscarUsuario(dto.getIdUsuario());
        if (usuario == null) {
            return null;
        }

        var curso = cursoFeignClient.buscarCurso(dto.getIdCurso());
        if (curso == null) {
            return null;
        }

        var retorno = new PagamentoDto();
        if (!realizarPagamento(usuario.getNumeroCartaoCredito(), curso.getPreco())) {
            retorno.setMensagem("Pagamento não realizado.");
            return retorno;
        }

        retorno.setMensagem("Pagamento realizado com sucesso.");
        return retorno;
    }

    private boolean realizarPagamento(String numeroCartao, double preco) {
        if (numeroCartao.equals("0123698574521698") && preco == 462) {
            return true;
        }

        if (numeroCartao.equals("0012302165900147") && preco == 580) {
            return false;
        }

        return false;
    }
}
