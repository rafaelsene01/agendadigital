package br.com.iftm.dao;

import java.util.List;

import br.com.iftm.business.BusinessException;
import br.com.iftm.entity.PrestadorServico;

public interface PrestadorServicoDAO {

	PrestadorServico create(PrestadorServico prestadorServico) throws BusinessException;

	List<PrestadorServico> read() throws BusinessException;

	List<PrestadorServico> readByName(String nome) throws BusinessException;

	PrestadorServico update(PrestadorServico prestadorServico) throws BusinessException;

	void delete(Integer id) throws BusinessException;
}
