package br.uece.spring.api.pagamento.application.interfaces;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import br.uece.spring.api.pagamento.application.model.CursoDto;

@FeignClient(value = "curso-ms")
public interface CursoFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/cursos")
    List<CursoDto> listarCursos();

    @RequestMapping(method = RequestMethod.GET, value = "/cursos/{codigo}")
    CursoDto buscarCurso(@PathVariable long codigo);
}
