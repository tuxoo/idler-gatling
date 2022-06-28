# Load testing application for IDLER chat service https://github.com/eugene-krivtsov/idler

###

- Scala 2.13.8
- Gatling 3.7.6

For this application need EnvFile plugin by Borys Pierov and .env file which contains:

```dotenv
POSTGRES_HOST=[your postgres host here]
POSTGRES_PORT=[your postgres port here]
POSTGRES_DB=[your postgres db here]
POSTGRES_SCHEMA=[your postgres schema here]
POSTGRES_USER=[your postgres user here]
POSTGRES_PASSWORD=[your postgres password here]
```

By default, this application makes load testing server by "http://localhost:9000". For change target application URL you
need to change baseUrl parameters in Scala config.
Also, for testing feeder need to provide database connection. You should fill .env file according to database connection
parameters.

```dotenv
serverName = ${POSTGRES_HOST}
portNumber = ${POSTGRES_PORT}
databaseName = ${POSTGRES_DB}
user = ${POSTGRES_USER}
password = ${POSTGRES_PASSWORD}
currentSchema = ${POSTGRES_SCHEMA}
```

The main class for testing is GatlingRunner.scala. You should choose simulation class from com.idler.api.simulation and
put to *simulation class here*.

```dotenv
object GatlingRunner {
def main(args: Array[String]): Unit = {

    val props = new GatlingPropertiesBuilder
    props.simulationClass(classOf[simulation class here].getName)
    Gatling.fromMap(props.build)
}
}
```

Important, there are two strategy of injections steps. You should carefully choose their for successful testing.
Parameters for injection steps in Config.scala.

```dotenv
val openInjectionSteps: Seq[OpenInjectionStep] = Seq(
incrementUsersPerSec(usersPerSec)
.times(times)
.eachLevelLasting(levelLasting)
.separatedByRampsLasting(rampsLasting)
)
```

```dotenv
val closedInjectionSteps: Seq[ClosedInjectionStep] = Seq(
    incrementConcurrentUsers(usersPerSec)
      .times(times)
      .eachLevelLasting(levelLasting)
      .separatedByRampsLasting(rampsLasting)
  )
```