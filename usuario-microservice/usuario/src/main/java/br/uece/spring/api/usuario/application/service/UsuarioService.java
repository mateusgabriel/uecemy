package br.uece.spring.api.usuario.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uece.spring.api.usuario.application.model.NovoUsuarioDto;
import br.uece.spring.api.usuario.application.model.UsuarioDto;
import br.uece.spring.api.usuario.domain.exception.EmailVinculadoUsuarioException;
import br.uece.spring.api.usuario.domain.exception.UsuarioNaoExistenteException;
import br.uece.spring.api.usuario.domain.model.Usuario;
import br.uece.spring.api.usuario.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper mapper;

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
}