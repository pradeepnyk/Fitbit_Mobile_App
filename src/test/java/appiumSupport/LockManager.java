package appiumSupport;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class LockManager {

private static HashMap<Object, ReentrantLock> map = new HashMap<Object, ReentrantLock>();
    
	public static ReentrantLock getLock(Object lockName){
		return map.get(lockName);
	}
	
	public static ReentrantLock createLock(Object lockName){
		map.put(lockName, new ReentrantLock());
		return map.get(lockName);
	}
}
