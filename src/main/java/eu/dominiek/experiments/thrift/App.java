package eu.dominiek.experiments.thrift;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

import eu.dominiek.experiments.thrift.gen.Measurement;
import eu.dominiek.experiments.thrift.gen.MetricService;
import eu.dominiek.experiments.thrift.gen.MetricService.Iface;

/**
 * Hello thrift!
 */
public class App {

    /**
     * Basic implementation of the {@link MetricService}.
     * 
     * @author dierickx
     */
    public final static class StubMetricService implements Iface {

        @Override
        public void submit(final Measurement measurement) throws TException {
            System.out.println(String.format("%s : %s", measurement.getType(), measurement.getValue()));
        }

    }

    /**
     * Start up a Thrift server, serving the {@link MetricService}.
     * 
     * @param args none required.
     * @throws TTransportException in case the server could not be started.
     */
    public static void main(final String[] args) throws TTransportException {
        final TServerTransport transport = new TServerSocket(9001);
        final Args serverArgs = new Args(transport);
        final StubMetricService service = new StubMetricService();
        /* implements buffering */
        serverArgs.transportFactory(new TFramedTransport.Factory());
        serverArgs.protocolFactory(new TBinaryProtocol.Factory());
        serverArgs.processor(new MetricService.Processor(service));
        final TSimpleServer server = new TSimpleServer(serverArgs);
        server.serve();
    }
}
