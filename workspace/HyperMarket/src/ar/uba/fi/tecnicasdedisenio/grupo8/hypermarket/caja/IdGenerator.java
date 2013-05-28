package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;
import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {

	private static class SingletonHolder {
			public static final IdGenerator instance = new IdGenerator();	
	}
	
	public static IdGenerator getInstance(){
		return SingletonHolder.instance;
	}
	
	private AtomicLong mIdGenerator = null;
	
	private IdGenerator(){
		mIdGenerator = new AtomicLong();
	}
	
	private AtomicLong getGenerator(){
		return mIdGenerator;
	}
	
	public long getNewId(){
		return getGenerator().incrementAndGet();
	}
}