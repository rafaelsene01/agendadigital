package br.com.iftm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.iftm.business.BusinessException;
import br.com.iftm.dao.CidadeDAO;
import br.com.iftm.entity.Cidade;
import br.com.iftm.enums.Estado;

@Repository
public class CidadeDAOImpl implements CidadeDAO {

	private List<Cidade> lista = new ArrayList<>();
	private int indice = 0;

	@Override
	public Cidade create(Cidade cidade) {

		cidade.setCodigo(indice++);
		lista.add(cidade);

		return cidade;
	}

	@Override
	public List<Cidade> read() {
		return lista;
	}

	@Override
	public List<Cidade> readByName(String nome) {

		List<Cidade> listaRetorno = new ArrayList<>();
		for (Cidade cidade : lista) {
			if (cidade.getNome().toUpperCase().contains(nome.toUpperCase())) {
				listaRetorno.add(cidade);
			}
		}
		return listaRetorno;
	}

	@Override
	public Cidade update(Cidade cidade) {
		for (Cidade cidade2 : lista) {
			if (cidade2.getCodigo().equals(cidade.getCodigo())) {
				cidade2.setNome(cidade.getNome());
				cidade2.setEstado(cidade.getEstado());
			}
		}
		return cidade;
	}

	@Override
	public void delete(Integer id) {
		for (Cidade cidade : lista) {
			if (cidade.getCodigo().equals(id)) {
				lista.remove(cidade);
				break;
			}
		}
	}

	@Override
	public List<Cidade> readByEstado(Estado estado) throws BusinessException {
		List<Cidade> listaRetorno = new ArrayList<>();
		for (Cidade cidade : lista) {
			if (cidade.getEstado().equals(estado)) {
				listaRetorno.add(cidade);
			}
		}
		return listaRetorno;
	}

}
