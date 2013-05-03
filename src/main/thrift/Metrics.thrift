namespace java eu.dominiek.experiments.thrift.gen

enum MeasurementType {
    TEMPERATURE,
    MOISTURE,
    CO2,
    NO2,
    LIGHT,
    BATTERY
}

struct Measurement {
    1: required MeasurementType type,
    2: double value
}

service MetricService {
    oneway void submit(1:Measurement measurement);
}
