#!/usr/bin/env bash

cf create-service cleardb spark fortunes-db
cf create-service p-config-server trial fortunes-config-server -c '{"git": { "uri": "https://github.com/spring-cloud-services-samples/fortune-teller", "searchPaths": "configuration" } }'
cf create-service p-service-registry trial fortunes-service-registry
cf create-service p-circuit-breaker-dashboard trial fortunes-circuit-breaker-dashboard
