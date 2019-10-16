package com.app.six;

import java.io.Serializable;

public class ThreadSafeSingleton implements Serializable {

	private static final long serialVersionUID = 1L;
	private static ThreadSafeSingleton threadSafeSingleton = null;

	private ThreadSafeSingleton() {
		System.out.println("ThreadSafeSingleton.ThreadSafeSingleton()");
	}

	public static ThreadSafeSingleton getInstance() {
		if (threadSafeSingleton == null) {
			synchronized (ThreadSafeSingleton.class) {
				if (threadSafeSingleton == null) {
					threadSafeSingleton = new ThreadSafeSingleton();
				}
			}
		}
		return threadSafeSingleton;
	}

	protected Object readResolve() {
		return threadSafeSingleton;
	}

//	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException 
//	{
//		ThreadSafeSingleton instance1 = ThreadSafeSingleton.getInstance();
//		ObjectOutput out = new ObjectOutputStream(new FileOutputStream("File.txt"));
//		out.writeObject(instance1);
//		out.close();
//		ObjectInput in = new ObjectInputStream(new FileInputStream("File.txt"));
//		ThreadSafeSingleton instance2 = (ThreadSafeSingleton) in.readObject();
//		in.close();
//		System.out.println(instance1);
//		System.out.println(instance2);
//		System.out.println(instance1 == instance2);
//	}
}
