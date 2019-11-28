/*
 * Copyright 2017-Present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 package io.spring.cloud.samples.fortuneteller.ui.services.fortunes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class FortuneService {
    private Logger log = LoggerFactory.getLogger(FortuneService.class);

    private RestTemplate rest;

    public FortuneService(RestTemplate rest) {
      this.rest = rest;
    }

    public Fortune randomFortune() {
        try {
            return rest.getForObject("http://fortunes/random", Fortune.class);
        } catch (RestClientException e) {
            log.error("Failed to get fortune from fortune-service", e);
            return new Fortune(42l, "Your future is unclear.");
        }
    }
}
