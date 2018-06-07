image:https://circleci.com/gh/spring-cloud-services-samples/fortune-teller.svg?style=svg["CircleCI", link="https://circleci.com/gh/spring-cloud-services-samples/fortune-teller"]

= Fortune Teller

*Fortune Teller* is a very basic application composed of two services:

. link:fortune-teller-fortune-service[Fortune Service] - serves up random Chinese fortune cookie fortunes
. link:fortune-teller-ui[Fortune UI] - presents a UI that consumes the fortune service

It leverages libraries and services from Spring Cloud and Netflix OSS to compose the system.

Fortune Teller is deployable to any Cloud Foundry environment utilizing the service components that have been packaged with the project.
However, it is most easily deployed to Pivotal Cloud Foundry environments that have installed the https://network.pivotal.io/products/p-spring-cloud-services[Spring Cloud Services] package.

== Build

. Using Maven, build and package the application:
+
----
$ mvn clean package
----
+
Maven will automatically download all of _Fortune Teller_'s dependencies. This may take a few moments.


== Deploy to Pivotal Cloud Foundry with Spring Cloud Services

. Run `scripts/pcf-create-services.sh` to create the services that you need:
+
----
$ ./scripts/pcf-create-services.sh
Creating service fortunes-db in org microservices / space fortune-teller as admin...
OK
Creating service config-server in org microservices / space fortune-teller as admin...
OK
Creating service service-registry in org microservices / space fortune-teller as admin...
OK
Creating service circuit-breaker-dashboard in org microservices / space fortune-teller as admin...
OK
----

. Click on the *Manage* links for the *Config Service*, *Service Registry*, and *Circuit Breaker Dashboard*. Make sure the services are finished initializing before you proceed.

. Edit the `manifest-pcf.yml` file to specify the Cloud Foundry target the apps are being pushed to, replacing the URL in `CF_TARGET: https://api.yourpcfenvironment.local` with the API endpoint for your Cloud Foundry deployment.

. Push the microservices:

+
----
$ cf push -f manifest-pcf.yml
----
+
This will push the fortunes service and the ui application and bind all of the services.

== Test the Application

. In a browser, access the fortunes-ui application at the route that was created for you:
+
image:docs/images/fortunes_1.png[]

. Stop the fortunes application:
+
----
$ cf stop fortune-service
----

. Access the fortunes-ui and see that the ``fallback fortune'' is being returned.
+
image:docs/images/fortunes_4.png[]

. Start the fortunes application:
+
----
$ cf start fortune-service
----

. Continue to access the fortunes-ui. After the fortunes service has re-registered with Eureka and the fortunes-ui load balancer caches are refreshed, you should then start getting random fortunes again!
