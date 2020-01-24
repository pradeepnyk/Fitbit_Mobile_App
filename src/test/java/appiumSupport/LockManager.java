package appiumSupport;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class LockManager {

private static HashMap<Object, ReentrantLock> map = new HashMap<Object, ReentrantLock>();
    
	public static ReentrantLock getLock(Object lockName){
		System.out.println("In get lock--------------------");
		return map.get(lockName);
	}
	
	public static ReentrantLock createLock(Object lockName){
		System.out.println("In lock--------------------");
		map.put(lockName, new ReentrantLock());
		return map.get(lockName);
	}
}
