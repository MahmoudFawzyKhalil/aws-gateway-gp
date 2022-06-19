package eg.gov.iti.jets.service.gateway.aws.ec2.scheduler;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;

public enum RunningTasks {
    INSTANCE;
    private final Map<String, ScheduledFuture<?>> tasks = new ConcurrentHashMap<>();


    public  Optional<ScheduledFuture<?>> getTaskForInstance(String instanceId) {
        if ( tasks.containsKey(instanceId)) {
            return Optional.of(tasks.get(instanceId));
        }else {
            return Optional.empty();
        }
    }
    public void removeAndCancelTask(String instanceId){
        if ( tasks.containsKey(instanceId)) {
            var scheduledFuture = tasks.get( instanceId );
            scheduledFuture.cancel( true );
            tasks.remove( instanceId );
        }
    }
    public void removeTask(String instanceId){
        if ( tasks.containsKey(instanceId)) {
            tasks.remove( instanceId );
        }
    }
    public void addTask(String instanceId,ScheduledFuture<?> future){
        tasks.put(instanceId , future);
    }

    @Override
    public String toString() {
        return "RunningTasks{" +
                "tasks=" + tasks +
                '}';
    }
}
