package br.uece.spring.api.pagamento.application.interfaces;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import br.uece.spring.api.pagamento.application.model.UsuarioDto;

@FeignClient(value = "usuario-ms")
public interface UsuarioFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/usuarios")
    List<UsuarioDto> listarUsuarios();

    @RequestMapping(method = RequestMethod.GET, value = "/usuarios/{codigo}")
    UsuarioDto buscarUsuario(@PathVariable long codigo);
}
