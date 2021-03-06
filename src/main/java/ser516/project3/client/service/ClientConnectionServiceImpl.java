package ser516.project3.client.service;

import java.io.IOException;

import org.apache.log4j.Logger;

import ser516.project3.client.helper.ClientConnectionThread;

public class ClientConnectionServiceImpl implements ClientConnectionServiceInterface {
	final static Logger logger = Logger.getLogger(ClientConnectionServiceImpl.class);
	Thread clientConnectionThread;
	ClientConnectionThread threadInstance;

	@Override
	public void createClientConnection(final String ipAddress, final int port, final String endpoint) {
		threadInstance = new ClientConnectionThread(ipAddress, port, endpoint);
		clientConnectionThread = new Thread(threadInstance);
		clientConnectionThread.start();
	}

	@Override
	public void stopClientConnection(){
		try {
			threadInstance.getClientSession().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Error while stopping client end point::::" + e.getStackTrace().toString());
		}
		clientConnectionThread.interrupt();
	}

}