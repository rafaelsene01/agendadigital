package br.com.iftm.business.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.iftm.business.BusinessException;
import br.com.iftm.business.PrestadorServicoBusiness;
import br.com.iftm.dao.PrestadorServicoDAO;
import br.com.iftm.entity.PrestadorServico;
import br.com.iftm.entity.Telefone;
import br.com.iftm.entity.TipoServico;

@Service
public class PrestadorServicoBusinessImpl implements PrestadorServicoBusiness {

	@Autowired
	private PrestadorServicoDAO dao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public PrestadorServico create(PrestadorServico prestadorServico) throws BusinessException {
		if (StringUtils.isEmpty(prestadorServico.getNome()))
			throw new BusinessException("Nome Requerido!");
		if (prestadorServico.getCidade() == null || prestadorServico.getCidade().getCodigo() == null)
			throw new BusinessException("Cidade Requerido!");
		if (prestadorServico.getTipoLogradouro() == null)
			throw new BusinessException("TipoLogradouro Requerido!");
		if (StringUtils.isEmpty(prestadorServico.getLogradouro()))
			throw new BusinessException("Logradouro Requerido!");
		if (prestadorServico.getNumero() == null)
			throw new BusinessException("Numero Requerido!");
		if (StringUtils.isEmpty(prestadorServico.getBairro()))
			throw new BusinessException("Bairro Requerido!");
		// if (StringUtils.isEmpty(prestadorServico.getCep()))
		// throw new BusinessException("Cep Requerido!");

		if (prestadorServico.getTelefone() == null || prestadorServico.getTelefone().isEmpty())
			throw new BusinessException("Telefone Requerido!");

		for (Telefone telefone : prestadorServico.getTelefone()) {
			if (telefone.getDdd() == null)
				throw new BusinessException("DDD invalido");
			if (telefone.getNumero() == null)
				throw new BusinessException("Numero Telefone invalido");

			telefone.setPrestadorServico(prestadorServico);
		}

		if (prestadorServico.getTipoServicos() == null || prestadorServico.getTipoServicos().isEmpty())
			throw new BusinessException("Pelo menos um tipo de servico requerido");

		for (TipoServico tipoServico : prestadorServico.getTipoServicos()) {
			if (tipoServico.getCodigo() == null)
				throw new BusinessException("Codigo tipoServico requerido");
		}

		return dao.create(prestadorServico);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PrestadorServico> read() throws BusinessException {
		return dao.read();
	}

	@Override
	@Transactional(readOnly = true)
	public List<PrestadorServico> readByName(String nome) throws BusinessException {
		if (StringUtils.isEmpty(nome))
			throw new BusinessException("Nome Requerido!");

		return dao.readByName(nome);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public PrestadorServico update(PrestadorServico prestadorServico) throws BusinessException {

		if (prestadorServico.getCodigo() == null)
			throw new BusinessException("Codigo Requerido!");
		if (StringUtils.isEmpty(prestadorServico.getNome()))
			throw new BusinessException("Nome Requerido!");
		if (StringUtils.isEmpty(prestadorServico.getCidade()))
			throw new BusinessException("Cidade Requerido!");
		if (StringUtils.isEmpty(prestadorServico.getBairro()))
			throw new BusinessException("Bairro Requerido!");
		if (prestadorServico.getCidade() == null || prestadorServico.getCidade().getCodigo() == null)
			throw new BusinessException("Cidade Requerido!");
		if (StringUtils.isEmpty(prestadorServico.getCep()))
			throw new BusinessException("Cep Requerido!");
		if (prestadorServico.getTipoLogradouro() == null)
			throw new BusinessException("TipoLogradouro Requerido!");
		if (StringUtils.isEmpty(prestadorServico.getLogradouro()))
			throw new BusinessException("Logradouro Requerido!");

		if (prestadorServico.getTelefone() == null || prestadorServico.getTelefone().isEmpty())
			throw new BusinessException("Telefone Requerido!");

		for (Telefone telefone : prestadorServico.getTelefone()) {
			if (telefone.getDdd() == null)
				throw new BusinessException("DDD invalido");
			if (telefone.getNumero() == null)
				throw new BusinessException("Numero Telefone invalido");

			telefone.setPrestadorServico(prestadorServico);
		}

		if (prestadorServico.getTipoServicos() == null || prestadorServico.getTipoServicos().isEmpty())
			throw new BusinessException("Pelo menos um tipo de servico requerido");

		for (TipoServico tipoServico : prestadorServico.getTipoServicos()) {
			if (tipoServico.getCodigo() == null)
				throw new BusinessException("Codigo tipoServico requerido");
		}

		return dao.update(prestadorServico);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Integer id) throws BusinessException {
		if (id == null)
			throw new BusinessException("Codigo Requerido!");

		dao.delete(id);
	}

}
