#!/usr/bin/env bash
if [ ! -z "`cf m | grep "p\.config-server"`" ]; then
  export service_name="p.config-server"
  export config_json="{\"git\": { \"uri\": \"https://github.com/spring-cloud-services-samples/fortune-teller\", \"searchPaths\": \"configuration\" } }"
elif [ ! -z "`cf m | grep "p-config-server"`" ]; then
  export service_name="p-config-server"
  export config_json="{\"skipSslValidation\": true, \"git\": { \"uri\": \"https://github.com/spring-cloud-services-samples/fortune-teller\", \"searchPaths\": \"configuration\" } }"
else
  echo "Can't find SCS Config Server in marketplace. Have you installed the SCS Tile?"
  exit 1;
fi

cf cs p.mysql db-small fortunes-db
cf cs $service_name standard config-server -c "$config_json"
cf cs p-service-registry standard service-registry
cf cs p-circuit-breaker-dashboard standard circuit-breaker
