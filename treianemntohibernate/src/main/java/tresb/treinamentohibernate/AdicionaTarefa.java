package tresb.treinamentohibernate;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AdicionaTarefa {
    
    public void adicionarTarefa(String descricao, boolean finalizada, Calendar dataCriacao)
    {
        Tarefa tarefa = new Tarefa();
        tarefa.setDescricao(descricao);
        tarefa.setFinalizada(finalizada);
        tarefa.setDataFinalizacao(dataCriacao);

        EntityManagerFactory factory = Persistence.
              createEntityManagerFactory("tarefas");
        EntityManager manager = factory.createEntityManager();

        manager.getTransaction().begin();    
        manager.persist(tarefa);
        manager.getTransaction().commit();  

        System.out.println("ID da tarefa: " + tarefa.getId());

        manager.close();
        factory.close();
    }
  
}