package leituradados;


public class DataCollector {
	//AQUI VOCE DEFINE O INTERVALO DE TEMPO EM MILISEGUNDOS
	private static final int PERIOD = 1000;
	
	private volatile boolean stop = false;
	private Listener listener;

	public DataCollector(Listener listener) {
		this.listener = listener;
	}

	// VOCE VAI MUDAR O PROXIMO METODO
	// para ao inves de pegar um ponto aleatorio ler do seu dispositivo
	// ou ler de algum arquivo, etc...
	private Point nextPoint() {
		return RandomPoint.nextPoint();
	}

	public void start() {
		stop = false;
		Runnable r = new Runnable() {
			@Override
			public void run() {
				while (!stop) {
					sleep();
					listener.receiveData(nextPoint());
				}
			}
		};
		Thread t = new Thread(r);
		t.start();
	}
	
	public void stop() {
		stop = true;
	}
	
	public static void sleep() {
		try {
			Thread.sleep(PERIOD);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
