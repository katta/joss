package nl.tweeenveertig.openstack.client.mock.scheduled;

import nl.tweeenveertig.openstack.client.StoredObject;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ObjectDeleter implements Runnable {

    private List<ScheduledForDeletion> objectsToDelete = new ArrayList<ScheduledForDeletion>();

    public ObjectDeleter(int startAfter, int intervalInSeconds) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this, startAfter, intervalInSeconds, TimeUnit.SECONDS);
    }

    public void scheduleForDeletion(StoredObject storedObject, Date deleteAt) {
        objectsToDelete.add(new ScheduledForDeletion(storedObject, deleteAt));
    }

    public int getAmountOfScheduledDeletions() {
        return objectsToDelete.size();
    }

    @Override
    public void run() {
        Date now = new Date();
        List<ScheduledForDeletion> objectsToDeleteNow = new ArrayList<ScheduledForDeletion>();
        for (ScheduledForDeletion scheduledForDeletion : objectsToDelete) {
            if (scheduledForDeletion.deleteIf(now)) {
                objectsToDeleteNow.add(scheduledForDeletion);
            }
        }
        objectsToDelete.removeAll(objectsToDeleteNow);
    }
}
