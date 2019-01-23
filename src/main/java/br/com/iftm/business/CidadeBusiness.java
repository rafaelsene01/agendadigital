package br.com.iftm.business;

import java.util.List;

import br.com.iftm.entity.Cidade;
import br.com.iftm.entity.TipoServico;

public interface CidadeBusiness {

	/**
	 * Metodo responsavel por garantir parametros obrigatorios, bem como solicitar a
	 * camada de acesso a dados que persista o objeto {@link TipoServico}.
	 * 
	 * @param tipoServico Objeto a ser persistido.
	 * @return Objeto persistido.
	 * @throws BusinessException
	 */
	Cidade create(Cidade cidade) throws BusinessException;

	/**
	 * Metodo responsavel por solicitar da base de datos todos os objetos
	 * {@link TipoServico}.
	 * 
	 * @return Lista de {@link TipoServico}.
	 * @throws BusinessException
	 */
	List<Cidade> read() throws BusinessException;

	/**
	 * Metodo responsavel por garantir parametros obrigatorios, bem como solicitar
	 * da base de datos todos os objetos {@link TipoServico}, cujo nome possua parte
	 * do parametro Nome.
	 * 
	 * @param nome Parte do nome a ser buscado.
	 * @return Lista de {@link TipoServico}.
	 * @throws BusinessException
	 */
	List<Cidade> readByName(String nome) throws BusinessException;

	/**
	 * Metodo responsavel por garantir parametros obrigatorios, bem como atualizar
	 * na base de dados o objeto {@link TipoServico}.
	 * 
	 * @param tipoServico objeto a ser persistido.
	 * @return
	 * @throws BusinessException
	 */
	Cidade update(Cidade cidade) throws BusinessException;

	/**
	 * Metodo responsavel por excluir da base de dados o objeto {@link TipoServico}
	 * referente ao id informado.
	 * 
	 * @param id identificador do objeto {@link TipoServico} a ser excluido.
	 * @throws BusinessException
	 */
	void delete(Integer id) throws BusinessException;

	List<Cidade> readByEstado(String estado) throws BusinessException;
}
