package porcupine;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.airhacks.porcupine.execution.entity.Statistics;

@Path("stats2")
@RequestScoped
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class StatisticsResource2 {

    @Inject
    Instance<List<Statistics>> statistics;

    @GET
    public List<Statistics> expose() {
        return this.statistics.get();
    }
    
    //Run it after running PorcupineResource first!
    //curl -i -H"Accept:application/json" http://localhost:8080/ThreadsAS/res/stats2
    
}