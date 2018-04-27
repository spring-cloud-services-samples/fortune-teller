#!/bin/bash

# delete apps
cf delete fortunes -f
cf delete fortune-ui -f

# delete services
cf delete-service fortunes-db -f
cf delete-service fortunes-config-server -f
cf delete-service fortunes-service-registry -f
cf delete-service fortunes-circuit-breaker-dashboard -f
