package eg.gov.iti.jets.service.gateway.aws.ec2.scheduler;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;

public enum RunningTasks {
    INSTANCE;
    private final Map<String, ScheduledFuture> tasks = new ConcurrentHashMap<>();


    public  Optional<ScheduledFuture> getTaskForInstance(String instanceId) {
        if ( tasks.containsKey(instanceId)) {
            return Optional.of(tasks.get(instanceId));
        }else {
            return Optional.empty();
        }
    }
    public void addTask(String instanceId,ScheduledFuture future){
        tasks.put(instanceId , future);
    }
}
