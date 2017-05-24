package tresb.treinamentohibernate;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Tarefa {

	@Id
	@GeneratedValue
	private Long id;

	private String descricao;

	private boolean finalizada;

	@Temporal(value = TemporalType.TIMESTAMP)
	private Calendar dataFinalizacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isFinalizada() {
		return finalizada;
	}

	public void setFinalizada(boolean finalizada) {
		this.finalizada = finalizada;
	}

	public Calendar getDataFinalizacao() {
		return dataFinalizacao;
	}

	public void setDataFinalizacao(Calendar dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}

	public void removerTarefa() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("tarefas");
		EntityManager manager = factory.createEntityManager();
		Tarefa encontrada = manager.find(Tarefa.class, 1L);

		manager.getTransaction().begin();
		manager.remove(encontrada);
		manager.getTransaction().commit();
	}
	
	public Collection<Tarefa> buscarFinalizadas()
	{
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("tarefas");
		EntityManager manager = factory.createEntityManager();
				@SuppressWarnings("unchecked")
				List<Tarefa> lista = manager
				  .createQuery("select t from Tarefa as t where t.finalizado = false")
				  .getResultList();
				return lista;
	}
	
	public Collection<Tarefa> buscarUsandoHQL()
	{
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("tarefas");
		EntityManager manager = factory.createEntityManager();
				Query query = manager
				    .createQuery("select t from Tarefa as t "+
				        "where t.finalizado = :paramFinalizado");
				query.setParameter("paramFinalizado", false);

				List<Tarefa> lista = query.getResultList();
				
				return lista;
	}
				
}
