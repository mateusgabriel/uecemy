package br.uece.spring.api.usuario.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.uece.spring.api.usuario.application.model.*;
import br.uece.spring.api.usuario.domain.exception.*;
import br.uece.spring.api.usuario.domain.model.*;
import br.uece.spring.api.usuario.domain.repository.*;

@Component
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BibliotecaRepository bibliotecaRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;

    // Método para listar usuários
    public List<UsuarioDto> listarUsuarios() {
        return usuarioRepository.findAll() .stream()
        .map(usuario -> mapper.map(usuario, UsuarioDto.class))
        .collect(Collectors.toList());
    }

    // Método para buscar usuário
    public UsuarioDto buscarUsuario(int codigo) {
        var usuario = usuarioRepository.findById(codigo);
        if (usuario == null) {
            throw new UsuarioNaoExistenteException();
        }

        return mapper.map(usuario, UsuarioDto.class);
    }

    //Método para cadastrar usuário
    public UsuarioDto cadastrarUsuario(NovoUsuarioDto dto) {
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new EmailVinculadoUsuarioException();
        }

        var usuario = usuarioRepository.save(mapper.map(dto, Usuario.class));
        return mapper.map(usuario, UsuarioDto.class);
    }

    //Método para atualizar cadastro
    public UsuarioDto atualizarUsuario(UsuarioDto dto) {
        if (!usuarioRepository.existsById(dto.getId())) {
            throw new UsuarioNaoExistenteException();
        }

        var usuario = usuarioRepository.save(mapper.map(dto, Usuario.class));
        return mapper.map(usuario, UsuarioDto.class);
    }

    // Método para excluir usuario
    public void excluirUsuario(int codigo) {
        var usuario = usuarioRepository.findById(codigo);
        if (usuario == null) {
            throw new UsuarioNaoExistenteException();
        }

        usuarioRepository.delete(usuario);
    }

    // Método para incluir curso na biblioteca do usuário
    @RabbitListener(queues = "notification-queue")
    private void incluirCursoUsuario(@Payload Message message) {
        try {
            NovoPagamentoDto dto = objectMapper.readValue(message.getBody(), NovoPagamentoDto.class);
            var biblioteca = mapper.map(dto, Biblioteca.class);
            bibliotecaRepository.save(biblioteca);
        } catch(Exception ex) {
            System.err.println("Falha ao receber pagamento.");
        }
    }
}