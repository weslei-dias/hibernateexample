package tresb.treinamentohibernate;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) {
		Tarefa tarefa = new Tarefa();
		tarefa.setDescricao("Estudar JPA");
		tarefa.setFinalizada(true);
		tarefa.setDataFinalizacao(Calendar.getInstance());

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("tarefas");
		EntityManager manager = factory.createEntityManager();

		manager.getTransaction().begin();    
		manager.persist(tarefa);
		manager.getTransaction().commit();  

		System.out.println("ID da tarefa: " + tarefa.getId());

		manager.close();
		factory.close();
	  }
}
