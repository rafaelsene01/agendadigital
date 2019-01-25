package br.com.iftm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import br.com.iftm.business.BusinessException;
import br.com.iftm.controller.dto.FiltroPrestadorDTO;
import br.com.iftm.dao.PrestadorServicoDAO;
import br.com.iftm.entity.PrestadorServico;

@Repository
public class PrestadorServicoDAOImpl implements PrestadorServicoDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public PrestadorServico create(PrestadorServico prestadorServico) {

		sessionFactory.getCurrentSession().save(prestadorServico);
		sessionFactory.getCurrentSession().flush();

		return prestadorServico;
	}

	@Override
	public List<PrestadorServico> read() {
		return sessionFactory.getCurrentSession().createCriteria(PrestadorServico.class).list();
	}

	@Override
	public List<PrestadorServico> readByName(String nome) {

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(PrestadorServico.class);

		criteria.add(Restrictions.like("nome", nome, MatchMode.ANYWHERE).ignoreCase());

		return criteria.list();
	}

	@Override
	public PrestadorServico update(PrestadorServico prestadorServico) {

		sessionFactory.getCurrentSession().update(prestadorServico);
		sessionFactory.getCurrentSession().flush();

		return prestadorServico;
	}

	@Override
	public void delete(Integer id) {

		PrestadorServico prestadorServico = sessionFactory.getCurrentSession().get(PrestadorServico.class, id);
		sessionFactory.getCurrentSession().delete(prestadorServico);
	}

	@Override
	public List<PrestadorServico> readByFiltros(FiltroPrestadorDTO filtroPrestadorDTO) throws BusinessException {

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(PrestadorServico.class);
		Criteria criteriaCidade = criteria.createCriteria("cidade");

		if (!StringUtils.isEmpty(filtroPrestadorDTO.getNome()))
			criteria.add(Restrictions.like("nome", filtroPrestadorDTO.getNome(), MatchMode.ANYWHERE).ignoreCase());
		if (filtroPrestadorDTO.getEstado() != null)
			criteriaCidade.add(Restrictions.eq("estado", filtroPrestadorDTO.getEstado()));
		if (filtroPrestadorDTO.getCidade() != null && filtroPrestadorDTO.getCidade().getCodigo() != null)
			criteria.add(Restrictions.eq("cidade", filtroPrestadorDTO.getCidade()));
		if (filtroPrestadorDTO.getTipoServicos() != null && filtroPrestadorDTO.getTipoServicos().isEmpty())
			criteria.add(Restrictions.in("tipoServicos", filtroPrestadorDTO.getTipoServicos()));

		return criteria.list();
	}

}
