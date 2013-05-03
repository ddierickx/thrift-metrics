#!/usr/bin/env python

# requires thrift 0.9 (pip install thrift==0.9)

from Metrics.ttypes import Measurement, MeasurementType
from Metrics import MetricService

from thrift import Thrift
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol

def submit_measurement(measurement, host="localhost", port=9001):
	try:
		transport = TTransport.TFramedTransport(TSocket.TSocket(host, port))
		protocol = TBinaryProtocol.TBinaryProtocol(transport)
		client = MetricService.Client(protocol)
	 
		# open sesame
		transport.open()
		print "connection opened, sending measurement"
		client.submit(measurement)
		print "measurement sent ok"
		transport.close()
	 
	except Thrift.TException, tx:
		print "error sending measurement '%s'" % (tx.message)

measurement = Measurement(MeasurementType.MOISTURE, 31.23)
submit_measurement(measurement)