Intro
=====
This is an example project for using Apache Thrift to communicate between a Python client and a Java server process. A simple usecase has been chosen for demonstration purposes. The Python client sends measurements (temperature, moisture, CO2, ...) to a Java server which simply prints out the measurement.

Contents
========
> /src/main/thrift : contains IDL

> /src/main/java : contains server implementation

> /src/main/python : contains client implementation

Getting it running
==================

You will need:

+ maven
+ jdk
+ python
+ thrift 0.9.0
+ python thrift library (pip install thrift==0.9)

To start the server (port 9001):

> run App.java main

To start the client and send a measurement:

> run MetricsClient.py
