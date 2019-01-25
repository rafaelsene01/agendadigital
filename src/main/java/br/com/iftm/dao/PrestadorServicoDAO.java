package br.com.iftm.dao;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import br.com.iftm.business.BusinessException;
import br.com.iftm.controller.dto.FiltroPrestadorDTO;
import br.com.iftm.entity.PrestadorServico;

public interface PrestadorServicoDAO {

	PrestadorServico create(PrestadorServico prestadorServico) throws BusinessException;

	List<PrestadorServico> read() throws BusinessException;

	List<PrestadorServico> readByName(String nome) throws BusinessException;

	PrestadorServico update(PrestadorServico prestadorServico) throws BusinessException;

	void delete(Integer id) throws BusinessException;

	List<PrestadorServico> readByFiltros(@RequestBody FiltroPrestadorDTO filtroPrestadorDTO) throws BusinessException;
}
