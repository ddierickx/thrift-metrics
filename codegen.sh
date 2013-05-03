#!/bin/bash

# Generate the thrift code, using a maven plugin could be a better solution but couldn't find a proper one...
thrift --gen java -out src/main/java/ src/main/thrift/Metrics.thrift
thrift --gen py -out src/main/python/ src/main/thrift/Metrics.thrift
